package network.persistence.old;


import au.edu.rmit.javaplex.graph.filtration.WeightedGraphFiltration;
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
			stream = WeightedGraphFiltration.getVietorisRipsStreamAscending(adjacencyMatrix, maxweight, maxDim);
		else
			stream = WeightedGraphFiltration.getVietorisRipsStreamDescending(adjacencyMatrix, maxweight, maxDim);
		
		System.out.println("Obtaining algorithm");
		final AbstractPersistenceAlgorithm<Simplex> algorithm = Plex4.getDefaultSimplicialAlgorithm(maxDim);
		System.out.println("Computing intervals");
		BarcodeCollection<Double> intervals = algorithm.computeIntervals(stream);
		if(!ascending){
			System.out.println("Converting intervals to descending intervals");
			intervals = WeightedGraphFiltration.convertToDescendingIntervals(intervals);
		}
		return(intervals);
	}
	
	
}
