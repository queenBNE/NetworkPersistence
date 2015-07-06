package network.persistence.bash;

import java.io.IOException;
import java.util.Set;
import au.edu.rmit.javaplex.homology.filtration.DecreasingMapConverter;
import au.edu.rmit.javaplex.io.BarcodeStringWriter;
import au.edu.rmit.javaplex.io.GraphReader;
import au.edu.rmit.javaplex.plex4.streams.impl.WeightedFlagComplexStream;
import edu.stanford.math.plex4.api.Plex4;
import edu.stanford.math.plex4.graph.UndirectedWeightedListGraph;
import edu.stanford.math.plex4.homology.barcodes.BarcodeCollection;
import edu.stanford.math.plex4.homology.chain_basis.Simplex;
import edu.stanford.math.plex4.homology.filtration.FiltrationConverter;
import edu.stanford.math.plex4.homology.interfaces.AbstractPersistenceAlgorithm;


public class DescendingPersistentHomology {
	
	final static String sep = ",";
	final static Boolean hasHeader = false;
	final static Boolean directed = false;
	
	static double maxWeight = 0;
	final static int maxDim = 3;
	
	public static void main(String[] args) throws IOException {
		if(args.length != 2){
			System.out.println("2 arguments required: network-file output-prefix");
			return;
		}
		
		String filename = args[0];
		String outputPrefix = args[1];
		System.out.println("Reading graph from file: " + filename);
		UndirectedWeightedListGraph graph = GraphReader.getUndirectedWeightedGraph(filename, sep, hasHeader);
		System.out.println("Creating filtration converter");
		Set<Double> weights = graph.getWeights();
		for(Double weight : weights)
			maxWeight = Math.max(maxWeight, weight);
		maxWeight = maxWeight + 1;
		
		weights.add(maxWeight);	
		FiltrationConverter filtrationConverter = new DecreasingMapConverter(weights);
		System.out.println("Creating clique complex stream");
		WeightedFlagComplexStream stream = new WeightedFlagComplexStream(graph, maxDim, filtrationConverter);
		System.out.println("Finalizing stream");
		stream.finalizeStream();
		System.out.println("Computing intervals");
		final AbstractPersistenceAlgorithm<Simplex> algorithm = Plex4.getDefaultSimplicialAlgorithm(maxDim);
		BarcodeCollection<Double> intervals = algorithm.computeIntervals(stream);
		System.out.println("Betti numbers (overall)");
		System.out.println(intervals.getBettiNumbers());
		System.out.println("Writing intervals to files");
		Set<Integer> dimensions = intervals.getDimensions();
		for(Integer dimension : dimensions)
			BarcodeStringWriter.writeToFile(intervals, dimension, String.valueOf(maxWeight), "0.0", outputPrefix + dimension + ".txt");
	}
}
