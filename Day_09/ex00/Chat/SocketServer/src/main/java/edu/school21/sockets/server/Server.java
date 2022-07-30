package edu.school21.sockets.server;

import edu.school21.sockets.services.UsersService;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Server {
    private Socket clientSocket;
    private ServerSocket server;
    private Scanner in;
    private PrintWriter out;
    private Integer port;
    private UsersService service;

    public Server(Integer port) {
        this.port = port;
        ApplicationContext context = new AnnotationConfigApplicationContext("edu.school21.sockets");
        this.service = context.getBean("usersService", UsersService.class);
    }

    public void connect() {
        try {
            try {
                server = new ServerSocket(this.port);
                System.out.println("Server started!\n-------------------------------");
                clientSocket = server.accept();
                try {
                    in = new Scanner(clientSocket.getInputStream());
                    out = new PrintWriter(clientSocket.getOutputStream(), true);
                    this.loop();
                } finally {
                    clientSocket.close();
                    in.close();
                    out.close();
                }
            } finally {
                System.out.println("-------------------------------\nServer finished!");
                server.close();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void loop() throws IOException {
        out.println("Hello from Server!");
        while (true) {
            if (!in.hasNextLine()) {
                continue;
            }
            String registration = in.nextLine();
            if (registration.equals("signUp")) {
                out.println("Enter username:");
                if (!in.hasNextLine()) {
                    continue;
                }
                String username = in.nextLine();
                out.println("Enter password:");
                if (!in.hasNextLine()) {
                    continue;
                }
                String password = in.nextLine();
                if (this.service.signUp(username, password)) {
                    out.println("Successful!");
                    continue;
                }
                out.println("Unsuccessful!");
            } else {
                out.println("Incorrect command!");
            }
        }
    }
}
