package com.networkflow.model;

import java.util.ArrayList;
import java.util.List;

public class FlowNetwork {
    private final List<Node> nodes;
    private final List<Edge> edges;
    private final int sourceId;
    private final int targetId;

    public FlowNetwork(int numberOfNodes) {
        nodes = new ArrayList<>(numberOfNodes);
        edges = new ArrayList<>();

        for (int i = 0; i < numberOfNodes; i++) {
            nodes.add(new Node(i));
        }

        sourceId = 0;
        targetId = numberOfNodes - 1;
    }

    public Edge addEdge(int from, int to, int capacity) {
        Node fromNode = nodes.get(from);
        Node toNode = nodes.get(to);

        Edge edge = new Edge(fromNode, toNode, capacity);

        fromNode.addOutgoingEdge(edge);
        toNode.addIncomingEdge(edge);
        edges.add(edge);

        return edge;
    }

    public Node getSource() {
        return nodes.get(sourceId);
    }

    public Node getTarget() {
        return nodes.get(targetId);
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public int getNumberOfNodes() {
        return nodes.size();
    }
}