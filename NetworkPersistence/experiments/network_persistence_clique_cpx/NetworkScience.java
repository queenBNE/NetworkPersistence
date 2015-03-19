package network_persistence_clique_cpx;

import java.io.IOException;
import java.util.Set;

import network_persistence.NetworkPersistenceFunctions;

import org.junit.Test;

import edu.stanford.math.plex4.api.Plex4;
import edu.stanford.math.plex4.graph.UndirectedWeightedListGraph;
import edu.stanford.math.plex4.homology.barcodes.BarcodeCollection;
import edu.stanford.math.plex4.homology.chain_basis.Simplex;
import edu.stanford.math.plex4.homology.filtration.FiltrationConverter;
import edu.stanford.math.plex4.homology.interfaces.AbstractPersistenceAlgorithm;
import edu.stanford.math.plex4.streams.impl.VietorisRipsStream;
import au.edu.rmit.javaplex.graph.filtration.WeightedGraphFiltrationFunctions;
import au.edu.rmit.javaplex.graph.io.GraphReader;
import au.edu.rmit.javaplex.homology.filtration.DecreasingMapConverter;
import au.edu.rmit.javaplex.plex4.streams.impl.CliqueComplexWeightedStream;

public class NetworkScience {
	final static String filename = "/Users/jacobien/Git/PersistentHomology/NetworkPersistence/Networks/networkScience.csv";
	final static String sep = ",";
	final static Boolean hasHeader = true;
	final static Boolean directed = false;
	
	final static double maxWeight = 5;
	final static int maxDim = 2;
	
	@Test
	public void computePersistence() throws IOException{
		System.out.println("Reading graph");
		UndirectedWeightedListGraph graph = GraphReader.getUndirectedWeightedGraph(filename, sep, hasHeader);
		Set<Double> weights = graph.getWeights();
		weights.add(maxWeight);
		FiltrationConverter filtrationConverter = new DecreasingMapConverter(weights);
		CliqueComplexWeightedStream stream = new CliqueComplexWeightedStream(graph, maxDim, filtrationConverter);
		stream.finalizeStream();
		final AbstractPersistenceAlgorithm<Simplex> algorithm = Plex4.getDefaultSimplicialAlgorithm(maxDim);
		BarcodeCollection<Double> intervals = algorithm.computeIntervals(stream);
		System.out.println("JavaPlex: Decreasing");
		System.out.println(intervals);
		System.out.println(intervals.getBettiNumbers());
	}
}
