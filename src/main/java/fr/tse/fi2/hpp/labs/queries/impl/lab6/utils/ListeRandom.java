package fr.tse.fi2.hpp.labs.queries.impl.lab6.utils;

import java.util.Random;

public class ListeRandom {

	public static int[] ListeRandom() {
		// TODO Auto-generated method stub
		int[] a = new int [50000];
		Random random = new Random(); 
		for(int i=0; i<10; i++) {
			a[i] = random.nextInt(10000);
		}
/*		
		for(int j=0; j<10; j++){
			System.out.println(a[j]);
		}
*/
		return a;
	}
/*	
	public static void main(String[] args){
		ListeRandom();
		
	}
*/

}
