package com.matheusdoedev.election.handlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private Socket socket;
    private String identifier;

    public ClientHandler(Socket socket, String identifier) {
        this.socket = socket;
        this.identifier = identifier;
    }

    @Override
    public void run() {
        try {
            System.out.println("Client connected: " + socket.getInetAddress().getHostAddress());
            BufferedReader in = new BufferedReader(new InputStreamReader((socket.getInputStream())));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String response = in.readLine();
            System.out.println("Received message: " + response);
            out.println("Hi! I am the process " + this.identifier);
            in.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            try {
                socket.close();
                System.out.println("Closed connection with the client: " + socket.getInetAddress().getHostAddress());
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }
}
