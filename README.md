# DirectedWeightedGraph
This repository implements directed weighted graph and its GUI as the second assignment of Object Oriented Programming curse in Ariel University.

# Installation:

Clone that project to your workspace directory with the commend:
git clone https://github.com/YehielSiri/DirectedWeightedGraph.git
Open your IDE and make sure you see the project.

# How to run:

From the terminal:
* 'cd' into the folder where you have downloaded the project
* run the command line below:
java -jar Ex2.jar JSON_NAME.json
'JSON_NAME' can be any json file which represent a graph and exist in the project.
for example: java -jar Ex2.jar G1.json
Be Careful: Running the project with the 1000Nodes.json or the 10000Nodes.json on a simple machine is not recommended at all!


# Data Stracture:

# Node class:

This object represents a vertex with the follow features:

* Key - the ID of the current vertex.
* Tag - marker passing through the current vertex.
* Weight - the cost for going through the current vertex.
* Location - the geographic locatiobn of the current node on the space - (X, Y, Z).
* Info-String

# Edge class:

This object represents an edge with the follow features:

* Src - the source node's ID.
* Dest - the destination node's ID.
* Tag - marker passing through the current edge.
* Weight - the cost for going through the current edge.
* Info-String

# Geo_Location class:

This object represents a geographic locatiobn in the space:

* x - position in dimension X.
* y - position in dimension Y.
* z - position in dimension Z.

# DirectedWeightedGraph class:

A directed weighted graph representation class-object by implementing DirectedWeightedGraph interface. This interface should support a large number of nodes, and hence should be based on an efficient compact representation (not on a n*n one). So the implement based on HushMap (time complexity - O(1) & space complexity - dynamically).

* Nodes - is a collection of HashMap of nodes in the graph.
* Edges - is a collection of HashMap of edges in the graph.
* NodesCounter - counting the vertices in the graph.
* EdgesCounter - counting the edges in the graph.
* MC - counting the changes which be made on the graph.


# DirectedWeightedGraphAlgorithms class:

Class for solving an algorithmic problems as, Shortest path, Is a connective graph, What is the center, TSP (include read graph from a json file and save to).

Feature:

* directed_weighted_graph - this is the graph that we perform the algorithms on.

Algotithms:

 * ShortestPath - Finding the shortest path in a directed weighted graph by Dijkstra algorithm.
 * Center - Finding the center of a directed weighted graph by Dijkstra algorithm.
 * IsConnected - Checking whether a directed weighted graph is conected by BFS algorithm.
 * TSP - Solving the Travelling Salesman Problem on a directed weighted graph by Dijkstra algorithm.
