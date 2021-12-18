package api;

import java.util.HashMap;

/**
 * @author Yehiel Siri
 * @since 16/12/2021
 * 
 * A directed weighted graph node representation class-object.
 * Represents set of operations applicable on a node (vertex) in 
 * a (directional) weighted graph by implementing the NodeData interface.
 */
public class Node_Data implements NodeData {
	private Geo_Location location;
	private final int key;								//the node ID
	private transient double weight = Double.MAX_VALUE;
	private transient String info = "Unvisited";
	private transient int tag = -1;

	private HashMap<Integer,EdgeData> edges;
	private HashMap<Integer,Integer> neighbors;

	/**
	 * Equivalent to a default constructor.
	 * The ID parameter is must in Node building.
	 * 
	 * @param key - id
	 */
	public Node_Data(int key) {
		this.key = key;
		this.location = new Geo_Location();
		this.edges = new HashMap<>();
		this.neighbors = new HashMap<>();
	}

	/**
	 * Constructor
	 *
	 * @param key - id
	 * @param location
	 */
	public Node_Data(int key, Geo_Location location) {
		this.key = key;
		this.location = new Geo_Location(location);
		this.edges = new HashMap<>();
		this.neighbors = new HashMap<>();
	}

	/**
	 * Copy constructor
	 *
	 * @param other
	 */
	public Node_Data(Node_Data other) {
		this.key = other.getKey();
		this.location = new Geo_Location(other.getLocation());
		this.weight = other.getWeight();
		this.info = other.getInfo();
		this.tag = other.getTag();
		this.edges = other.getEdges();
		this.neighbors = other.getNeighbors();
	}

	@Override
	public int getKey() {
		return this.key;
	}

	@Override
	public GeoLocation getLocation() {
		return this.location;
	}

	@Override
	public void setLocation(GeoLocation p) {
		this.location = (Geo_Location)p;
	}

	@Override
	public double getWeight() {
		return this.weight;
	}

	@Override
	public void setWeight(double w) {
		this.weight = w;
	}

	@Override
	public String getInfo() {
		return this.info;
	}

	@Override
	public void setInfo(String s) {
		this.info = s;
	}

	@Override
	public int getTag() {
		return this.tag;
	}

	@Override
	public void setTag(int t) {
		this.tag = t;
	}

	public HashMap<Integer, EdgeData> getEdges() {
		return this.edges;
	}

	public HashMap<Integer, Integer> getNeighbors() {
		return this.neighbors;
	}

	@Override
	public String toString() {
		return "{Position:" + this.location + ", ID:" + this.key + ", Weight:" + this.weight + '}';
	}
	
	/**
	 * Equivalence checking function between this and other nodes:
	 * Two nodes are equal iff the all five are equal to their counterparts in 
	 * the other; location, id, weight, edges and neighbors.
	 * In fact, id=id is enough.
	 * 
	 * @return boolean
	 */
	public boolean isEqual(Node_Data other) {
//		return (this.location.isEqual((Geo_Location) other.getLocation()) 
//				&& this.key == other.getKey()) && (this.weight == other.getWeight()) 
//				&& this.edges.equals(other.getEdges()) && (this.neighbors.equals(other.getNeighbors()) );
		return this.key == other.getKey();
	}

}
