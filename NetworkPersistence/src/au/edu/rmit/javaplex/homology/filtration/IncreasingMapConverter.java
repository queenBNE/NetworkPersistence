package au.edu.rmit.javaplex.homology.filtration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

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

public class IncreasingMapConverter extends FiltrationConverter {

	final private ArrayList<Double> map;

	public IncreasingMapConverter(final Set<Double> weights) {
		super();
		map = new ArrayList<Double>(weights);
		Collections.sort(map);
	}

	public static IncreasingMapConverter getInstance(final Set<Double> weights) {
		return new IncreasingMapConverter(weights);
	}

	@Override
	public int getFiltrationIndex(final double filtrationValue) {
		if (filtrationValue == 0)
			return 0;
		return (map.indexOf(filtrationValue) + 1);
	}

	@Override
	public double getFiltrationValue(final int filtrationIndex) {
		if (filtrationIndex == 0)
			return 0;
		return map.get(filtrationIndex - 1);
	}

	@Override
	public double computeInducedFiltrationValue(final double a, final double b) {
		return Math.max(a, b);
	}

	@Override
	public double getInitialFiltrationValue() {
		return 0;
	}

}
