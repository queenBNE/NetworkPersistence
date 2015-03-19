package au.edu.rmit.javaplex.homology.filtration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

import edu.stanford.math.plex4.homology.barcodes.Interval;
import edu.stanford.math.plex4.homology.filtration.FiltrationConverter;

/**
 * <p>
 * This class implements a conversion between filtration values and filtration indices that is increasing in weights. The weights are indexed by the
 * position within an ordered ascending list.
 * </p>
 * <p>
 * If the list of weights is given by (0 < w_1 < w_2 < w_3 < ... < w_k) then w_i will be mapped to i. Zero is always mapped to 0.
 * </p>
 * Only works for all positive weights.
 * @author Jacobien Carstens
 */

public class DecreasingMapConverter extends FiltrationConverter {

	final private ArrayList<Double> map;
	final private Double maxValue;

	public DecreasingMapConverter(final Set<Double> weights) {
		super();
		map = new ArrayList<Double>(weights);
		Collections.sort(map);
		Collections.reverse(map);
		this.maxValue = map.get(0);
	}

	public static DecreasingMapConverter getInstance(final Set<Double> weights) {
		return new DecreasingMapConverter(weights);
	}

	public static DecreasingMapConverter getInstance(final Set<Double> weights, final double maxValue) {
		weights.add(maxValue);
		return new DecreasingMapConverter(weights);
	}

	@Override
	public int getFiltrationIndex(final double filtrationValue) {
		return (map.indexOf(filtrationValue));
	}

	@Override
	public double getFiltrationValue(final int filtrationIndex) {
		return map.get(filtrationIndex);
	}

	@Override
	public double computeInducedFiltrationValue(final double a, final double b) {
		return Math.min(a, b);
	}

	@Override
	public double getInitialFiltrationValue() {
		return maxValue;
	}

	/**
	 * This function returns an interval with the desired parameters.
	 * @param <T>
	 * @param start
	 * @param end
	 * @param isLeftClosed
	 * @param isRightClosed
	 * @param isLeftInfinite
	 * @param isRightInfinite
	 * @return an interval specified by the given parameters
	 */
	//makeInterval(T start, T end, boolean isLeftClosed, boolean isRightClosed, boolean isLeftInfinite, boolean isRightInfinite) {

	@Override
	public Interval<Double> evaluate(final Interval<Integer> interval) {
		if (interval.isLeftInfinite() && interval.isRightInfinite()) { return Interval.makeInterval(0d, null, true, false, false, true); }

		if (interval.isLeftInfinite()) { return Interval.makeInterval(this.getFiltrationValue(interval.getEnd()), null, true, false, false, true); }

		if (interval.isRightInfinite()) { return Interval.makeInterval(0d, this.getFiltrationValue(interval.getStart()), true, false, false, false); }

		return Interval.makeInterval(this.getFiltrationValue(interval.getEnd()), this.getFiltrationValue(interval.getStart()), interval.isRightClosed(), interval.isLeftClosed(),
			false, false);
	}

}
