package fr.tse.fi2.hpp.labs.test;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import fr.tse.fi2.hpp.labs.queries.impl.lab6.utils.MergeSort;

public class TestSorting {

	@Test
	public void test1() {
		
		int[] tab1;
		int[] tab2;
		
		tab1 = MergeSort.genererAleatoire(100000);
		tab2 = tab1;
		
		Arrays.sort(tab1);
		
		int[] listeTrie1 = MergeSort.trier(tab2);
		
		assertArrayEquals(tab1, listeTrie1);
	}
	
	@Test
	public void test2() {
		
		int[] tab1;
		int[] tab2;
		
		tab1 = MergeSort.genererAleatoire(100000);
		tab2 = tab1;
		
		Arrays.sort(tab1);
		
		int[] listeTrie2 = MergeSort.trierInsertionSort(tab2);
		
		assertArrayEquals(tab1, listeTrie2);
	}

}
