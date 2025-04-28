package com.networkflow.util;

import com.networkflow.model.Edge;
import com.networkflow.model.FlowNetwork;

/**
 * Utility class for formatting and displaying network information
 */
public class OutputFormatter {

    /**
     * Print information about the network topology
     *
     * @param network flow network to display information about
     */
    public static void printNetworkInfo(FlowNetwork network) {
        System.out.println("========================================");
        System.out.println("Network Information:");
        System.out.println("----------------------------------------");
        System.out.println("Number of nodes: " + network.getNumberOfNodes());
        System.out.println("Number of edges: " + network.getEdges().size());
        System.out.println("Source node: " + network.getSource().getId());
        System.out.println("Target node: " + network.getTarget().getId());
        System.out.println("----------------------------------------");
        System.out.println("Edges:");
        for (Edge edge : network.getEdges()) {
            System.out.println("  From " + edge.getFrom().getId() +
                    " to " + edge.getTo().getId() +
                    " (capacity: " + edge.getCapacity() + ")");
        }
        System.out.println("========================================");
    }

    /**
     * Print final state of the network with flow values
     *
     * @param network flow network with updated flow values
     */
    public static void printFinalState(FlowNetwork network) {
        System.out.println("========================================");
        System.out.println("Final network state:");
        System.out.println("----------------------------------------");
        for (Edge edge : network.getEdges()) {
            System.out.println("  Edge from " + edge.getFrom().getId() +
                    " to " + edge.getTo().getId() +
                    " (flow: " + edge.getFlow() + "/" +
                    edge.getCapacity() + ")");
        }
        System.out.println("========================================");
    }
}