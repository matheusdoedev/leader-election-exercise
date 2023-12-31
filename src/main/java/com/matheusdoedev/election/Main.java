package com.matheusdoedev.election;

import com.matheusdoedev.election.entities.Consumer;
import com.matheusdoedev.election.entities.Producer;

public class Main {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("To execute the program: Main <type> <identifier> <port>");
            System.exit(0);
        }

        String type = args[0];
        String name = args[1];
        Integer port = Integer.getInteger(args[2]);
        System.out.println("Hello, I am a program from type " + type + " with identifier " + name);

        if (type.equals("producer")) {
            Producer producer = new Producer(name, port);
            producer.run();
        } else if (type.equals("consumer")) {
            Consumer consumer = new Consumer(name, port);
            consumer.run();
        } else {
            System.out.println("Not valid type!");
        }
    }
}