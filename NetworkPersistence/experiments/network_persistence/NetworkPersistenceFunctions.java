package network_persistence;


import au.edu.rmit.javaplex.graph.filtration.WeightedGraphFiltrationFunctions;
import edu.stanford.math.plex4.api.Plex4;
import edu.stanford.math.plex4.homology.barcodes.BarcodeCollection;
import edu.stanford.math.plex4.homology.chain_basis.Simplex;
import edu.stanford.math.plex4.homology.interfaces.AbstractPersistenceAlgorithm;
import edu.stanford.math.plex4.streams.impl.VietorisRipsStream;

public class NetworkPersistenceFunctions {

	public static BarcodeCollection<Double> computeIntervals(double[][] adjacencyMatrix, boolean ascending, double maxweight, int maxDim) {
		System.out.println("Creating stream");
		VietorisRipsStream<Integer> stream;
		if(ascending)
			stream = WeightedGraphFiltrationFunctions.getVietorisRipsStreamAscending(adjacencyMatrix, maxweight, maxDim);
		else
			stream = WeightedGraphFiltrationFunctions.getVietorisRipsStreamDescending(adjacencyMatrix, maxweight, maxDim);
		
		System.out.println("Obtaining algorithm");
		final AbstractPersistenceAlgorithm<Simplex> algorithm = Plex4.getDefaultSimplicialAlgorithm(maxDim);
		System.out.println("Computing intervals");
		BarcodeCollection<Double> intervals = algorithm.computeIntervals(stream);
		if(!ascending){
			System.out.println("Converting intervals to descending intervals");
			intervals = WeightedGraphFiltrationFunctions.convertToDescendingIntervals(intervals);
		}
		return(intervals);
	}
	
	
}
