package com.matheusdoedev.election.entities;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.matheusdoedev.election.Main;

public abstract class ProcessType {
	protected String port;
	protected String name;

	protected ProcessType(String port, String name) {
		this.port = port;
		this.name = name;
		System.out.println("Listening on port " + this.port);
	}

	public void run() {
		while (true) {
			try {
				System.out.println("I will sleep!");
				Thread.sleep(1000 * 60);
				System.out.println("I wake up!");
				Thread.sleep(1000 * 60);
			} catch (InterruptedException exception) {
				Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, exception);
			}
		}
	}
}
