package network_persistence;

import java.io.IOException;

import org.junit.Test;

import edu.stanford.math.plex4.api.Plex4;
import edu.stanford.math.plex4.homology.barcodes.BarcodeCollection;
import edu.stanford.math.plex4.homology.chain_basis.Simplex;
import edu.stanford.math.plex4.homology.interfaces.AbstractPersistenceAlgorithm;
import edu.stanford.math.plex4.streams.impl.VietorisRipsStream;
import au.edu.rmit.javaplex.graph.filtration.WeightedGraphFiltrationFunctions;
import au.edu.rmit.javaplex.io.GraphReader;

public class NetworkScience {
	final static String filename = "/Users/jacobien/Git/PersistentHomology/NetworkPersistence/Networks/networkScience.csv";
	final static String sep = ",";
	final static Boolean hasHeader = true;
	final static Boolean directed = false;
	
	final static double maxWeight = 5;
	final static int maxDim = 2;
	
	@Test
	public void computePersistence2() throws IOException{
		System.out.println("Reading graph");
		double[][] A = GraphReader.getWeigthedAdjacencyMatrix(filename, sep, hasHeader, directed);
		BarcodeCollection<Double> intervals = NetworkPersistenceFunctions.computeIntervals(A, false, maxWeight, maxDim);
		System.out.println("Intervals: Decreasing");
		System.out.println(intervals);		
		System.out.println(intervals.getBettiNumbers());
	}
}
