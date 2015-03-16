package network_persistence;

import java.io.IOException;

import org.junit.Test;

import edu.stanford.math.plex4.api.Plex4;
import edu.stanford.math.plex4.homology.barcodes.BarcodeCollection;
import edu.stanford.math.plex4.homology.chain_basis.Simplex;
import edu.stanford.math.plex4.homology.interfaces.AbstractPersistenceAlgorithm;
import edu.stanford.math.plex4.streams.impl.VietorisRipsStream;
import au.edu.rmit.javaplex.graph.filtration.WeightedGraphFiltrationFunctions;
import au.edu.rmit.javaplex.graph.io.GraphReader;

public class NetworkScience {
	final static String filename = "/Users/jacobien/Dropbox/PhD/Data/netscience/largestConnectedComp[edges].csv";
	final static String sep = ",";
	final static Boolean hasHeader = true;
	final static Boolean directed = false;
	
	final static double maxWeight = 5;
	final static int maxDim = 2;
	
	@Test
	public void computePersistence() throws IOException{
		System.out.println("Reading graph");
		double[][] A = GraphReader.getWeigthedAdjacencyMatrix(filename, sep, hasHeader, directed);
		System.out.println("Creating stream");
		final VietorisRipsStream<Integer> stream = WeightedGraphFiltrationFunctions.getVietorisRipsStreamDescending(A, maxWeight, maxDim);
		System.out.println("Obtaining algorithm");
		final AbstractPersistenceAlgorithm<Simplex> algorithm = Plex4.getDefaultSimplicialAlgorithm(maxDim);
		System.out.println("Computing intervals");
		BarcodeCollection<Double> intervals = algorithm.computeIntervals(stream);
		System.out.println("Converting intervals to descending intervals");
		intervals = WeightedGraphFiltrationFunctions.convertToDescendingIntervals(intervals);
		System.out.println("Intervals: Decreasing");
		System.out.println(intervals);		
		System.out.println(intervals.getBettiNumbers());
	}
}
