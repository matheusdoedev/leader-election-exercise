package com.matheusdoedev.election.entities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.matheusdoedev.election.Main;
import com.matheusdoedev.election.sockets.ClientSocket;
import com.matheusdoedev.election.sockets.ServerSocketImpl;

public abstract class ProcessType {

	protected static final int THREAD_SLEEP_TIME = 1000 * 60;

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
			Integer processPort = Integer.getInteger(properties.getProperty("app.process.port" + i));
			Process process = new Process(identifier, host, processPort);
			processes.add(process);
		}
		this.initConnection();
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

	protected void createClientSocket(Process process, String processType) {
		try {
			ClientSocket socket = new ClientSocket(process.getHost(), process.getPort());
			String socketSendMessage = String.format("Hello, I am a %s process %s", processType, this.name);

			socket.send(socketSendMessage);

			String response = socket.receive();

			System.out.println("Response: " + response);
		} catch (IOException exception) {
			String logString = String.format("Error in connection with %s: %s", process.getIdentifier(),
					exception.getMessage());

			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, logString);
		}
	}

	public abstract void run();
}
