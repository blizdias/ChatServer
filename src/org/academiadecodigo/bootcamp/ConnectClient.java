package org.academiadecodigo.bootcamp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectClient implements Runnable {

    //try to read a line from socket

    //send to client


    private Socket clientSocket;
    private BufferedWriter out;
    private BufferedReader in;
    private ServerSocket bindSocket;
    private Server server;


    public ConnectClient(Socket clientSocket, Server server) {
        this.clientSocket = clientSocket;
        this.server = server;
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        try {

            while (true) {

                String lineToServer = in.readLine();
                server.sendToAll(lineToServer);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendToClient (String message) {
        try {
            out.write(message + "\n");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
