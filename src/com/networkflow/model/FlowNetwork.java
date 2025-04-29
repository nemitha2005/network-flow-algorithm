package com.networkflow.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a flow network with nodes and edges.
 */
public class FlowNetwork {
    private final List<Node> nodes;
    private final List<Edge> edges;
    private final int        sourceId;
    private final int        targetId;

    /**
     * Create new flow network with the specified number of nodes.
     *
     * @param numberOfNodes number of nodes in network
     */
    public FlowNetwork(int numberOfNodes) {
        nodes = new ArrayList<>(numberOfNodes);
        edges = new ArrayList<>();

        for (int i = 0; i < numberOfNodes; i++) {
            nodes.add(new Node(i));
        }

        sourceId = 0;
        targetId = numberOfNodes - 1;
    }

    /**
     * Add directed edge to network with specified capacity.
     *
     * @param from source node ID
     * @param to target node ID
     * @param capacity maximum flow capacity
     * @return created edge
     */
    public Edge addEdge(int from, int to, int capacity) {
        Node fromNode = nodes.get(from);
        Node toNode   = nodes.get(to);

        Edge edge     = new Edge(fromNode, toNode, capacity);

        fromNode.addOutgoingEdge(edge);
        toNode.addIncomingEdge(edge);
        edges.add(edge);

        return edge;
    }

    /**
     * Get source node of network (node with ID 0).
     *
     * @return source node
     */
    public Node getSource() {
        return nodes.get(sourceId);
    }

    /**
     * Get target node of network (node with highest ID).
     *
     * @return target node
     */
    public Node getTarget() {
        return nodes.get(targetId);
    }

    /**
     * Get all edges in network.
     *
     * @return all edges list
     */
    public List<Edge> getEdges() {
        return edges;
    }

    /**
     * Get total number of nodes in network.
     *
     * @return number of nodes
     */
    public int getNumberOfNodes() {
        return nodes.size();
    }
}