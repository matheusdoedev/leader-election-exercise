package com.matheusdoedev.election.entities;

public class Process {
    private String identifier;
    private String host;
    private Integer port;

    public Process(String identifier, String host, Integer port) {
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

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
