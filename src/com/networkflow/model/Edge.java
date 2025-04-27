package com.networkflow.model;

public class Edge {
    private Node from;
    private Node to;
    private int capacity;
    private int flow;

    public Edge(Node from, Node to, int capacity) {
        this.from = from;
        this.to = to;
        this.capacity = capacity;
        this.flow = 0;
    }

    public Node getFrom() {
        return from;
    }

    public Node getTo() {
        return to;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getFlow() {
        return flow;
    }

    public void setFlow(int flow) {
        this.flow = flow;
    }

    public int getResidualCapacity() {
        return capacity - flow;
    }

    @Override
    public String toString() {
        return "Edge from " + from.getId() + " to " + to.getId() + " (capacity: " + capacity + ", flow: " + flow + ")";
    }
}