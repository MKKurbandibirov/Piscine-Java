package edu.socket.client.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket clientSocket;
    private Scanner in;
    private PrintWriter out;

    public Client(Integer port) {
        try {
            this.clientSocket = new Socket("localhost",port);
            this.in = new Scanner(clientSocket.getInputStream());
            this.out = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() throws IOException {
        Writer writer = new Writer(out);
        Reader reader = new Reader(in, writer);
        writer.start();
        reader.start();

        try {
            writer.join();
            reader.join();
            stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void stop() {
        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    private class Reader extends Thread {
        private Scanner reader;
        private Writer writer;

        public Reader(Scanner reader, Writer writer) {
            this.reader = reader;
            this.writer = writer;
        }

        @Override
        public void run() {
            while (reader.hasNextLine()) {
                String message = reader.nextLine();
                System.out.println(message);
                if (message.equals("exit")) {
                    reader.close();
                    writer.setFlag(false);
                    break;
                }
            }
        }
    }

    private class Writer extends Thread {
        private PrintWriter writer;
        private Scanner scanner = new Scanner(System.in);
        private Boolean flag = true;

        public Writer(PrintWriter writer) {
            this.writer = writer;
        }

        public Boolean getFlag() {
            return this.flag;
        }

        public void setFlag(Boolean flag) {
            this.flag = flag;
        }


        @Override
        public void run() {
            while (flag) {
                String message = scanner.nextLine();
                writer.println(message);
                if (message.equals("exit")) {
                    writer.close();
                    System.exit(0);
                }
            }
            writer.close();
        }
    }
}
