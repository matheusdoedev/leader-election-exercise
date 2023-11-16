package com.matheusdoedev.election.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSocket {
    private final Socket socket;
    private final BufferedReader in;
    private final PrintWriter out;

    public ClientSocket(String host, Integer port) throws IOException {
        this.socket = new Socket(host, port);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream(), true);
    }

    public void send(String message) {
        this.out.println(message);
    }

    public String receive() throws IOException {
        return this.in.readLine();
    }

    public void close() throws IOException {
        this.in.close();
        this.out.close();
    }
}
