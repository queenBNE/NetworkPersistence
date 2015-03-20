package au.edu.rmit.javaplex.io;

import java.io.IOException;
import java.util.List;

import edu.stanford.math.plex4.homology.barcodes.Interval;
import edu.stanford.math.plex4.homology.barcodes.PersistenceInvariantDescriptor;
import edu.stanford.math.plex4.io.FileIOUtility;


public class BarcodeStringWriter {

	
	public static <G> void writeToFile(PersistenceInvariantDescriptor<Interval<Double>, G> object, int dimension, String inf, String minInf, String path) throws IOException {
		List<Interval<Double>> intervals = object.getIntervalsAtDimension(dimension);
		String contents = "";
		for(Interval<Double> interval : intervals)
			contents = contents + String.format("%s,%s\n", interval.isLeftInfinite() ? minInf : interval.getStart(), interval.isRightInfinite() ? inf : interval.getEnd());
		FileIOUtility.writeTextFile(path, contents, false);
	}
	
}
