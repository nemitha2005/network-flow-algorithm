package com.networkflow.io;

import com.networkflow.model.FlowNetwork;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class reads a flow network from a text file.
 */
public class NetworkParser {

    /**
     * Read file and makes the flow network.
     *
     * @param filename file to read
     * @return flow network from file
     * @throws IOException if file can't be read
     */
    public FlowNetwork parseFromFile(String filename) throws IOException {
        FlowNetwork network = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            // First line tells how many nodes
            String line = reader.readLine();
            int numberOfNodes = Integer.parseInt(line.trim());

            // Make the network with that many nodes
            network = new FlowNetwork(numberOfNodes);

            // Next lines are edges
            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                if (parts.length != 3) {
                    continue;
                }

                int from = Integer.parseInt(parts[0]);
                int to = Integer.parseInt(parts[1]);
                int capacity = Integer.parseInt(parts[2]);

                // add the edge
                network.addEdge(from, to, capacity);
            }
        }

        return network;
    }
}
