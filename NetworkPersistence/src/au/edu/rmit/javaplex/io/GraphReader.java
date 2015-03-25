package au.edu.rmit.javaplex.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;



import edu.stanford.math.plex4.graph.UndirectedWeightedListGraph;

public class GraphReader {

	public static double[][] getWeigthedAdjacencyMatrix(String edgeFile, String sep, Boolean header, Boolean directed) throws IOException{
		BufferedReader edgeReader = new BufferedReader(new FileReader(new File(edgeFile)));
		Map<String, Integer> nodes = new HashMap<String, Integer>(); 
		
		String line;
		if(header)
			edgeReader.readLine();
		Integer index = 0;
		while((line = edgeReader.readLine()) != null){
			final String[] lineValues = line.split(sep);
			final String source = lineValues[0];
			final String target = lineValues[1];
			if(!nodes.containsKey(source)){
				nodes.put(source, index);
				index += 1;
			}
			if(!nodes.containsKey(target)){
				nodes.put(target, index);
				index += 1;
			}
		}
		
		int n = nodes.size();
		double[][] adjacencyMatrix = new double[n][n];
		
		edgeReader = new BufferedReader(new FileReader(new File(edgeFile)));
		if(header)
			edgeReader.readLine();
		
		while((line = edgeReader.readLine()) != null){
			final String[] lineValues = line.split(sep);
			adjacencyMatrix[nodes.get(lineValues[0])][nodes.get(lineValues[1])] = Double.valueOf(lineValues[2]);
			if(!directed)
				adjacencyMatrix[nodes.get(lineValues[1])][nodes.get(lineValues[0])] = Double.valueOf(lineValues[2]);
		}
		return(adjacencyMatrix);
	}
	
	public static UndirectedWeightedListGraph getUndirectedWeightedGraph(String edgeFile, String sep, Boolean header) throws IOException{
		BufferedReader edgeReader = new BufferedReader(new FileReader(new File(edgeFile)));
		Map<String, Integer> nodes = new HashMap<String, Integer>(); 
		
		String line;
		if(header)
			edgeReader.readLine();
		Integer index = 0;
		while((line = edgeReader.readLine()) != null){
			final String[] lineValues = line.split(sep);
			final String source = lineValues[0];
			final String target = lineValues[1];
			if(!nodes.containsKey(source)){
				nodes.put(source, index);
				index += 1;
			}
			if(!nodes.containsKey(target)){
				nodes.put(target, index);
				index += 1;
			}
		}
		
		int n = nodes.size();
		UndirectedWeightedListGraph graph = new UndirectedWeightedListGraph(n);
		edgeReader = new BufferedReader(new FileReader(new File(edgeFile)));
		if(header)
			edgeReader.readLine();
		
		while((line = edgeReader.readLine()) != null){
			final String[] lineValues = line.split(sep);
			graph.addEdge(nodes.get(lineValues[0]), nodes.get(lineValues[1]), Double.valueOf(lineValues[2]));
		}
		return(graph);
	}
	
	
}
