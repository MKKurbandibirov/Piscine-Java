package edu.school21.sockets.server;

import edu.school21.sockets.services.UsersService;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Server {
    private static List<Client> clients = new ArrayList<>();
    private ServerSocket server;
    private final Integer port;
    private final UsersService service;

    public Server(Integer port) {
        ApplicationContext context = new AnnotationConfigApplicationContext("edu.school21.sockets");
        this.service = context.getBean("usersService", UsersService.class);
        this.port = port;
    }

    public void start() {
        try {
            try {
                server = new ServerSocket(this.port);
                System.out.println("Server started!\n-------------------------------");
                try (Socket clientSocket = server.accept()) {
//                    ServerLifeCycle cycle = new ServerLifeCycle(clientSocket, service);
//                    clientsSockets.add(cycle);
//                    executorService.execute(cycle.start());
                }
            } finally {
                System.out.println("-------------------------------\nServer finished!");
                server.close();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private void stop() {
        try {
            if (service != null) {
                server.close();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        System.exit(0);
    }

    private void remove(Client client) {
        clients.remove(client);
        System.out.println("The user has left the chat.");

        if (clients.isEmpty()) {
            System.out.println("No users! The chat is closed!");
            stop();
        }
    }


    private class Client extends Thread {
        private Scanner reader;
        private PrintWriter writer;
        private Socket socket;
        private String username;
        private String password;
        private Boolean online;

        public Client(Socket socket) {
            this.socket = socket;
            try {
                this.reader = new Scanner(socket.getInputStream());
                this.writer = new PrintWriter(socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            writer.println("Hello from server!");
            while (true) {
                writer.println("Choose command:\n\tsignUp\n\tsignIn\n\texit");
                if (reader.hasNextLine()) {
                    String message = reader.nextLine();
                    if (message.equals("signUp")) {
                        if (!readUserData()) {

                        }
                    }
                }

            }
        }

        public Boolean readUserData() {
            writer.println("Enter username:");
            if (reader.hasNextLine()) {
                username = reader.nextLine();
            }
            if (username.equals("exit")) {
                return false;
            }
            writer.println("Enter password:");
            if (reader.hasNextLine()) {
                username = reader.nextLine();
            }
            if (username.equals("exit")) {
                return false;
            }
            return true;
        }


    }
}
