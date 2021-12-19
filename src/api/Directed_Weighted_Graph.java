package api;

import java.util.HashMap;
import java.util.Iterator;

/**
 * @author Yehiel Siri
 * @since 06/12/2021
 * 
 * A directed weighted graph representation class-object by implementing DirectedWeightedGraph interface.
 * This interface should support a large number of nodes,and hence should be based on an efficient 
 * compact representation (not on a n*n one).
 * So the implement based on HushMap (time complexity - O(1) & space complexity - dynamically).
 */
public class Directed_Weighted_Graph implements DirectedWeightedGraph {
	private int MC;
	private HashMap<Integer, Node_Data> nodes;
	private HashMap<String, Edge_Data> edges;
	
	private int nodesCounter;
	private int edgesCounter;

	public Directed_Weighted_Graph(String json_file) {
		Json_Handler.JsonDeserializer(json_file, this);
	}

	@Override
	public NodeData getNode(int key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EdgeData getEdge(int src, int dest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addNode(NodeData n) {
		// TODO Auto-generated method stub

	}

	@Override
	public void connect(int src, int dest, double w) {
		// TODO Auto-generated method stub

	}

	@Override
	public Iterator<NodeData> nodeIter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<EdgeData> edgeIter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<EdgeData> edgeIter(int node_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NodeData removeNode(int key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EdgeData removeEdge(int src, int dest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int nodeSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int edgeSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMC() {
		// TODO Auto-generated method stub
		return 0;
	}

}
