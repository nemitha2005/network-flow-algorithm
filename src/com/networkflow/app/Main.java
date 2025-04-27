package com.networkflow.app;

import com.networkflow.algorithm.MaxFlowFinder;
import com.networkflow.io.NetworkParser;
import com.networkflow.model.FlowNetwork;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {

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
            NetworkParser parser = new NetworkParser();
            FlowNetwork network = parser.parseFromFile(inputFile);

            MaxFlowFinder maxFlowFinder = new MaxFlowFinder(network);
            int maxFlow = maxFlowFinder.findMaxFlow();

            System.out.println("Maximum flow: " + maxFlow);
            System.out.println("\nExplanation:");

            List<String> steps = maxFlowFinder.getExplanationSteps();
            for (String step : steps) {
                System.out.println(step);
            }

        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
        }
    }
}