package network_persistence;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import edu.stanford.math.plex4.api.Plex4;
import edu.stanford.math.plex4.homology.barcodes.BarcodeCollection;
import edu.stanford.math.plex4.homology.chain_basis.Simplex;
import edu.stanford.math.plex4.homology.interfaces.AbstractPersistenceAlgorithm;
import edu.stanford.math.plex4.streams.impl.VietorisRipsStream;
import au.edu.rmit.javaplex.graph.filtration.WeightedGraphFiltrationFunctions;
import au.edu.rmit.javaplex.graph.io.GraphReader;

public class HighEnergyPhysics {
	final static String filename = "/Users/jacobien/Git/PersistentHomology/NetworkPersistence/Networks/highEnergyPhysics.csv";
	final static String sep = ",";
	final static Boolean hasHeader = false;
	final static Boolean directed = false;
	
	final static double maxWeight = 5;
	final static int maxDim = 2;
	
	@Test
	public void computePersistence() throws IOException{
		System.out.println("Reading graph");
		double[][] A = GraphReader.getWeigthedAdjacencyMatrix(filename, sep, hasHeader, directed);
		int n = A.length;
		int m = 0;
		for(int i=0; i <n-1; i++)
			for(int j=i+1; j < n; j++){
				if(A[i][j] > 0)
					m += 1;
			}
		System.out.println(String.format("Graph with %s nodes and %s edges", n,m));
		BarcodeCollection<Double> intervals = NetworkPersistenceFunctions.computeIntervals(A, false, maxWeight, maxDim);
		System.out.println("Intervals: Decreasing");
		System.out.println(intervals);		
		System.out.println(intervals.getBettiNumbers());
	}
}
