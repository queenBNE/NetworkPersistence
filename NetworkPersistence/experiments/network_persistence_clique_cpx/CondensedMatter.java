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
import au.edu.rmit.javaplex.graph.io.GraphReader;
import au.edu.rmit.javaplex.homology.filtration.DecreasingMapConverter;
import au.edu.rmit.javaplex.plex4.streams.impl.CliqueComplexWeightedStream;

public class CondensedMatter {
	final static String filename = "/Users/jacobien/Git/PersistentHomology/NetworkPersistence/Networks/condensedMatter.csv";
	final static String sep = ",";
	final static Boolean hasHeader = false;
	final static Boolean directed = false;
	
	double maxWeight = 0;
	final static int maxDim = 2;
	
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
		FiltrationConverter filtrationConverter = new DecreasingMapConverter(weights);
		CliqueComplexWeightedStream stream = new CliqueComplexWeightedStream(graph, maxDim, filtrationConverter);
		stream.finalizeStream();
		final AbstractPersistenceAlgorithm<Simplex> algorithm = Plex4.getDefaultSimplicialAlgorithm(maxDim);
		BarcodeCollection<Double> intervals = algorithm.computeIntervals(stream);
		System.out.println("JavaPlex: Decreasing");
		//System.out.println(intervals);
		System.out.println(intervals.getBettiNumbers());
	}
}
