package fr.tse.fi2.hpp.labs.main;

import fr.tse.fi2.hpp.labs.beans.DebsRecord;
import fr.tse.fi2.hpp.labs.beans.measure.QueryProcessorMeasure;
import fr.tse.fi2.hpp.labs.dispatcher.LoadFirstDispatcher;
import fr.tse.fi2.hpp.labs.queries.AbstractQueryProcessor;
import fr.tse.fi2.hpp.labs.queries.impl.lab4.RouteMembershipProcessor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


//@BenchmarkMode(Mode.AverageTime)
//@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@State(Scope.Thread)

public class MyBenchmark {

	private DebsRecord recordTest;
	final static Logger logger = LoggerFactory.getLogger(MainNonStreaming.class);
	public void init(){
		// Init query time measure
		QueryProcessorMeasure measure = new QueryProcessorMeasure();
		// Init dispatcher and load everything
		LoadFirstDispatcher dispatch = new LoadFirstDispatcher(
				"src/main/resources/data/sorted_data.csv");
		logger.info("Finished parsing");
		// Query processors
		List<AbstractQueryProcessor> processors = new ArrayList<>();

		// Add you query processor here
		//processors.add(new SimpleQuerySumEvent(measure));
		//processors.add(new NaiveAverage(measure));
		//processors.add(new IncrementalAverage(measure));	
		processors.add(new RouteMembershipProcessor(measure));	


		// Register query processors
		for (AbstractQueryProcessor queryProcessor : processors) {
			dispatch.registerQueryProcessor(queryProcessor);
		}
		// Initialize the latch with the number of query processors
		CountDownLatch latch = new CountDownLatch(processors.size());
		// Set the latch for every processor
		for (AbstractQueryProcessor queryProcessor : processors) {
			queryProcessor.setLatch(latch);
		}
		for (AbstractQueryProcessor queryProcessor : processors) {
			Thread t = new Thread(queryProcessor);
			t.setName("QP" + queryProcessor.getId());
			t.start();
		}
		// Start everything dispatcher first, not as a thread
		dispatch.run();
		logger.info("Finished Dispatching");
		// Wait for the latch
		try {
			latch.await();
		} catch (InterruptedException e) {
			logger.error("Error while waiting for the program to end", e);
		}
		// Output measure and ratio per query processor
		measure.setProcessedRecords(dispatch.getRecords());
		measure.outputMeasure();

		
//		float x1 = (float) -73.98358;
//		float y1= (float) 40.7341;
//		float x2= (float) -73.98048;
//		float y2= (float) 40.72557 ;
//		String licence= "4FE0002AAE2310E6DD209FBB9187AF71";

		recordTest = RouteMembershipProcessor.getRecord();
		
    	}
	
   @Benchmark	
   public void test1(){
	   System.out.println("Recherche de la route : " + RouteMembershipProcessor.checkroute(recordTest));
   }
   
	//    public float test2() {
	//       long sum=0L;
	//       for(Integer integer : liste2){
	//    	   sum += integer;
	//       }
	//       float mean = sum / n;
	//       return mean;
	//    }
   
   public static void main(String[] args) throws RunnerException {
		Options opt = new OptionsBuilder()
		.include(MyBenchmark.class.getSimpleName())
		.build();
		new Runner(opt).run();
	}
	


}
