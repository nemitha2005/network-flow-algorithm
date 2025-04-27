# Network Flow Algorithm

This project implements the Ford-Fulkerson method (specifically the Edmonds-Karp algorithm) for finding the maximum flow in a network, as per the requirements of the 5SENG003W Algorithms coursework.

## Project Structure

The project is organized into the following packages:

- `com.networkflow.model`: Contains the data structures for representing the flow network
  - `Node.java`: Represents a node in the network
  - `Edge.java`: Represents a directed edge with capacity and flow
  - `FlowNetwork.java`: Represents the entire flow network
  
- `com.networkflow.io`: Contains the parsing functionality
  - `NetworkParser.java`: Parses network definitions from input files
  
- `com.networkflow.algorithm`: Contains the maximum flow algorithm
  - `MaxFlowFinder.java`: Implements the Edmonds-Karp algorithm
  
- `com.networkflow.app`: Contains the main application
  - `Main.java`: Entry point for the application

## Algorithm

This implementation uses the Edmonds-Karp algorithm, which is a specific implementation of the Ford-Fulkerson method. It uses Breadth-First Search (BFS) to find augmenting paths from source to target, ensuring that the shortest available path is always chosen. This approach has a time complexity of O(V × E²), where V is the number of vertices and E is the number of edges.

## Input Format

The input files should have the following format:
- The first line contains the number of nodes n.
- Nodes are numbered from 0 to n-1, where node 0 is the source and node n-1 is the target.
- Each subsequent line represents an edge in the format: `from to capacity`
  - `from`: The source node ID
  - `to`: The target node ID
  - `capacity`: The maximum flow capacity of the edge

Example:
```
4
0 1 6
0 2 4
1 2 2
1 3 3
2 3 5
```

This represents a network with 4 nodes (0, 1, 2, 3), where node 0 is the source and node 3 is the target. There are 5 edges as described on each line.

## Usage

To run the application:

1. Ensure you have JDK installed
2. Compile the project
3. Run with an input file parameter:

```
java com.networkflow.app.Main <input_file>
```

If no input file is specified, the application will look for `resources/example_network.txt` by default.

## Output

The application outputs:
- The maximum flow value
- A detailed explanation of how the solution was obtained, including all augmenting paths found and their respective flows
