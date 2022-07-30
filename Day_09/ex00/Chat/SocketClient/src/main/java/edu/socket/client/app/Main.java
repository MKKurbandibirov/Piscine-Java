package edu.socket.client.app;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    private static Socket clientSocket;
    private static Scanner reader;
    private static Scanner in;
    private static PrintWriter out;
    private static Integer port;

    private static void loop() throws IOException {
        while (true) {
            String response = in.nextLine();
            if (response.equals("Incorrect command!\n")) {
                System.out.printf("%s\n-> ", response);
                continue;
            }
            System.out.printf("%s\n-> ", response);
            String message = reader.nextLine();
            if (message.equals("exit")) {
                System.out.println("Bye!");
                return;
            }
            out.println(message);

        }
    }

    public static void main(String[] args) {
        if (args.length == 1) {
            String[] tmp = args[0].split("=");
            if (!tmp[0].equals("--server-port")) {
                System.err.println("Illegal Argument!");
                System.exit(-1);
            }
            port = Integer.parseInt(tmp[1]);
            try {
                try {
                    clientSocket = new Socket("localhost", port);
                    System.out.println("Client started!\n-------------------------------");
                    reader = new Scanner(System.in);
                    in = new Scanner(clientSocket.getInputStream());
                    out = new PrintWriter(clientSocket.getOutputStream(), true);
                    loop();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("-------------------------------\nClient finished!");
                    clientSocket.close();
                    in.close();
                    out.close();
                }
            } catch (IOException e) {
                System.err.println(e);
            }

        } else {
            System.err.println("Illegal Argument!");
            System.exit(-1);
        }
    }
}
