package edu.school21.sockets.app;

import edu.school21.sockets.server.Server;

public class Main {
    private static Integer port;
    public static void main(String[] args) {
        if (args.length == 1) {
            String[] tmp = args[0].split("=");
            if (!tmp[0].equals("--port")) {
                System.err.println("Illegal Argument!");
                System.exit(-1);
            }
            
            port = Integer.parseInt(tmp[1]);
            Server server = new Server(port);
            server.connect();
        } else {
            System.err.println("Illegal Argument!");
            System.exit(-1);
        }
    }
}
