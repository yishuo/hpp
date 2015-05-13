package fr.tse.fi2.hpp.labs.queries.impl.lab4;

import fr.tse.fi2.hpp.labs.beans.DebsRecord;
import fr.tse.fi2.hpp.labs.beans.measure.QueryProcessorMeasure;
import fr.tse.fi2.hpp.labs.queries.AbstractQueryProcessor;


import java.util.BitSet;

/**
 * @author lv
 *
 */
public class BloomFiltres extends AbstractQueryProcessor{

	  private static DebsRecord recordTest;
	  private int com=0;
	  
      private static final int DEFAULT_SIZE = 14378;//Initialisation de m
      private static final int[] seeds = new int[] { 5, 7, 11, 13, 31, 37, 61, 67, 71, 79};
      private static BitSet bits = new BitSet(DEFAULT_SIZE);
      //l'objet des function hash
      private static SimpleHash[] func = new SimpleHash[seeds.length];
      
      public BloomFiltres(QueryProcessorMeasure measure) {
    	  super(measure);
    	  // TODO Auto-generated constructor stub
    	  for (int i = 0; i < seeds.length; i++) {
    		  func[i] = new SimpleHash(DEFAULT_SIZE, seeds[i]);
    		  }	
    	  }
      
	@Override
	protected void process(DebsRecord record) {
		// TODO Auto-generated method stub	
		
			String recordstring =null;
			recordstring += record.getPickup_longitude();
			recordstring += record.getPickup_latitude();
			recordstring += record.getDropoff_longitude();
			recordstring += record.getDropoff_latitude();
			recordstring += record.getHack_license();
			this.add(recordstring);
			com++;
			if(com==15){
				recordTest = record;
			}					
		}
	
	public static String getRecord(){
		String recordstring=null;
		recordstring += recordTest.getPickup_longitude();
		recordstring += recordTest.getPickup_latitude();
		recordstring += recordTest.getDropoff_longitude();
		recordstring += recordTest.getDropoff_latitude();
		recordstring += recordTest.getHack_license();
		return recordstring;
	}
	
	//coder String dans la hash bits
	public void add(String value) {
		for (SimpleHash f : func) {
			bits.set(f.hash(value), true);
			}
		}
	
	//verifier
	public static boolean contain(String value) {
		if (value == null) {
			return false;
			}
		boolean ret = true;
		for (SimpleHash f : func) {
			ret = ret && bits.get(f.hash(value));
			}
		return ret;
		}
	}
  