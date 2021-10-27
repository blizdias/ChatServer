package org.academiadecodigo.bootcamp;

public class Main {
    public static final int DEFAULT_PORT = 1234;

    public static void main(String[] args) {


        int port = args.length > 0 ? Integer.parseInt(args[0]) : DEFAULT_PORT;

        Server server = new Server();
        server.listen(port);






        //Accept()
        //Start()
    }

}
