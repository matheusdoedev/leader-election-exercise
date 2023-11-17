package com.matheusdoedev.election.entities;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.matheusdoedev.election.Main;

public class Consumer extends ProcessType {
	public Consumer(String name, Integer port) {
		super(name, port);
	}

	@Override
	public void run() {
		while (true) {
			try {
				for (Process process : this.processes) {
					this.createClientSocket(process, "consumer");
				}
				System.out.println("I will sleep!");
				Thread.sleep(THREAD_SLEEP_TIME);
				System.out.println("Wake up!");
			} catch (InterruptedException exception) {
				Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, exception);
				Thread.currentThread().interrupt();
			}
		}
	}
}
