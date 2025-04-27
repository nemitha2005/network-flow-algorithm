package com.networkflow.model;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private int id;
    private List<Edge> outgoingEdges;
    private List<Edge> incomingEdges;

    public Node(int id) {
        this.id = id;
        this.outgoingEdges = new ArrayList<>();
        this.incomingEdges = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public List<Edge> getOutgoingEdges() {
        return outgoingEdges;
    }

    public List<Edge> getIncomingEdges() {
        return incomingEdges;
    }

    public void addOutgoingEdge(Edge edge) {
        outgoingEdges.add(edge);
    }

    public void addIncomingEdge(Edge edge) {
        incomingEdges.add(edge);
    }

    @Override
    public String toString() {
        return "Node " + id;
    }
}