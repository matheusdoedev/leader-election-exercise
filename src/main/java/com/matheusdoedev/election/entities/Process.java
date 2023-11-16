package com.matheusdoedev.election.entities;

public class Process {
    private String identifier;
    private String host;
    private String port;

    public Process(String identifier, String host, String port) {
        this.identifier = identifier;
        this.host = host;
        this.port = port;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
