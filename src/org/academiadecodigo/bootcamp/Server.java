package org.academiadecodigo.bootcamp;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {



    //Listen incoming connections

    //knows connected clients - fazer linked list de clientes

    // can broadcast to all


    private static final Logger logger = Logger.getLogger(Server.class.getName());
    public static final String DOCUMENT_ROOT = "www/";
    public LinkedList<ConnectClient> clientLinkedList;
    private Client client;

    private ServerSocket bindSocket;



    public Server() {
        clientLinkedList = new LinkedList<>();
    }


    public void addToLinked(ConnectClient connectClient) {
        clientLinkedList.addFirst(connectClient);
    }

    public void listen(int port) {


        try {
            bindSocket = new ServerSocket(port);
            logger.log(Level.INFO, "server bind to " + getAddress());
            dispatch(bindSocket);

        } catch (IOException e) {

            logger.log(Level.SEVERE, "could not bind to port " + port);
            logger.log(Level.SEVERE, e.getMessage());
            System.exit(1);
        }
    }

    public void dispatch(ServerSocket bindSocket) {

        while (true) {

            try {
                //ExecutorService cachedPool = Executors.newCachedThreadPool();

                // accepts client connections and instantiates client dispatchers
                ConnectClient connectClient = new ConnectClient(bindSocket.accept(),this);


                logger.log(Level.INFO, "new connection from " + connectClient);


                // launch the client thread
                Thread clientThread = new Thread(connectClient);
                addToLinked(connectClient);

                clientThread.start();



            } catch (IOException e) {

                logger.log(Level.SEVERE, e.getMessage());

            }
        }
    }

    public String getAddress() {

        if (bindSocket == null) {
            return null;
        }

        return bindSocket.getInetAddress().getHostAddress() + ":" + bindSocket.getLocalPort();
    }

    public void sendToAll(String message) {

        for (ConnectClient c: clientLinkedList) {
            c.sendToClient(message);
        }
    }


}


