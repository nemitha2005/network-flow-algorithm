package com.networkflow.model;

/**
 * Represent directed edge in the flow network
 * It has a start and end node, max capacity, and current flow value.
 */
public class Edge {
    private final Node from;
    private final Node to;
    private final int capacity;
    private int flow;

    /**
     * Make new edge with given nodes and capacity.
     * Flow starts at 0.
     *
     * @param from starting node
     * @param to ending node
     * @param capacity how much flow can go through
     */
    public Edge(Node from, Node to, int capacity) {
        this.from = from;
        this.to = to;
        this.capacity = capacity;
        this.flow = 0;
    }

    /**
     * Get source node of this edge.
     *
     * @return source node
     */
    public Node getFrom() {
        return from;
    }

    /**
     * Get target node of this edge.
     *
     * @return target node
     */
    public Node getTo() {
        return to;
    }

    /**
     * Get maximum capacity of this edge.
     *
     * @return maximum flow capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Get current flow value through this edge.
     *
     * @return current flow
     */
    public int getFlow() {
        return flow;
    }

    /**
     * Set flow value for this edge.
     *
     * @param flow new flow value
     */
    public void setFlow(int flow) {
        this.flow = flow;
    }

    /**
     * Calculate residual capacity of this edge
     *
     * @return residual capacity
     */
    public int getResidualCapacity() {
        return capacity - flow;
    }

    /**
     * Provide string representation of this edge.
     *
     * @return string with edge information
     */
    @Override
    public String toString() {
        return "Edge from " + from.getId() + " to " + to.getId() +
                " (capacity: " + capacity + ", flow: " + flow + ")";
    }
}