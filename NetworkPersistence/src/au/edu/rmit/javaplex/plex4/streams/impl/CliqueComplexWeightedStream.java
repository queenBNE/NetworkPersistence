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
public class CliqueComplexWeightedStream extends FlagComplexStream {

	private final UndirectedWeightedListGraph graph;

	/**
	 * This constructor initializes the complex with a given graph.
	 * 
	 * @param graph
	 *            the graph to compute the clique complex from
	 * @param maxAllowableDimension
	 *            the maximum dimension of the complex
	 */
	public CliqueComplexWeightedStream(final UndirectedWeightedListGraph graph, final int maxAllowableDimension) {
		this(graph, maxAllowableDimension, IncreasingMapConverter.getInstance(graph.getWeights()));
	}

	public CliqueComplexWeightedStream(final UndirectedWeightedListGraph graph, final int maxAllowableDimension,
			final FiltrationConverter filtrationConverter) {
		super(maxAllowableDimension, filtrationConverter);
		super.converter = filtrationConverter;
		this.graph = graph;
	}

	@Override
	protected UndirectedWeightedListGraph constructEdges() {
		return graph;
	}

}
