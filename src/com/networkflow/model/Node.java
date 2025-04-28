package com.networkflow.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represent node in the flow network.
 * Maintain lists of incoming and outgoing edges.
 */
public class Node {
    private final int id;
    private final List<Edge> outgoingEdges;
    private final List<Edge> incomingEdges;

    /**
     * Create new node with the specified ID.
     *
     * @param id unique identifier (this node)
     */
    public Node(int id) {
        this.id = id;
        this.outgoingEdges = new ArrayList<>();
        this.incomingEdges = new ArrayList<>();
    }

    /**
     * Get ID of this node.
     *
     * @return node ID
     */
    public int getId() {
        return id;
    }

    /**
     * Get all edges that leave this node.
     *
     * @return outgoing edges list
     */
    public List<Edge> getOutgoingEdges() {
        return outgoingEdges;
    }

    /**
     * Get all edges that enter this node.
     *
     * @return incoming edges list
     */
    public List<Edge> getIncomingEdges() {
        return incomingEdges;
    }

    /**
     * Add edge to the list of outgoing edges.
     *
     * @param edge edge to add
     */
    public void addOutgoingEdge(Edge edge) {
        outgoingEdges.add(edge);
    }

    /**
     * Add edge to the list of incoming edges.
     *
     * @param edge edge to add
     */
    public void addIncomingEdge(Edge edge) {
        incomingEdges.add(edge);
    }

    /**
     * Provide string representation of this node.
     *
     * @return node information
     */
    @Override
    public String toString() {
        return "Node " + id;
    }
}