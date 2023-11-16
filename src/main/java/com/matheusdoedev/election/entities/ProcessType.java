package com.matheusdoedev.election.entities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.matheusdoedev.election.Main;
import com.matheusdoedev.election.sockets.ClientSocket;
import com.matheusdoedev.election.sockets.ServerSocketImpl;

public abstract class ProcessType {
	protected Integer port;
	protected String name;
	protected List<Process> processes;
	protected ServerSocketImpl serverSocket;

	protected ProcessType(String name, Integer port) {
		this.name = name;
		this.port = port;
		System.out.println("Listening on port " + this.port);

		Properties properties = new Properties();

		try (FileInputStream fileInputStream = new FileInputStream("app.config")) {
			properties.load(fileInputStream);
		} catch (FileNotFoundException exception) {
			System.err.println("Configuration file not found!");
			System.exit(0);
		} catch (IOException exception) {
			exception.printStackTrace();
			System.exit(0);
		}
		System.out.println(properties.getProperty("app.name"));

		Integer totalProcess = Integer.parseInt(properties.getProperty(("app.process.total")));

		this.processes = new ArrayList<>();
		for (int i = 1; i <= totalProcess; i++) {
			String identifier = properties.getProperty("app.process.identifier" + i);
			String host = properties.getProperty("app.process.host" + i);
			Integer processPort = properties.getProperty("app.process.port" + i);
			Process process = new Process(identifier, host, processPort);
			processes.add(process);
		}
	}

	protected void initConnection() {
		try {
			this.serverSocket = new ServerSocketImpl(this.port, this.name);
			Thread thread = new Thread(this.serverSocket);
			thread.start();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}

	public void run() {
		while (true) {
			try {
				for (Process process : this.processes) {
					try {
						ClientSocket socket = new ClientSocket(process.getHost(), process.getPort());
						socket.send("Hello, I am a consumer process " + this.name);
						String response = socket.receive();
						System.out.println("Response: " + response);
					} catch (IOException exception) {
						Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Error in connection with " + process.getIdentifier() + ": " + exception.getMessage();
					}
				}
				System.out.println("I will sleep!");
				Thread.sleep(1000*60);
				System.out.println("Wake up!");
			} catch (InterruptedException exception) {
				Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, exception);
			}
		}
	}
}
