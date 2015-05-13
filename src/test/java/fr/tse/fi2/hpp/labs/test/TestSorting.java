package fr.tse.fi2.hpp.labs.test;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.tse.fi2.hpp.labs.queries.impl.lab6.utils.InsertionSort;
import fr.tse.fi2.hpp.labs.queries.impl.lab6.utils.ListeRandom;
import fr.tse.fi2.hpp.labs.queries.impl.lab6.utils.MergeSort;

public class TestSorting {
	
	int[] liste = ListeRandom.ListeRandom(); 
	int[] a = liste;
	int[] b = liste;
	int[] c = liste;

	@Test
	public void testMergeSort() {
		//int[] a = {26, 5, 98, 108, 28, 99, 100, 56, 34, 1 };
		long starttime1=System.nanoTime();	 
//        MergeSort.printArray("Avant Sort : ", a);
        MergeSort.sort(a, 0, a.length-1);
//        System.out.println();
//        MergeSort.printArray("Après Sort : ", a);
        long endtime1=System.nanoTime();	 
		System.out.println("1.Time (merge) taken by program:"+(endtime1-starttime1)+"ns");
	}
	
	@Test
	public void testInsertionSort() {
		//int[] a = {26, 5, 98, 108, 28, 99, 100, 56, 34, 1 };
		long starttime2=System.nanoTime();	 	
		InsertionSort.InsertionSort(b);
		long endtime2=System.nanoTime();	 
		System.out.println("2.Time (insertion) taken by program:"+(endtime2-starttime2)+"ns");
		
	}
	
/*	@Test
	public void test() {
		//int[] a = {26, 5, 98, 108, 28, 99, 100, 56, 34, 1 };
		long starttime3=System.nanoTime();	 	
		for(int i=1;i<=100000;i++){	 
			java.util.Arrays.sort(c);	
			}	  
		long endtime3=System.nanoTime();	 
		System.out.println("3.Time taken by program:"+(endtime3-starttime3)+"ns");
		
	}
*/
}
