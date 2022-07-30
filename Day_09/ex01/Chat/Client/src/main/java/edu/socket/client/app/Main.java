package edu.socket.client.app;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    private static Integer port;

    public static void main(String[] args) {
        if (args.length == 1) {
            String[] tmp = args[0].split("=");
            if (!tmp[0].equals("--server-port")) {
                System.err.println("Illegal Argument!");
                System.exit(-1);
            }
            port = Integer.parseInt(tmp[1]);
            Client client = new Client(port);
            try {
                client.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Illegal Argument!");
            System.exit(-1);
        }
    }
}
