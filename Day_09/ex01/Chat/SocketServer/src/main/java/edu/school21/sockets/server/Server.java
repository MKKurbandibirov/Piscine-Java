package edu.school21.sockets.server;

import edu.school21.sockets.models.User;
import edu.school21.sockets.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class Server {
    private UsersService usersService;
    private ServerSocket serverSocket;
    private List<LifeCycle> lifeCycles = new ArrayList<>();

    @Autowired
    public Server(UsersService usersService) {
        this.usersService = usersService;
    }

    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);

            while(true) {
                Socket socket = serverSocket.accept();
                LifeCycle client = new LifeCycle(socket);
                lifeCycles.add(client);
                client.start();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            stop();
        }
    }

    private synchronized void sendMessageToAll(String message) {
        usersService.createMessage(message);
        lifeCycles.stream().filter(c -> c.active).forEach(c -> c.writer.println(message));
    }

    private void removeClient(LifeCycle client) {
        lifeCycles.remove(client);

        if (lifeCycles.isEmpty()) {
            stop();
        }
    }

    private void stop() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        System.exit(0);
    }

    private class LifeCycle extends Thread {
        private PrintWriter writer;
        private Scanner reader;
        private Socket socket;
        private String username;
        private String password;
        private boolean active;

        LifeCycle(Socket socket) {
            try {
                this.socket = socket;
                reader = new Scanner(socket.getInputStream());
                writer = new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        @Override
        public void run() {
            writer.println("Hello from Server!");

            while (true) {
                writer.println("Choose command: signUp, signIn, exit");

                try {
                    if (reader.hasNextLine()) {
                        String message = reader.nextLine().trim();

                        if ("signUp".equalsIgnoreCase(message)) {
                            if (!getUserPass()) {
                                exitChat();
                                break;
                            }
                            usersService.signUp(username, password);
                        } else if ("signIn".equalsIgnoreCase(message)) {
                            if (!getUserPass()) {
                                exitChat();
                                break;
                            }

                            if (usersService.signIn(username, password)) {
                                writer.println("Authorization successful!");
                                writer.println("Start messaging");
                                talk();
                                break;
                            } else {
                                writer.println("Authorization failed!");
                            }
                        } else if ("".equals(message)) {
                            continue;
                        } else if ("exit".equals(message)) {
                            exitChat();
                            break;
                        } else {
                            writer.println("Unknown command!");
                        }
                    }
                } catch (RuntimeException e) {
                    System.err.println(e.getMessage());
                    writer.println(e.getMessage());
                }
            }
        }

        private boolean getUserPass() {
            writer.println("Enter username: ");
            username = reader.nextLine().trim();

            while("".equals(username)) {
                username = reader.nextLine().trim();
            }

            if ("exit".equals(username)) {
                return false;
            }
            writer.println("Enter password: ");
            password = reader.nextLine().trim();

            while("".equals(password)) {
                password = reader.nextLine().trim();
            }

            if ("exit".equals(password)) {
                return false;
            }
            return true;
        }

        private void talk() {
            while (true) {
                active = true;
                String message = reader.nextLine().trim();

                if ("exit".equals(message)) {
                    exitChat();
                    break;
                }
                sendMessageToAll(username + ": " + message);
            }
        }

        private void exitChat() {
            try {
                writer.println("exit");
                removeClient(this);
                reader.close();
                writer.close();
                socket.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
