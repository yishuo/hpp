package fr.tse.fi2.hpp.labs.queries.impl.lab1;

import fr.tse.fi2.hpp.labs.beans.DebsRecord;
import fr.tse.fi2.hpp.labs.beans.measure.QueryProcessorMeasure;
import fr.tse.fi2.hpp.labs.queries.AbstractQueryProcessor;

public class IncrementalAveragePrice extends AbstractQueryProcessor {
	private int num = 0;
	private float sum = 0;

	public IncrementalAveragePrice(QueryProcessorMeasure measure) {
		super(measure);
	}

	
	@Override
	protected void process(DebsRecord record) {
		num++;
		sum += record.getFare_amount();
		writeLine("The average price is : " + (sum / num));
	}

}