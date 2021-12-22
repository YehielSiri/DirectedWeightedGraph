package api;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Directed_Weighted_Graph_Algorithms implements DirectedWeightedGraphAlgorithms {
	private Directed_Weighted_Graph graph;

	@Override
	public void init(DirectedWeightedGraph g) {
		//		g = new Directed_Weighted_Graph();
		this.graph = (Directed_Weighted_Graph)g;
	}

	@Override
	public DirectedWeightedGraph getGraph() {
		return this.graph;
	}

	@Override
	public DirectedWeightedGraph copy() {
		return new Directed_Weighted_Graph(this.graph);
	}

	@Override
	public boolean isConnected() {
		Iterator<NodeData> it = graph.nodeIter();
		while (it.hasNext()) if(!BFS(it.next())) return false;
		return true;
	}

	@Override
	public double shortestPathDist(int src, int dest) {
		Dijkstra d = new Dijkstra(graph);
		return d.getShortestPathDist(src, dest);
	}

	@Override
	public List<NodeData> shortestPath(int src, int dest) {
		Dijkstra d = new Dijkstra(graph);
		return d.getShortestPath(src, dest);
	}

	/**
	 * Center is the closest vertex to each other vertices.
	 * Dijkstra will be useful to us.
	 * 
	 * @return center
	 */
	@Override
	public NodeData center() {
		if (!this.isConnected()) return null;
		return findCenter();
	}

	/**
	 * Play Dijkstra for each vertex and find the max distance from it.
	 * 
	 * @return center - the minimum one
	 */
	private NodeData findCenter() {
		double min = Integer.MAX_VALUE;
		NodeData ans = null;
		Dijkstra d = new Dijkstra(this.graph);
		for (NodeData n : this.graph.getNodes().values()) {
			double max = d.getLongestPath(n.getKey());
			if (max < min) {
				min = max;
				ans = n;
			}
		}
		return ans;
	}

	/**
	 * 
	 * 
	 * @param cities
	 * @return ans - List<NodeData>
	 */
	@Override
	public List<NodeData> tsp(List<NodeData> cities) {
		if (cities.size() == 0)
			return null;
		List<NodeData> ans = new LinkedList<>();
		List<NodeData> temp = new ArrayList<>(cities);
		NodeData p = temp.remove(0);
		ans.add(p);
		while (temp.size() >= 1) {
			int key = p.getKey();
			Dijkstra d = new Dijkstra(this.graph, key);
			HashMap<Integer, Double> dist = d.getDists();
			NodeData t = null;
			double min = (double) Integer.MAX_VALUE;
			for (NodeData n : temp) {
				if (dist.get(n.getKey()) < min) {
					min = dist.get(n.getKey());
					t = n;
				}
			}
			if (t == null)
				return null;
			List<NodeData> path = new LinkedList<>();
			HashMap<Integer, NodeData> prev = d.getPrevs();
			NodeData c = t;
			if (prev.get(t.getKey()) != null || t.getKey() == key)
				while (prev.get(t.getKey()) != null) {
					path.add(0, t);
					t = prev.get(t.getKey());
				}
			p = c;
			temp.remove(c);
			ans.addAll(path);
		}
		return ans;
	}

	@Override
	public boolean save(String file) {
		return Json_Handler.JsonSerializer(file, this.graph);
	}

	@Override
	public boolean load(String file) {
		return Json_Handler.JsonDeserializer(file, this.graph);
	}

	@Override
	public String toString() {
		return this.graph.toString();
	}

	public Boolean BFS(NodeData vertex){
		long startTime = System.currentTimeMillis();
		Queue<NodeData> queue = new LinkedList<NodeData>();
		HashSet<Integer> closedList = new HashSet<>();
		Iterator<EdgeData> it;

		queue.add(vertex);
		closedList.add(vertex.getKey());

		while (!(queue.isEmpty())) {
			NodeData temp = queue.poll();
			it = graph.edgeIter(temp.getKey());
			while (it.hasNext()) {
				NodeData dest = graph.getNode(it.next().getDest());
				if( !(closedList.contains(dest.getKey())) ) {
					queue.add(dest);
					closedList.add(dest.getKey());
				}
			}
		}
		return closedList.size() == graph.nodeSize();
	}

}
