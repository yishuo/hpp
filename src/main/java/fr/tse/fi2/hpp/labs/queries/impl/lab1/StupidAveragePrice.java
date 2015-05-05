package fr.tse.fi2.hpp.labs.queries.impl.lab1;

import java.util.ArrayList;
import java.util.List;

import fr.tse.fi2.hpp.labs.beans.DebsRecord;
import fr.tse.fi2.hpp.labs.beans.measure.QueryProcessorMeasure;
import fr.tse.fi2.hpp.labs.queries.AbstractQueryProcessor;

public class StupidAveragePrice extends AbstractQueryProcessor {
//	private float sum = 0;
//	private float trip = 0;
//	private float ave = 0;
	List<Float> prices;

	public StupidAveragePrice(QueryProcessorMeasure measure) {
		super(measure);
		// TODO Auto-generated constructor stub
	    prices = new ArrayList<>();
	}

	@Override
	protected void process(DebsRecord record) {
		// TODO Auto-generated method stub
//		sum+=record.getFare_amount();
//		trip+=record.getTrip_distance();
//		ave=sum/trip;
		prices.add(record.getFare_amount());
		double sum=0;
		for(Float f: prices){
			sum += f;
		}
//		writeLine("Average"+ave);
		writeLine("" + sum / (double) prices.size());
		
	}

}
