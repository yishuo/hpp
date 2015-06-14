package fr.tse.fi2.hpp.labs.queries.impl.lab3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.tse.fi2.hpp.labs.queries.AbstractQueryProcessor;

/*
 * Récrire la fonction de output.
 */
public class ResultWriter implements Runnable{
	
	final static Logger logger = LoggerFactory.getLogger(AbstractQueryProcessor.class);

	private BufferedWriter outputWriter;
	private int id;
	private final BlockingQueue<String> resultqueue;
	
	public ResultWriter(int id, BlockingQueue<String> resultqueue){
		super();
		this.id = id;
		this.resultqueue = resultqueue;
		try {
			outputWriter = new BufferedWriter(new FileWriter(new File(
					"result/query" + id + resultqueue + ".txt")));
		} catch (IOException e) {
			logger.error("Cannot open output file for " + id, e);
			System.exit(-1);
		}
	}
	
	protected void writeLine(String line) {
		try {
			outputWriter.write(line);
			outputWriter.newLine();
		} catch (IOException e) {
			logger.error("Could not write new line for query processor " + id
					+ ", line content " + line, e);
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		// Recuperer dans la queue
		// Ecrire
		while(true){
			try{
				String line = resultqueue.take();
				if(line.equals("DIE!!!")){
					break;
				}
				writeLine(line);
			}catch(InterruptedException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		finish();
	}
	
	protected void finish() {
		// Close writer
		try {
			outputWriter.flush();
			outputWriter.close();
		} catch (IOException e) {
			logger.error("Cannot property close the output file for query "
					+ id, e);
		}
	}
}
