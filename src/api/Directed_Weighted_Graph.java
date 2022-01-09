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
	private HashMap<Integer, NodeData> nodes;
	private HashMap<String, EdgeData> edges;

	private int nodesCounter;
	private int edgesCounter;

	/**
	 * Equivalent to a default constructor.
	 */
	public Directed_Weighted_Graph() {
		this.MC = 0;
		this.nodes = new HashMap<>();
		this.edges = new HashMap<>();

		this.nodesCounter = this.edgesCounter = 0;
	}

	/**
	 * Constructor - building by reading the parameters from a Json file
	 *
	 * @param Json file name
	 */
	public Directed_Weighted_Graph(String json_file) {
		this.MC = 0;
		this.nodes = new HashMap<>();
		this.edges = new HashMap<>();

		this.nodesCounter = this.edgesCounter = 0;

		Json_Handler.JsonDeserializer(json_file, this);
	}

	/**
	 * Copy constructor
	 *
	 * @param other
	 */
	public Directed_Weighted_Graph(Directed_Weighted_Graph other) {
		this.MC = other.getMC();
//		this.nodes = new HashMap<>(other.getNodes());
		this.nodes = cloneMap(other.getNodes());
//		this.edges = new HashMap<>(other.getEdges());
		this.edges = cloneMap(other.getEdges());
		this.nodesCounter = other.getNodesCounter();
		this.edgesCounter = other.getEdgesCounter();
	}

	/**
	 * An auxiliary function - makes a map<T, O> clone
	 * and initializing the two table fields
	 *
	 * @param original - type of HashMap<T, O> (T - Type, O - Object)
	 * @return copy
	 */
	private <T, O> HashMap<T, O> cloneMap(HashMap<T, O> original) {
		HashMap<T, O> copy = new HashMap<>();
		copy.putAll(original);

		System.out.println("A clone has made!");
		System.out.println("original:");
		System.out.println(original);
		System.out.println("copy:");
		System.out.println(copy);

		return copy;
	}

	@Override
	public NodeData getNode(int key) {
		return this.getNodes().get(key);
	}

	@Override
	public EdgeData getEdge(int src, int dest) {
		return this.getEdges().get(src + "-" + dest);
	}

	@Override
	public void addNode(NodeData n) {
		if (this.getNodes().containsKey(n.getKey())) return;

		this.getNodes().put(n.getKey(), (Node_Data)n);
		this.MC++;
		this.nodesCounter++;
	}

	@Override
	public void connect(int src, int dest, double w) {
		Edge_Data e = new Edge_Data(src, dest, w);
		if (this.getEdges().containsKey(src + "-" + dest)) {
			//This edge is already exist, so override it by the new one:
			this.getEdges().replace(src + "-" + dest, e);
			( (Node_Data)this.getNode(src) ).getEdges().replace(dest, e);
		}
		else {
			this.getEdges().put(src + "-" + dest, e);
			( (Node_Data)this.getNode(src) ).getEdges().put(dest, e);
			( (Node_Data)this.getNode(src) ).getNeighbors().put(src, dest);
		}
	}

	/**
	 * Iterator 
	 * With the usage of the following methods:
	 * 1. next()
	 * 2. hasNext()
	 * 3. remove()
	 * 
	 * @return - an iterator for all the nodes in the graph
	 * @throws RuntimeException - when the graph has changed during the iterator operation
	 */	
	@Override
	public Iterator<NodeData> nodeIter() {
		return new Iterator<NodeData>() {
			private final Iterator<NodeData> it = nodes.values().iterator();
			private final int verify = MC;

			@Override
			public NodeData next() {
				if (verify != MC)	throw new RuntimeException("Error! Graph has changed during iterator operation.");
				return it.next();
			}

			@Override
			public boolean hasNext() {
				if (verify != MC)	throw new RuntimeException("Error! Graph has changed during iterator operation.");
				return it.hasNext();
			}

			NodeData n = null;
			@Override
			public void remove() {
				if (verify != MC)	throw new RuntimeException("Error! Graph has changed during iterator operation.");

				for (EdgeData e : ((Node_Data) n).getEdges().values()) {
					((Node_Data) nodes.get(e.getDest())).getNeighbors().remove(n.getKey());
					edges.remove(n.getKey() + "-" + e.getDest());
				}
				//remove edges from Nodes that they have edges to this Node
				for (Integer src : ((Node_Data) n).getNeighbors().keySet()) {
					((Node_Data) nodes.get(src)).getEdges().remove(n.getKey());
					edges.remove(src + "-" + n.getKey());
				}
				it.remove();
			}
		};
	}

	/**
	 * Iterator 
	 * With the usage of the following methods:
	 * 1. next()
	 * 2. hasNext()
	 * 3. remove()
	 * 
	 * @return - an iterator for all the edges in the graph
	 * @throws RuntimeException - when the graph has changed during the iterator operation
	 */	
	@Override
	public Iterator<EdgeData> edgeIter() {
		return new Iterator<EdgeData>() {
			private final Iterator<EdgeData> it = edges.values().iterator();
			private final int verify = MC;

			@Override
			public EdgeData next() {
				if (verify != MC)	throw new RuntimeException("Error! Graph has changed during iterator operation.");
				return it.next();
			}

			@Override
			public boolean hasNext() {
				if (verify != MC)	throw new RuntimeException("Error! Graph has changed during iterator operation.");
				return it.hasNext();
			}

			Edge_Data e = null;
			@Override
			public void remove() {
				if (verify != MC)	throw new RuntimeException("Error! Graph has changed during iterator operation.");

				((Node_Data) nodes.get(e.getSrc())).getEdges().remove(e.getDest());
				((Node_Data) nodes.get(e.getDest())).getNeighbors().remove(e.getSrc());
				it.remove();
			}

		};
	}

	/**
	 * Iterator 
	 * With the usage of the following methods:
	 * 1. next()
	 * 2. hasNext()
	 * 3. remove()
	 * 
	 * @param node_id - an Node_Data's key
	 * @return - an iterator for all the edges coming out of a certain vertex in the graph
	 * @throws RuntimeException - when the graph has changed during the iterator operation
	 */	
	@Override
	public Iterator<EdgeData> edgeIter(int node_id) {
		return new Iterator<EdgeData>() {
			@SuppressWarnings("unlikely-arg-type")
			private final Iterator<EdgeData> it = ( (Node_Data)(nodes.get(node_id)) ).getEdges().values().iterator();
			private final int verify = MC;

			@Override
			public EdgeData next() {
				if (verify != MC)	throw new RuntimeException("Error! Graph has changed during iterator operation.");
				return it.next();
			}
			
			@Override
			public boolean hasNext() {
				if (verify != MC)	throw new RuntimeException("Error! Graph has changed during iterator operation.");
				return it.hasNext();
			}

			Edge_Data e = null;
			@Override
			public void remove() {
				if (verify != MC)	throw new RuntimeException("Error! Graph has changed during iterator operation.");

				
				((Node_Data) nodes.get(e.getDest())).getNeighbors().remove(e.getSrc());
				edges.remove(e.toString());
				it.remove();
			}
			
		};
	}

	/**
	 * Remove vertex function
	 * Required to remove all edges coming out of this vertex as well as those entering it
	 *
	 * @param key
	 */
	@Override
	public NodeData removeNode(int key) {
		this.MC++;
		Node_Data ver = (Node_Data) this.getNode(key);		//The vertex to remove
		//Remove edges coming out
		for (EdgeData e: ver.getEdges().values()) {
			int dest = e.getDest();
			Node_Data neighbor = (Node_Data) this.getNode(dest);

			neighbor.getNeighbors().remove(key);
			this.getEdges().remove(key+"-"+dest);
		}
		//Remove edges entering
		for (Integer src: ver.getNeighbors().keySet()){
			Node_Data srcNeighbor = (Node_Data) this.getNode(src);

			srcNeighbor.getEdges().remove(key);
			this.getEdges().remove(src+"_"+key);
		}
		return this.getNodes().remove(key);
	}

	/**
	 * Remove edge function
	 *
	 * @param key
	 */
	@Override
	public EdgeData removeEdge(int src, int dest) {
		// TODO remove from srcNode.edges, from destNode.neighbors and from graph.edges
		this.MC++;
		Node_Data var = (Node_Data)this.getNode(src);
		var.getEdges().remove(dest);
		var.getNeighbors().remove(src);
		return this.getEdges().remove(src + "-" + dest);
	}

	@Override
	public int nodeSize() {
		return this.nodes.size();
	}

	@Override
	public int edgeSize() {
		return this.edges.size();
	}

	@Override
	public int getMC() {
		return this.MC;
	}

	public int getNodesCounter() {
		return this.nodesCounter;
	}

	public int getEdgesCounter() {
		return this.edgesCounter;
	}

	public HashMap<Integer,NodeData> getNodes() {
		return this.nodes;
	}

	public HashMap<String, EdgeData> getEdges() {
		return this.edges;
	}

	@Override
	public String toString() {
		return "G = < V={" + this.getNodes().values() + "}, E={" + this.getEdges().values() + "} > MC = " + this.getMC();
	}
}
