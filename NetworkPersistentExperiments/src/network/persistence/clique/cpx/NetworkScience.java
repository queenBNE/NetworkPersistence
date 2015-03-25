package network.persistence.clique.cpx;

import java.io.IOException;
import java.util.Set;

import network.persistence.old.NetworkPersistenceFunctions;

import org.junit.Test;

import edu.stanford.math.plex4.api.Plex4;
import edu.stanford.math.plex4.graph.UndirectedWeightedListGraph;
import edu.stanford.math.plex4.homology.barcodes.BarcodeCollection;
import edu.stanford.math.plex4.homology.chain_basis.Simplex;
import edu.stanford.math.plex4.homology.filtration.FiltrationConverter;
import edu.stanford.math.plex4.homology.interfaces.AbstractPersistenceAlgorithm;
import edu.stanford.math.plex4.streams.impl.VietorisRipsStream;
import au.edu.rmit.javaplex.graph.filtration.WeightedGraphFiltration;
import au.edu.rmit.javaplex.homology.filtration.DecreasingMapConverter;
import au.edu.rmit.javaplex.io.BarcodeStringWriter;
import au.edu.rmit.javaplex.io.GraphReader;
import au.edu.rmit.javaplex.plex4.streams.impl.CliqueComplexWeightedStream;

public class NetworkScience {
	final static String basepathUbuntu = "/home/jacobien/git/PersistentHomology/NetworkPersistence/";
	final static String basepathMac = "/Users/jacobien/Git/PersistentHomology/NetworkPersistence/";
	final static String basepath = basepathUbuntu;
	final static String filename = basepath + "networks/networkScience.csv";
	final static String sep = ",";
	final static Boolean hasHeader = true;
	final static Boolean directed = false;
	
	double maxWeight = 0;
	final static int maxDim = 3;
	
	
	@Test
	public void computePersistence() throws IOException{
		System.out.println("Reading graph");
		UndirectedWeightedListGraph graph = GraphReader.getUndirectedWeightedGraph(filename, sep, hasHeader);
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
		System.out.println(intervals);
		System.out.println(intervals.getBettiNumbers());
		
		Set<Integer> dimensions = intervals.getDimensions();
		for(Integer dimension : dimensions){
			BarcodeStringWriter.writeToFile(intervals, dimension, String.valueOf(maxWeight), "0.0", basepath + "results/networkScience"+ dimension+ ".txt");
		}
	}
}
