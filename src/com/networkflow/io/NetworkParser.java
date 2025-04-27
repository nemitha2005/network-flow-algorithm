package com.networkflow.io;

import com.networkflow.model.FlowNetwork;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class NetworkParser {
    public FlowNetwork parseFromFile(String filename) throws IOException {
        FlowNetwork network = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine();
            int numberOfNodes = Integer.parseInt(line.trim());

            network = new FlowNetwork(numberOfNodes);

            while ((line = reader.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                if (parts.length != 3) {
                    continue;
                }

                int from = Integer.parseInt(parts[0]);
                int to = Integer.parseInt(parts[1]);
                int capacity = Integer.parseInt(parts[2]);

                network.addEdge(from, to, capacity);
            }
        }

        return network;
    }
}