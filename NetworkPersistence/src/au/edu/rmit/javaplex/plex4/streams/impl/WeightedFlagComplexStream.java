package au.edu.rmit.javaplex.plex4.streams.impl;

import au.edu.rmit.javaplex.homology.filtration.IncreasingMapConverter;
import edu.stanford.math.plex4.graph.UndirectedWeightedListGraph;
import edu.stanford.math.plex4.homology.filtration.FiltrationConverter;
import edu.stanford.math.plex4.streams.impl.FlagComplexStream;


/**
 * 
 * @author jacobien
 * 
 */
public class WeightedFlagComplexStream extends FlagComplexStream {

	private final UndirectedWeightedListGraph graph;

	/**
	 * This constructor initializes the stream with a given graph, and using increasing weights.
	 * 
	 * @param graph:				 	the graph to compute the flag complex from
	 * @param maxAllowableDimension:	the maximum dimension of the complex
	 */
	public WeightedFlagComplexStream(final UndirectedWeightedListGraph graph, final int maxAllowableDimension) {
		this(graph, maxAllowableDimension, IncreasingMapConverter.getInstance(graph.getWeights()));
	}

	/**
	 * This constructor initializes the stream with a given graph, using a converter of choice. 
	 * For decreasing weights, use DecreasingMapConverter.getInstance(graph.getWeights()).
	 * 
	 * @param graph:				 	the graph to compute the flag complex from
	 * @param maxAllowableDimension:	the maximum dimension of the complex
	 * @param filtrationConverter:		a custom filtration converter
	 */
	public WeightedFlagComplexStream(final UndirectedWeightedListGraph graph, final int maxAllowableDimension,
			final FiltrationConverter filtrationConverter) {
		super(maxAllowableDimension, filtrationConverter);
		this.graph = graph;
	}

	@Override
	protected UndirectedWeightedListGraph constructEdges() {
		return graph;
	}

}
