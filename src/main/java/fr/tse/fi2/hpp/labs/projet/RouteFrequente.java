package fr.tse.fi2.hpp.labs.projet;

import java.util.LinkedList;
import java.util.List;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import fr.tse.fi2.hpp.labs.beans.DebsRecord;
import fr.tse.fi2.hpp.labs.beans.Route;
import fr.tse.fi2.hpp.labs.beans.measure.QueryProcessorMeasure;
import fr.tse.fi2.hpp.labs.queries.AbstractQueryProcessor;

public class RouteFrequente extends AbstractQueryProcessor {
	private List<DebsRecord> recordList;
	private static Multiset<Route> top10;

	
	public RouteFrequente(QueryProcessorMeasure measure) {
		super(measure);
		// TODO Auto-generated constructor stub
		
		this.recordList = new LinkedList<DebsRecord>();
		this.top10 = HashMultiset.create();
	
	}
	
	public void process(DebsRecord record) {
		// TODO Auto-generated method stub
		recordList.add(record);
		if (recordList.size() > 1)
		{
			if (record.getDropoff_datetime() >= (recordList.get(0).getDropoff_datetime()+1800000))//30min et i'unite est ms
			{
				
				for (int i=0; i<recordList.size(); i++) {
				
					if(recordList.get(i).getDropoff_datetime() <= record.getDropoff_datetime()-1800000)
						recordList.remove(i);
				
				}
			}
			
			top10.clear();
			
			for (DebsRecord cursRecord : recordList) {
				
				Route cursRoute= convertRecordToRoute(cursRecord);
				top10.add(cursRoute,1);
			}
			
			for (Route route : top10) {
				
				if (top10.count(route) > 1)
					System.out.println("Top dix sont : " + top10.count(route));
			
			}
		}
	}
}