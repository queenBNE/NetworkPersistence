package au.edu.rmit.javaplex.graph;

import java.util.Set;

import au.edu.rmit.javaplex.homology.filtration.DecreasingMapConverter;
import au.edu.rmit.javaplex.homology.filtration.IncreasingMapConverter;
import au.edu.rmit.javaplex.plex4.streams.impl.WeightedFlagComplexStream;
import edu.stanford.math.plex4.api.Plex4;
import edu.stanford.math.plex4.graph.UndirectedWeightedListGraph;
import edu.stanford.math.plex4.homology.barcodes.BarcodeCollection;
import edu.stanford.math.plex4.homology.chain_basis.Simplex;
import edu.stanford.math.plex4.homology.filtration.FiltrationConverter;
import edu.stanford.math.plex4.homology.interfaces.AbstractPersistenceAlgorithm;
import edu.stanford.math.plex4.streams.impl.FlagComplexStream;

public class GraphPersistenceAPI {

	public static void main(String[] args) {}
	
	public static FlagComplexStream getWeightedFlagComplexStream(UndirectedWeightedListGraph graph, int dim, boolean increasing){
		Set<Double> weights = graph.getWeights();
		double maxWeight = 0;
		for(Double weight : weights)
			maxWeight = Math.max(maxWeight, weight);
		maxWeight = maxWeight + 1;
		weights.add(maxWeight);		
		FiltrationConverter filtrationConverter;
		if(increasing)
			filtrationConverter = new IncreasingMapConverter(weights);
		else
			filtrationConverter = new DecreasingMapConverter(weights);
		WeightedFlagComplexStream stream = new WeightedFlagComplexStream(graph, dim, filtrationConverter);
		stream.finalizeStream();
		return(stream);
	}
	
	public static BarcodeCollection<Double> getWeightedGraphPersistence(UndirectedWeightedListGraph graph, int dim, boolean increasing){
		final FlagComplexStream stream = getWeightedFlagComplexStream(graph, dim, increasing);
		final AbstractPersistenceAlgorithm<Simplex> algorithm = Plex4.getDefaultSimplicialAlgorithm(dim);
		return(algorithm.computeIntervals(stream));
	}
	
}
