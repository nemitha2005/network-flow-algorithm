package com.networkflow.app;

import com.networkflow.algorithm.MaxFlowFinder;
import com.networkflow.io.NetworkParser;
import com.networkflow.model.FlowNetwork;
import com.networkflow.util.OutputFormatter;

import java.io.File;
import java.io.IOException;

/**
 * Main application class for the network flow algorithm
 */
public class Main {

    /**
     * Entry point for the application
     *
     * @param
     */
    public static void main(String[] args) {
        String inputFile;

        if (args.length != 1) {
            System.out.println("No input file provided. Using default network.txt");
            inputFile = "resources/network.txt";
        } else {
            inputFile = args[0];
            if (!inputFile.contains("/") && !inputFile.contains("\\")) {
                inputFile = "resources/" + inputFile;
            }
        }

        File file = new File(inputFile);
        if (!file.exists()) {
            System.err.println("Error: File not found: " + inputFile);
            System.out.println("Usage: java Main <input_file>");
            return;
        }

        try {
            System.out.println("Parsing network from file: " + inputFile);
            NetworkParser parser = new NetworkParser();
            FlowNetwork network = parser.parseFromFile(inputFile);

            OutputFormatter.printNetworkInfo(network);

            System.out.println("Calculating maximum flow...");
            System.out.println("Results:");

            MaxFlowFinder maxFlowFinder = new MaxFlowFinder(network);
            int maxFlow = maxFlowFinder.findMaxFlow();

            System.out.println("Maximum flow: " + maxFlow);
            System.out.println("Detailed execution log:");

            for (String step : maxFlowFinder.getExplanationSteps()) {
                System.out.println(step);
            }

            OutputFormatter.printFinalState(network);

        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
        }
    }
}