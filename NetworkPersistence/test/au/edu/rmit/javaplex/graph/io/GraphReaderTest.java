package au.edu.rmit.javaplex.graph.io;	

import java.io.IOException;

import org.junit.Test;

import edu.stanford.math.plex4.graph.UndirectedWeightedListGraph;
import static org.junit.Assert.assertEquals;

public class GraphReaderTest {

	final static String filename = "/Users/jacobien/Dropbox/PhD/Data/netscience/largestConnectedComp[edges].csv";
	final static String sep = ",";
	final static Boolean hasHeader = true;
	final static Boolean directed = false;
	
	@Test
	public void readEdgeFile() throws IOException{
		double[][] A = GraphReader.getWeigthedAdjacencyMatrix(filename, sep, hasHeader, directed);
		
		int n = A.length;
		assertEquals(379, n);
		
		int m = 0;
		for(int i=0; i <n-1; i++)
			for(int j=i+1; j < n; j++){
				if(A[i][j] > 0)
					m += 1;
			}
		
		assertEquals(914, m);
		System.out.println(String.format("Graph with %s nodes and %s edges", n,m));
	}
	
	@Test
	public void readEdgeFileAsGraph() throws IOException{
		UndirectedWeightedListGraph graph = GraphReader.getUndirectedWeightedGraph(filename, sep, hasHeader);
		
		int n = graph.getNumVertices();
		assertEquals(379, n);
		
		int m = graph.getNumEdges();
		assertEquals(914, m);
		System.out.println(String.format("Graph with %s nodes and %s edges", n,m));
	}
	
}
