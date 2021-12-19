package api;

//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;

import com.google.gson.*;

import java.io.File;
import java.io.FileReader;

/**
 * @author Yehiel Siri
 * @since  06/12/2021
 * 
 * A Json type files handle class
 */


public class Json_Handler /*implements JsonHandler*/ {

	/**
	 * JsonDeserializer(json_file, graph) - extracts the graph details from Json file and load to 'graph'
	 * 
	 * @param json_file - file name
	 * @param g			- a directed weighted graph
	 * @return boolean	- true <-> file has deserialized
	 */
	public static boolean JsonDeserializer(String json_file, Directed_Weighted_Graph graph) {
		File jsonFile = new File(json_file);

		JsonElement fileElement;
		JsonObject fileObject;
		JsonParser parser = new JsonParser();

		JsonArray nodes;
		JsonArray edges;

		try {
			fileElement = parser.parse(new FileReader(jsonFile));
			fileObject = fileElement.getAsJsonObject();

			//System.out.println("Deserializing graph date from Json file!");
			//System.out.println("Nodes:");
			nodes = fileObject.get("Nodes").getAsJsonArray();
			for (JsonElement nodeElement : nodes) {
				JsonObject NodeObject = nodeElement.getAsJsonObject();

				//Extract data:
				String[] position = NodeObject.get("pos").getAsString().split(",");
				//				String[] xyz = pos.split(",");
				//				double x = Double.parseDouble(xyz[0]);
				//				double y = Double.parseDouble(xyz[1]);
				//				double z = Double.parseDouble(xyz[2]);
				double x = Double.parseDouble(position[0]);
				double y = Double.parseDouble(position[1]);
				double z = Double.parseDouble(position[2]);

				Geo_Location loc = new Geo_Location(x, y, z);

				//				System.out.println("Position: " + pos);
				int id = NodeObject.get("id").getAsInt();
				//              System.out.println("ID: " + id);
				Node_Data n = new Node_Data(id, loc);
				graph.addNode(n);
			}

			//			System.out.println();
			//			System.out.println("Edges:");
			edges = fileObject.get("Edges").getAsJsonArray();
			for (JsonElement edgeElement : edges) {
				JsonObject EdgeObject = edgeElement.getAsJsonObject();

				//Extract data:
				int source = EdgeObject.get("src").getAsInt();
				//System.out.println("Source: " + source);
				double weight = EdgeObject.get("w").getAsDouble();
				//System.out.println("Weight: " + weight);
				int destination = EdgeObject.get("dest").getAsInt();
				//System.out.println("Destination: " + destination);
				graph.connect(source, destination, weight);
			}


		} catch (Exception e) {
			// TODO: file has not found
			e.printStackTrace();
			return false;
		}

		return true;
	}

	/**
	 * JsonDeserializer(json_file, graph) - extracts the graph details from Json file and load to 'graph'
	 * 
	 * @param json_file - file name
	 * @param graph		- a directed weighted graph
	 * @return boolean	- true <-> file has deserialized
	 */
	public static boolean JsonSerializer(String json_file, DirectedWeightedGraph graph) {
		File jsonFile = new File(json_file);

		JsonElement fileElement;
		JsonObject fileObject;
		JsonParser parser = new JsonParser();

		JsonArray nodes;
		JsonArray edges;

		try {
			fileElement = parser.parse(new FileReader(jsonFile));
			fileObject = fileElement.getAsJsonObject();

			//System.out.println("Deserializing graph date from Json file!");
			//System.out.println("Nodes:");
			nodes = fileObject.get("Nodes").getAsJsonArray();
			for (JsonElement nodeElement : nodes) {
				JsonObject NodeObject = nodeElement.getAsJsonObject();

				//Extract data:
				String[] position = NodeObject.get("pos").getAsString().split(",");
				//				String[] xyz = pos.split(",");
				//				double x = Double.parseDouble(xyz[0]);
				//				double y = Double.parseDouble(xyz[1]);
				//				double z = Double.parseDouble(xyz[2]);
				double x = Double.parseDouble(position[0]);
				double y = Double.parseDouble(position[1]);
				double z = Double.parseDouble(position[2]);

				Geo_Location loc = new Geo_Location(x, y, z);

				//				System.out.println("Position: " + pos);
				int id = NodeObject.get("id").getAsInt();
				//              System.out.println("ID: " + id);
				Node_Data n = new Node_Data(id, loc);
				graph.addNode(n);
			}

			//			System.out.println();
			//			System.out.println("Edges:");
			edges = fileObject.get("Edges").getAsJsonArray();
			for (JsonElement edgeElement : edges) {
				JsonObject EdgeObject = edgeElement.getAsJsonObject();

				//Extract data:
				int source = EdgeObject.get("src").getAsInt();
				//System.out.println("Source: " + source);
				double weight = EdgeObject.get("w").getAsDouble();
				//System.out.println("Weight: " + weight);
				int destination = EdgeObject.get("dest").getAsInt();
				//System.out.println("Destination: " + destination);
				graph.connect(source, destination, weight);
			}


		} catch (Exception e) {
			// TODO: file has not found
			e.printStackTrace();
			return false;
		}

		return true;
	}


}
