package network_persistence_clique_cpx;

import java.io.IOException;
import java.util.Set;

import org.junit.Test;

import edu.stanford.math.plex4.api.Plex4;
import edu.stanford.math.plex4.graph.UndirectedWeightedListGraph;
import edu.stanford.math.plex4.homology.barcodes.BarcodeCollection;
import edu.stanford.math.plex4.homology.chain_basis.Simplex;
import edu.stanford.math.plex4.homology.filtration.FiltrationConverter;
import edu.stanford.math.plex4.homology.interfaces.AbstractPersistenceAlgorithm;
import au.edu.rmit.javaplex.homology.filtration.DecreasingMapConverter;
import au.edu.rmit.javaplex.io.BarcodeStringWriter;
import au.edu.rmit.javaplex.io.GraphReader;
import au.edu.rmit.javaplex.plex4.streams.impl.CliqueComplexWeightedStream;

public class AstroPhysics {
	final static String basepathUbuntu = "/home/jacobien/git/PersistentHomology/NetworkPersistence/";
	final static String basepathMac = "/Users/jacobien/Git/PersistentHomology/NetworkPersistence/";
	final static String basepath = basepathUbuntu;
	final static String filename = basepath + "Networks/astroPhysics.csv";
	final static String sep = ",";
	final static Boolean hasHeader = false;
	final static Boolean directed = false;
	
	double maxWeight = 0;
	final static int maxDim = 3;
	
	@Test
	public void computePersistence() throws IOException{
		System.out.println("Reading graph");
		UndirectedWeightedListGraph graph = GraphReader.getUndirectedWeightedGraph(filename, sep, hasHeader);
		int n = graph.getNumVertices();
		int m = graph.getNumEdges();
		System.out.println(String.format("Graph with %s nodes and %s edges", n,m));
		
		Set<Double> weights = graph.getWeights();
		for(Double weight : weights)
			maxWeight = Math.max(maxWeight, weight);
		maxWeight = maxWeight + 1;
		weights.add(maxWeight);
		System.out.println("Creating filtration converter");
		FiltrationConverter filtrationConverter = new DecreasingMapConverter(weights);
		System.out.println("Creating clique complex stream");
		CliqueComplexWeightedStream stream = new CliqueComplexWeightedStream(graph, maxDim, filtrationConverter);
		System.out.println("Finalizing clique complex stream");
		stream.finalizeStream();
		System.out.println("Obtaining default simplicial algorithm");
		final AbstractPersistenceAlgorithm<Simplex> algorithm = Plex4.getDefaultSimplicialAlgorithm(maxDim);
		System.out.println("Computing intervals");
		BarcodeCollection<Double> intervals = algorithm.computeIntervals(stream);
		System.out.println("JavaPlex: Decreasing");
		//System.out.println(intervals);
		System.out.println(intervals.getBettiNumbers());
		
		BarcodeStringWriter.writeToFile(intervals, 0, String.valueOf(maxWeight), "0.0", basepath + "results/astroPhysics0.txt");
		BarcodeStringWriter.writeToFile(intervals, 1, String.valueOf(maxWeight), "0.0", basepath + "results/astroPhysics1.txt");
		BarcodeStringWriter.writeToFile(intervals, 2, String.valueOf(maxWeight), "0.0", basepath + "results/astroPhysics2.txt");
	}
}
