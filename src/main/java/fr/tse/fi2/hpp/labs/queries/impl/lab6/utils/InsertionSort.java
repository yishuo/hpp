package fr.tse.fi2.hpp.labs.queries.impl.lab6.utils;

public class InsertionSort {
	
	public static void InsertionSort(int[] a) {
		// TODO Auto-generated method stub
		 for (int i = 1; i < a.length; i++) {
	            int currentNumber = a[i];
	            int j = i - 1;
	            while (j >= 0 && a[j] > currentNumber) {
	                a[j + 1] = a[j];
	                j--;
	            }
	            a[j + 1] = currentNumber;
	        }
	}
	
	
	public static void main(String[] args){
        int[] a = {26, 5, 98, 108, 28, 99, 100, 56, 34, 1 };
		InsertionSort(a);
        for(int i=0; i<a.length; i++){
        System.out.println(a[i]);}
	
	}
	
	}
