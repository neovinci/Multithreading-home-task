
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

import by.epamlab.Sort;

public class Running {

	public static void main(String[] args) {
		Random rnd = new Random();
		final int MAX_SIZE = 10;
		int[] array = new int[MAX_SIZE];
		for (int i = 0; i < MAX_SIZE; i++) {
			array[i] = rnd.nextInt();			
		}
	
		System.out.println();
		int[] array1 = array.clone();
		int[] array2 = array.clone();
		
		Date start = new Date();
		
		Arrays.sort(array2);
		Date finish = new Date();
		System.out.println("Final! array sorted by " + (finish.getTime() - start.getTime()) + " ms");
		
		
	
		start = new Date();
		Sort.quicksort(array1);
		finish = new Date();
		System.out.println("Final! array sorted by " + (finish.getTime() - start.getTime()) + " ms");
		for(int out : array1) {
			System.out.print(out + "  ");
		}
	}

}
