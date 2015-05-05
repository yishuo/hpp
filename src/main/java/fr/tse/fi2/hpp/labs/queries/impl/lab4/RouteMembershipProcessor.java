package fr.tse.fi2.hpp.labs.queries.impl.lab4;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

import fr.tse.fi2.hpp.labs.beans.DebsRecord;
import fr.tse.fi2.hpp.labs.beans.measure.QueryProcessorMeasure;
import fr.tse.fi2.hpp.labs.queries.AbstractQueryProcessor;

@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@State(Scope.Thread)

public class RouteMembershipProcessor extends AbstractQueryProcessor {

	private static DebsRecord recordTest;
	private int com=0;
	private static ArrayList<DebsRecord> ListeRoute = new ArrayList<DebsRecord>();

	
	public RouteMembershipProcessor(QueryProcessorMeasure measure) {
		super(measure);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void process(DebsRecord record){
		// TODO Auto-generated constructor stub

		ListeRoute.add(record);
		com++;
		if(com==20){
			recordTest = record;
		}
	}

	public static DebsRecord getRecord(){
		return recordTest;
	}

	
	public static boolean checkroute(DebsRecord record){
		for(int i=0; i<ListeRoute.size(); i++){
			if(record.getPickup_latitude() == ListeRoute.get(i).getPickup_latitude()
			&& record.getPickup_longitude() == ListeRoute.get(i).getPickup_longitude()
			&& record.getDropoff_latitude() == ListeRoute.get(i).getDropoff_latitude()
			&& record.getDropoff_longitude() == ListeRoute.get(i).getDropoff_longitude()
			&& record.getHack_license() == ListeRoute.get(i).getHack_license())
			{
				return true;
			}
		} return false;
	}   

}
