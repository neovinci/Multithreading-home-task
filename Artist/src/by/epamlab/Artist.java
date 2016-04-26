package by.epamlab;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Artist extends Thread{
		
	@Override
	public void run() {
		Random rnd = new Random();
		ExecutorService pool = Executors.newFixedThreadPool(1);					
		while(!isInterrupted()) {
			switch(rnd.nextInt(2)) {
			
				case 0: pool.submit(new Pencil());						
						break;
			
				case 1: pool.submit(new Eracer());
						break;
			}
		}
		pool.shutdownNow();
	}		
}
