package com.matheusdoedev.election.sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.matheusdoedev.election.handlers.ClientHandler;

public class ServerSocketImpl implements Runnable {

    private ServerSocket serverSocket;
    private String identifier;

    public ServerSocketImpl(Integer port, String identifier) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.identifier = identifier;
        System.out.println("Server started in port " + port + ". Waiting");
    }

    @Override
    public void run() {
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                Thread thread = new Thread(new ClientHandler(socket, this.identifier));
                thread.start();
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
