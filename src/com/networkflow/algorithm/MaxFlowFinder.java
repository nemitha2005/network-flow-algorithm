package com.networkflow.algorithm;

import com.networkflow.model.Edge;
import com.networkflow.model.FlowNetwork;
import com.networkflow.model.Node;

import java.util.*;

public class MaxFlowFinder {
    private FlowNetwork network;
    private List<String> steps;

    public MaxFlowFinder(FlowNetwork network) {
        this.network = network;
        this.steps = new ArrayList<>();
    }

    public int findMaxFlow() {
        int maxFlow = 0;

        Node source = network.getSource();
        Node target = network.getTarget();

        steps.add("Starting maximum flow calculation:");

        while (true) {
            Map<Node, Node> parentMap = findAugmentingPath(source, target);

            if (!parentMap.containsKey(target)) {
                break;
            }

            int pathFlow = findBottleneckCapacity(parentMap, source, target);

            augmentFlow(parentMap, source, target, pathFlow);

            maxFlow += pathFlow;

            steps.add("Found augmenting path with flow " + pathFlow + ". Total flow now: " + maxFlow);
        }

        steps.add("Maximum flow found: " + maxFlow);
        return maxFlow;
    }

    private Map<Node, Node> findAugmentingPath(Node source, Node target) {
        Map<Node, Node> parentMap = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();

        queue.add(source);
        parentMap.put(source, null);

        while (!queue.isEmpty() && !parentMap.containsKey(target)) {
            Node current = queue.poll();

            for (Edge edge : current.getOutgoingEdges()) {
                Node next = edge.getTo();

                if (!parentMap.containsKey(next) && edge.getResidualCapacity() > 0) {
                    parentMap.put(next, current);
                    queue.add(next);
                }
            }

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

    private int findBottleneckCapacity(Map<Node, Node> parentMap, Node source, Node target) {
        int bottleneck = Integer.MAX_VALUE;
        Node current = target;

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

    private void augmentFlow(Map<Node, Node> parentMap, Node source, Node target, int amount) {
        Node current = target;
        StringBuilder pathDescription = new StringBuilder("Path: ");

        while (!current.equals(source)) {
            Node parent = parentMap.get(current);
            Edge edge = findEdge(parent, current);

            if (edge != null) {
                if (edge.getFrom().equals(parent)) {
                    edge.setFlow(edge.getFlow() + amount);
                    pathDescription.insert(6, parent.getId() + " -> ");
                } else {
                    edge.setFlow(edge.getFlow() - amount);
                    pathDescription.insert(6, parent.getId() + " <- ");
                }
            }

            current = parent;
        }

        pathDescription.append(target.getId());
        steps.add(pathDescription.toString() + " with flow " + amount);
    }

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

    public List<String> getExplanationSteps() {
        return steps;
    }
}