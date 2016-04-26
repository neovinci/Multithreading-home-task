package by.epamlab;

import java.util.Random;

public class Pencil extends Thread{
		
	@Override
	public void run() {
		try {
			Random rnd = new Random();
			System.out.println("DRAW START");
			sleep(rnd.nextInt(10000));
			System.out.println("DRAW STOP ");
		} catch (InterruptedException e) {
			System.out.println("DRAW INTERRUPTED");;
		}		
	}	
}
