import java.io.File;

import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.Directed_Weighted_Graph;
import api.Directed_Weighted_Graph_Algorithms;
import api.Json_Handler;

/**
 * @author Yehiel Siri
 * @since  06/12/2021
 * 
 * This class is the main class for Ex2 - your implementation will be tested using this class.
 */
public class Ex2 {

	public static void main(String[] args) {
		try {
			try {
				File trace = new File("");
				runGUI(trace.getAbsolutePath() + "/data/" + args[0]);
			} catch (Exception e) {
				// TODO: handle exception in which there is any problem with the file name
				e.printStackTrace();
				System.out.println("A problem with the name file has found.");
				System.out.println("Check if file like that is realy exist!");
			}
		}catch (Exception e) {
			// TODO: handle exception in which there is nothing in input
			System.out.println("There is nothing for file name in input.");
			System.out.println("Please try again after inserting!");
		}
	}

	/**
	 * This static function will be used to test your implementation
	 * @param json_file - a json file (e.g., G1.json - G3.gson)
	 * @return
	 */
	public static DirectedWeightedGraph getGrapg(String json_file) {
		DirectedWeightedGraph ans = null;
		
//		My third implementation version - the simplest way:
		ans = new Directed_Weighted_Graph(json_file);
		
//		My second implementation version - a try to implement according to the prophesor interface meaning:
//		Directed_Weighted_Graph_Algorithms handler = new Directed_Weighted_Graph_Algorithms();
//		handler.load(json_file);
//		ans = handler.getGraph();
		
//		My first implementation version - a little complicated than the third one:
//		Json_Handler.JsonDeserializer(json_file, (Directed_Weighted_Graph)ans);
		return ans;
	}
	/**
	 * This static function will be used to test your implementation
	 * @param json_file - a json file (e.g., G1.json - G3.gson)
	 * @return
	 */
	public static DirectedWeightedGraphAlgorithms getGrapgAlgo(String json_file) {
		DirectedWeightedGraphAlgorithms ans = null;
		ans = new Directed_Weighted_Graph_Algorithms();
		ans.load(json_file);
		return ans;
	}
	/**
	 * This static function will run your GUI using the json file.
	 * @param json_file - a json file (e.g., G1.json - G3.gson)
	 *
	 */
	public static void runGUI(String json_file) {
		DirectedWeightedGraphAlgorithms alg = getGrapgAlgo(json_file);
		new window(alg);
	}
}