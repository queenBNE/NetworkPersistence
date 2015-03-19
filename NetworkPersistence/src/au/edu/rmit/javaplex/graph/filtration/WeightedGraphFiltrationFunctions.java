package au.edu.rmit.javaplex.graph.filtration;

import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import edu.stanford.math.plex4.homology.barcodes.BarcodeCollection;
import edu.stanford.math.plex4.homology.barcodes.Interval;
import edu.stanford.math.plex4.metric.impl.ExplicitMetricSpace;
import edu.stanford.math.plex4.streams.impl.VietorisRipsStream;
import edu.stanford.math.primitivelib.utility.Infinity;

public class WeightedGraphFiltrationFunctions {

	public static double inf = Infinity.Double.getPositiveInfinity();

	public static VietorisRipsStream<Integer> getVietorisRipsStreamAscending(double[][] adjacencyMatrix, double maxWeight, int dim) {
		double[][] distanceMatrix = WeightedGraphFiltrationFunctions.adjacencyToDistanceMatrix(adjacencyMatrix);
		ExplicitMetricSpace ems = new ExplicitMetricSpace(distanceMatrix);
		double[] weights = WeightedGraphFiltrationFunctions.getIncreasingWeights(distanceMatrix, maxWeight);
		final VietorisRipsStream<Integer> stream = new VietorisRipsStream<Integer>(ems, weights, dim);
		stream.finalizeStream();
		return stream;
	}
	
	public static VietorisRipsStream<Integer> getVietorisRipsStreamDescending(double[][] adjacencyMatrix, double maxWeight, int dim) {
		double[][] distanceMatrix = WeightedGraphFiltrationFunctions.adjacencyToDistanceMatrixDescending(adjacencyMatrix);
		double[] weights = WeightedGraphFiltrationFunctions.getIncreasingWeights(distanceMatrix, -maxWeight);
		ExplicitMetricSpace ems = new ExplicitMetricSpace(distanceMatrix);
		final VietorisRipsStream<Integer> stream = new VietorisRipsStream<Integer>(ems, weights, dim);
		stream.finalizeStream();
		return stream;
	}
	
	public static double[][] adjacencyToDistanceMatrix(double[][] adjacencyMatrix){
		double[][] distanceMatrix = adjacencyMatrix;
			for(int i=0; i < distanceMatrix.length; i++)
				for(int j=0; j < distanceMatrix[0].length; j++)
					if(i != j && distanceMatrix[i][j] == 0)
						distanceMatrix[i][j] = inf;
		return distanceMatrix;
	}
	
	private static double[][] adjacencyToDistanceMatrixDescending(double[][] adjacencyMatrix){
		double[][] distanceMatrix = adjacencyMatrix;
			for(int i=0; i < distanceMatrix.length; i++)
				for(int j=0; j < distanceMatrix[0].length; j++){
					if(i != j && distanceMatrix[i][j] == 0)
						distanceMatrix[i][j] = inf;
					else
						distanceMatrix[i][j] =  0-adjacencyMatrix[i][j];
				}
		return distanceMatrix;
	}
	
	private static double[] getIncreasingWeights(double[][] M, double max) {
		TreeSet<Double> weights = new TreeSet<Double>();
		for(int i=0; i < M.length; i++)
			for(int j=0; j < M[0].length; j++)
				if(M[i][j] != inf)
					weights.add(M[i][j]);
		weights.add(max);
		
		Iterator<Double> iterator = weights.iterator();
		double[] sortedWeights = new double[weights.size()];
		int i = 0;
		while(iterator.hasNext()){
			sortedWeights[i] = iterator.next();
			i++;
		}
		return sortedWeights;
	}
	
	
	public static BarcodeCollection<Double> convertToDescendingIntervals(BarcodeCollection<Double> intervals){
		BarcodeCollection<Double> descendingIntervals = new BarcodeCollection<Double>();
		for(Integer dim : intervals.getDimensions()){
			List<Interval<Double>> intervalsAtDim = intervals.getIntervalsAtDimension(dim);
			for(Interval<Double> interval : intervalsAtDim){
				Interval<Double> intervalD = interval.isRightInfinite() ? Interval.makeLeftInfiniteLeftOpenInterval(-interval.getStart()) : Interval.makeFiniteLeftOpenInterval(-interval.getEnd(), -interval.getStart());
				descendingIntervals.addInterval(dim, intervalD);
			}	
		}
		return descendingIntervals;
	}

}
