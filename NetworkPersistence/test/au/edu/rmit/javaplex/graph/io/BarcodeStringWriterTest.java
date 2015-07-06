package au.edu.rmit.javaplex.graph.io;

import java.io.IOException;

import org.junit.Test;

import au.edu.rmit.javaplex.graph.filtration.WeightedGraphFiltration;
import au.edu.rmit.javaplex.io.BarcodeStringWriter;
import edu.stanford.math.plex4.api.Plex4;
import edu.stanford.math.plex4.homology.barcodes.BarcodeCollection;
import edu.stanford.math.plex4.homology.chain_basis.Simplex;
import edu.stanford.math.plex4.homology.interfaces.AbstractPersistenceAlgorithm;
import edu.stanford.math.plex4.streams.impl.VietorisRipsStream;

public class BarcodeStringWriterTest {
	// Adjacency matrix of a graph
	double[][] adjacencyMatrix = new double[][] {{ 0, 2.1,   0, 2.1,   1,  0}, 
												{2.1,   0, 2.1,   0,   1,  0}, 
												{  0, 2.1,   0,   0, 0.7,  1}, 
												{2.1,   0,   0,   0,   0,  0}, 
												{  1,   1, 0.7,   0,   0,  1}, 
												{  0,   0,   1,   0,   1,  0}};
	
	
	
	
	
	int d = 2;
	double maxWeight = 5d;
	
	
	@Test 
	/**
	 * Compute the persistent homology of the Vietoris-Rips (clique complex) associated with 
	 * a graph filtration on increasing edge weight and write intervals to file.
	 * 
	 */
	public void exampleExistingCodeIncreasing() throws IOException{
		final VietorisRipsStream<Integer> stream = WeightedGraphFiltration.getVietorisRipsStreamAscending(adjacencyMatrix, maxWeight, d);
		final AbstractPersistenceAlgorithm<Simplex> algorithm = Plex4.getDefaultSimplicialAlgorithm(d);
		final BarcodeCollection<Double> intervals = algorithm.computeIntervals(stream);
		System.out.println(intervals);
		System.out.println(intervals.getBettiNumbers());
		BarcodeStringWriter.writeToFile(intervals, 0, "5", "0", "/home/jacobien/Desktop/test0.txt");
		BarcodeStringWriter.writeToFile(intervals, 1, "5", "0", "/home/jacobien/Desktop/test1.txt");
		BarcodeStringWriter.writeToFile(intervals, 2, "5", "0", "/home/jacobien/Desktop/test2.txt");
	}
}
