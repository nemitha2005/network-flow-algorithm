package com.networkflow.algorithm;

import com.networkflow.model.Edge;
import com.networkflow.model.FlowNetwork;
import com.networkflow.model.Node;

import java.util.*;

/**
 * This class uses Edmonds-Karp algorithm to calculate maximum flow
 * (better version of Ford-Fulkerson)
 */
public class MaxFlowFinder {

    // The network where maximum flow is calculated
    private final FlowNetwork network;

    // List that stores steps of algorithm (for explanation)
    private final List<String> steps;

    // Counter for how many times the loop runs
    private int iteration;

    /**
     * Creates new MaxFlowFinder for the given network
     *
     * @param network flow network to work on
     */
    public MaxFlowFinder(FlowNetwork network) {
        this.network = network;
        this.steps = new ArrayList<>();
        this.iteration = 0;
    }

    /**
     * Calculate the maximum flow in the network
     *
     * @return total maximum flow
     */
    public int findMaxFlow() {
        int maxFlow = 0;
        Node source = network.getSource();
        Node target = network.getTarget();

        steps.add("========================================");
        steps.add("Start maximum flow calculation:");
        steps.add("Source node: " + source.getId());
        steps.add("Target node: " + target.getId());
        steps.add("----------------------------------------");

        // Repeat until no more augmenting paths
        while (true) {
            iteration++;
            steps.add("\nIteration " + iteration + ":");
            steps.add("----------------------------------------");

            // Try to get a path from source to target
            Map<Node, Node> parentMap = findAugmentingPath(source, target);
            if (!parentMap.containsKey(target)) {
                steps.add("No more paths found.");
                steps.add("----------------------------------------");
                break;
            }

            // Find smallest capacity in the path (bottleneck)
            int pathFlow = findBottleneckCapacity(parentMap, source, target);

            // Path description for logs
            List<Edge> pathEdges = getPathEdges(parentMap, source, target);
            StringBuilder pathDescription = new StringBuilder(" Path found, flow possible: " + pathFlow + ":\n");

            for (Edge edge : pathEdges) {
                pathDescription.append("   Edge from ").append(edge.getFrom().getId()).append(" to ").append(edge.getTo().getId()).append(" (flow: ").append(edge.getFlow()).append("/").append(edge.getCapacity()).append(")\n");
            }
            steps.add(pathDescription.toString().trim());

            // Increase flow through the path
            augmentFlow(parentMap, source, target, pathFlow);
            maxFlow += pathFlow;

            steps.add("Flow increased by " + pathFlow + ", total flow now " + maxFlow);
            steps.add("----------------------------------------");
        }

        steps.add("\nMaximum flow is " + maxFlow);
        steps.add("========================================");
        return maxFlow;
    }

    /**
     * Uses BFS to find a path from source to target
     *
     * @param source starting node
     * @param target target node
     * @return map that shows path to each node
     */
    private Map<Node, Node> findAugmentingPath(Node source, Node target) {
        Map<Node, Node> parentMap = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();

        queue.add(source);

        // no parent for source
        parentMap.put(source, null);

        while (!queue.isEmpty() && !parentMap.containsKey(target)) {
            Node current = queue.poll();

            // Forward edges
            for (Edge edge : current.getOutgoingEdges()) {
                Node next = edge.getTo();
                if (!parentMap.containsKey(next) && edge.getResidualCapacity() > 0) {
                    parentMap.put(next, current);
                    queue.add(next);
                }
            }

            // Backward edges
            for (Edge edge : current.getIncomingEdges()) {
                Node next = edge.getFrom();
                if (!parentMap.containsKey(next) && edge.getFlow() > 0) {
                    parentMap.put(next, current);
                    queue.add(next);
                }
            }
        }

        return parentMap;
    }

    /**
     * Finds the bottleneck in the path
     *
     * @param parentMap path info from BFS
     * @param source start node
     * @param target end node
     * @return minimum flow that can be pushed through path
     */
    private int findBottleneckCapacity(Map<Node, Node> parentMap, Node source, Node target) {
        int bottleneck = Integer.MAX_VALUE;
        Node current = target;

        // Go backward from target to source
        while (!current.equals(source)) {
            Node parent = parentMap.get(current);
            Edge edge = findEdge(parent, current);

            if (edge != null) {
                if (edge.getFrom().equals(parent)) {
                    bottleneck = Math.min(bottleneck, edge.getResidualCapacity());
                } else {
                    bottleneck = Math.min(bottleneck, edge.getFlow());
                }
            }

            current = parent;
        }

        return bottleneck;
    }

    /**
     * Update flow values along the path
     *
     * @param parentMap path info
     * @param source start node
     * @param target end node
     * @param amount flow amount to add
     */
    private void augmentFlow(Map<Node, Node> parentMap, Node source, Node target, int amount) {
        Node current = target;

        while (!current.equals(source)) {
            Node parent = parentMap.get(current);
            Edge edge = findEdge(parent, current);

            if (edge != null) {
                if (edge.getFrom().equals(parent)) {
                    edge.setFlow(edge.getFlow() + amount);
                } else {
                    edge.setFlow(edge.getFlow() - amount);
                }
            }

            current = parent;
        }
    }

    /**
     * Collect edges used in path from source to target
     *
     * @param parentMap the parent info for path
     * @param source start node
     * @param target end node
     * @return list of edges in the path
     */
    private List<Edge> getPathEdges(Map<Node, Node> parentMap, Node source, Node target) {
        List<Edge> pathEdges = new ArrayList<>();
        Node current = target;

        // Collect edges by walking back from target
        while (!current.equals(source)) {
            Node parent = parentMap.get(current);
            Edge edge = findEdge(parent, current);
            if (edge != null) {
                pathEdges.add(edge);
            }
            current = parent;
        }

        // Reverse list to get correct order
        // from source to target
        Collections.reverse(pathEdges);
        return pathEdges;
    }

    /**
     * Find edge between two nodes
     *
     * @param from first node
     * @param to second node
     * @return the edge between the two nodes, or null
     */
    private Edge findEdge(Node from, Node to) {
        for (Edge edge : from.getOutgoingEdges()) {
            if (edge.getTo().equals(to)) {
                return edge;
            }
        }

        for (Edge edge : from.getIncomingEdges()) {
            if (edge.getFrom().equals(to)) {
                return edge;
            }
        }

        return null;
    }

    /**
     * Return explanation log for all steps
     *
     * @return list of strings showing logs ( steps )
     */
    public List<String> getExplanationSteps() {
        return steps;
    }
}