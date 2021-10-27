package org.academiadecodigo.bootcamp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Client  {


    //listen to user input
    // send to server

    //receive from server
    //print to console

    public static final int DEFAULT_PORT = 1234;
    public static final String HOST = "localhost";
    Socket clientSocket;
    private String name;
    BufferedWriter out;
    BufferedReader in, inputServer;

    ServerSocket bindSocket;

    public static void main(String[] args) {

        int port = args.length > 0 ? Integer.parseInt(args[0]) : DEFAULT_PORT;

        Client client1 = new Client("Bruno", port);

        client1.writeToConsole();

    }


    public Client(String name, int port) {
        this.name = name;

        in = new BufferedReader(new InputStreamReader(System.in));
        try {
            clientSocket(port);
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            inputServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            clientOn(this.clientSocket);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void clientSocket(int port) throws IOException {
        clientSocket = new Socket(HOST, port);
    }

    public void clientOn(Socket clientSocket) {

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {

                    try {
                        String input = in.readLine();
                        sendToServer(input);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread1.start();

    }

    public void writeToConsole() {
        String messageToConsole = null;
        try {
            messageToConsole = inputServer.readLine();
            System.out.println(messageToConsole);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void sendToServer(String message) {
        try {
            out.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}