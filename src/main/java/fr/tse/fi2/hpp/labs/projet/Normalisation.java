package fr.tse.fi2.hpp.labs.projet;

import java.util.Vector;

import fr.tse.fi2.hpp.labs.beans.DebsRecord;
import fr.tse.fi2.hpp.labs.beans.measure.QueryProcessorMeasure;
import fr.tse.fi2.hpp.labs.queries.AbstractQueryProcessor;


public class Normalisation extends AbstractQueryProcessor{
	
	private static DebsRecord recordTest;
	float xDepart, yDepart, xArrivee, yArrivee;

	
	public Normalisation(QueryProcessorMeasure measure) {
		super(measure);
		// TODO Auto-generated constructor stub
		
	}


	public static Vector<Vector> Normalisation(DebsRecord record){
		float xDepart, yDepart, xArrivee, yArrivee;
		
		Vector<Vector> v = new Vector<Vector>();
		Vector<Integer> v1 = new Vector<Integer>();
		Vector<Integer> v2 = new Vector<Integer>();
		v.addElement(v1);
		v.addElement(v2);
		
		xDepart = record.getPickup_longitude();
		yDepart = record.getPickup_latitude();
		xArrivee = record.getDropoff_longitude();
		yArrivee = record.getDropoff_latitude();
		
		//le point depart (-208.52506, -324.913584)
		v1.add((int)((xDepart+208.52506)*200));
		v1.add((int)((yDepart+324.913584)*200));
		
		v2.add((int)((xArrivee+208.52506)*200));
		v2.add((int)((yArrivee+324.913584)*200));
		
		System.out.println(v);
		
		return v;
	}
	
	
	protected void process(DebsRecord record) {
		// TODO Auto-generated method stub	
			int compte=0;
			compte++;
			if(compte==20){
					recordTest = record;
			}					
		}
	
	public static DebsRecord getRecord(){
		return recordTest;
	}
    
    
	public static void main(String[] args){
		DebsRecord record = getRecord();
		System.out.println(Normalisation.Normalisation(record));
	}
	
	
}