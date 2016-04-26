package by.epamlab;

import java.util.Random;

public class Eracer extends Thread{
		
	@Override
	public void run() {
		try {
			Random rnd = new Random();
			System.out.println("ERACE START");
			sleep(rnd.nextInt(rnd.nextInt(10000)));
			System.out.println("ERACE STOP");
		} catch (InterruptedException e) {
			System.out.println("ERACE INTERRUPTED");			
		}		
	}
}
