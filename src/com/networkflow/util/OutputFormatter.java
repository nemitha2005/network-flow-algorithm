package com.networkflow.util;

import com.networkflow.model.Edge;
import com.networkflow.model.FlowNetwork;

/**
 * Global Utility class that for formatting and displaying network information
 */
public class OutputFormatter {

    /**
     * Display summary of the network topology
     *
     * @param network flow network to display information about
     */
    public static void displayNetworkSummary(FlowNetwork network) {
        System.out.println("===== NETWORK TOPOLOGY =====");
        System.out.println("Total vertices: " + network.getNumberOfNodes());
        System.out.println("Total connections: " + network.getEdges().size());
        System.out.println("Entry point: Node #" + network.getSource().getId());
        System.out.println("Exit point: Node #" + network.getTarget().getId());

        System.out.println("\nConnection Details:");
        for (Edge edge : network.getEdges()) {
            System.out.println("  Link " + edge.getFrom().getId() + " → " +
                    edge.getTo().getId() + " [Max throughput: " +
                    edge.getCapacity() + " units]");
        }
        System.out.println("===========================\n");
    }

    /**
     * Displays final state of the network after execution
     *
     * @param network flow network with updated flow values
     */
    public static void displayFinalState(FlowNetwork network) {
        System.out.println("\n===== FINAL CONFIGURATION =====");
        for (Edge edge : network.getEdges()) {
            int usage = (int)((double)edge.getFlow() / edge.getCapacity() * 100);
            System.out.println("  Link " + edge.getFrom().getId() + " → " +
                    edge.getTo().getId() + " | Current: " +
                    edge.getFlow() + " | Capacity: " +
                    edge.getCapacity() + " | Usage: " + usage + "%");
        }
        System.out.println("===============================");
    }
}