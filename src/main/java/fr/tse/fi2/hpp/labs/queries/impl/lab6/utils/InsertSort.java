package fr.tse.fi2.hpp.labs.queries.impl.lab6.utils;

/*
 * L'algorithme de insertSort 
 */

public class InsertSort {  
	public static int[] InsertSort(int a[]){
		int length=a.length; 
		int j;				 
		int i;				 
		int key;			 
		for(j=1;j<length;j++){
			key=a[j];
			i=j-1;
			while(i>=0 && a[i]<key){
				a[i+1]=a[i]; 
				i--;   		
			}
			a[i+1]=key;   
		}
		return a;
	} 

	    public static void main(String []args) {  
	        int []c = {5, 6, 432, 56, 453, 890};  
	        InsertSort(c);
	        for(int i = 0; i < c.length; i++)  
	            System.out.println( c[i] );  
	    }
  


}