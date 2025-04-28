package com.networkflow.app;

import com.networkflow.algorithm.MaxFlowFinder;
import com.networkflow.io.NetworkParser;
import com.networkflow.model.FlowNetwork;
import com.networkflow.util.OutputFormatter;

import java.io.File;
import java.io.IOException;

/**
 * Main application class for network flow algorithm.
 */
public class Main {

    /**
     * Entry point for application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // Determine which input file to use
        String inputFile;

        // If no command line argument provided, use the default txt file
        if (args.length != 1) {
            System.out.println("No input file provided. Using default network.txt");

            inputFile = "resources/network.txt";
        } else {
            // Otherwise use provided filename
            inputFile = args[0];

            // If the name has no path, try to find it in the resources folder in root
            if (!inputFile.contains("/") && !inputFile.contains("\\")) {
                inputFile = "resources/" + inputFile;
            }
        }

        // Check if the file exists
        File file = new File(inputFile);
        if (!file.exists()) {
            System.err.println("Error: File not found: " + inputFile);
            System.out.println("Usage: java Main <input_file>");
            return;
        }

        try {
            // Parse network from input file
            System.out.println("Parsing network from file: " + inputFile);
            NetworkParser parser = new NetworkParser();
            FlowNetwork network = parser.parseFromFile(inputFile);

            // Display information about network
            OutputFormatter.printNetworkInfo(network);

            // Calculate maximum flow
            System.out.println("Calculating maximum flow...");
            System.out.println("========================================");
            System.out.println("Results:");
            System.out.println("----------------------------------------");

            MaxFlowFinder maxFlowFinder = new MaxFlowFinder(network);
            int maxFlow = maxFlowFinder.findMaxFlow();

            // Display results
            System.out.println("========================================");
            System.out.println("Maximum flow: " + maxFlow);
            System.out.println("========================================");
            System.out.println("Detailed execution log:");
            System.out.println("----------------------------------------");

            // Show all steps that performed
            for (String step : maxFlowFinder.getExplanationSteps()) {
                System.out.println(step);
            }

            // Display final state of network with flow values
            OutputFormatter.printFinalState(network);

        } catch (IOException e) {
            // Handle file reading errors
            System.err.println("Error reading input file: " + e.getMessage());
        }
    }
}