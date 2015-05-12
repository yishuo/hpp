package fr.tse.fi2.hpp.labs.main;

import fr.tse.fi2.hpp.labs.beans.measure.QueryProcessorMeasure;
import fr.tse.fi2.hpp.labs.dispatcher.LoadFirstDispatcher;
import fr.tse.fi2.hpp.labs.queries.AbstractQueryProcessor;
import fr.tse.fi2.hpp.labs.queries.impl.lab4.BloomFiltres;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@BenchmarkMode(Mode.AverageTime)
//@OutputTimeUnit(TimeUnit.NANOSECONDS)
//@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)


//@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)


@Fork(1)
@Measurement(iterations = 5)
@Warmup(iterations = 5)
@State(Scope.Benchmark)
//@State(Scope.Thread)

public class MyBenchmark {
//	private DebsRecord recordTest;//pour 1 version
	private String recordTest;
	
	final static Logger logger = LoggerFactory
			.getLogger(MainNonStreaming.class);
	

	
	@Setup//是决定在benchmark前做还是后做
	/**
	 * 
	 * initialise la liste des entiers pour chque iteration
	 * suivant la valeur de <code>n</code>.
	 */
	public void init(){
		/**
		 * @param args
		 * @throws IOException
		 */
			// Init query time measure
			QueryProcessorMeasure measure = new QueryProcessorMeasure();
			// Init dispatcher and load everything
			LoadFirstDispatcher dispatch = new LoadFirstDispatcher(
					"src/main/resources/data/1000Records.csv");
			
			
//			LoadFirstDispatcher dispatch = new LoadFirstDispatcher(
	//				"src/main/resources/data/sorted_data.csv");
			logger.info("Finished parsing");
			// Query processors
			List<AbstractQueryProcessor> processors = new ArrayList<>();
			// Add you query processor here
			
//			processors.add(new StupidAveragePrice(measure));
//			processors.add(new AverageQuery(measure));
//			processors.add(new IncrementalAverage(measure));
//			processors.add(new RouteMembershipProcessor(measure));
			processors.add(new BloomFiltres(measure));

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
	/*		
			float x1=(float) -73.971138;
			float y1=(float)40.75898;
			float x2=(float)-73.972206;
			float y2=(float)40.752502;
			String l1="6BA29E9A69B10F218C1509BEDD7410C2";
			*/
//			recordTest= RouteMembershipProcessor.getRecord();
			recordTest = BloomFiltres.getRecord();
			


		
	}

    @Benchmark
    public void testMethod1() {
 //   	System.out.println("Recherche de la Route : " + RouteMembershipProcessor.checkroute(recordTest));
    	System.out.println("Il est dans la liste : " + BloomFiltres.contain(recordTest));
    }


    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(MyBenchmark.class.getSimpleName())
                .build();
        new Runner(opt).run();
    }
}