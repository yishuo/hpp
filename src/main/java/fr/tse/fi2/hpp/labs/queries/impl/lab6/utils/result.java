package fr.tse.fi2.hpp.labs.queries.impl.lab6.utils;

import java.util.concurrent.ForkJoinPool;

public class result {

	
	private static void result() {
		// TODO Auto-generated method stub
		
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
        

		MyRecursiveAction myRecursiveAction = new MyRecursiveAction(24);
		forkJoinPool.invoke(myRecursiveAction);
/*
        
		MyRecursiveTask myRecursiveTask = new MyRecursiveTask(24);
		long mergedResult = forkJoinPool.invoke(myRecursiveTask);
		System.out.println("mergedResult = " + mergedResult);
*/	
        
	}
	
	public static void main(String[] args){
		result();
	}



}
