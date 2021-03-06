package by.epamlab;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Sort {
	private static final int NUMBER_CORE = Runtime.getRuntime().availableProcessors();
    private static ExecutorService threadPool = Executors.newFixedThreadPool(NUMBER_CORE);
    		
	public static void quicksort(int[] arrayToSort) {
		final AtomicInteger countThread = new AtomicInteger(1);
        threadPool.submit((new QuickSort(arrayToSort, 0, arrayToSort.length - 1, countThread)));
        try {
            synchronized (countThread) {
                countThread.wait();                
            }
            threadPool.shutdown();            
        } catch (InterruptedException e) {
            e.printStackTrace();
        } 
	}
	
	private static class QuickSort implements Runnable{
		private final int[] array;
		private final int first;
		private final int last;
		private final AtomicInteger countThread;
		
		public QuickSort(int[] array, int first, int last, AtomicInteger countThread) {
			this.array = array;
			this.first = first;
			this.last = last;
			this.countThread = countThread;
		}

		@Override
		public void run() {
			quicksort(first, last);
			synchronized (countThread) {
                if (countThread.getAndDecrement() == 1) {
               		countThread.notify();               		
               	}          
            }		
		}
		
		private void quicksort(int first, int last) {
			if(last - first == 1) {
				if(array[last] < array[first]) {
					swap(first, last);
				}				
			} else if (first < last) {
				int middleIndex = partitionArray(first, last);
				
				if (countThread.get() >= NUMBER_CORE) {
					quicksort(first, middleIndex - 1);
					quicksort(middleIndex + 1, last);
				} else {
					countThread.getAndAdd(2);
					threadPool.submit((new QuickSort(array, first, middleIndex - 1, countThread)));
					threadPool.submit((new QuickSort(array, middleIndex + 1, last, countThread)));
					
					
				}
			}			
	    }
		 
		private int partitionArray(int first, int last) {
			int middleIndex = (first + last) / 2;
			int rootValue = array[middleIndex];
			swap(middleIndex, first);
			
			int scanUp = first + 1;
			int scanDown = last;
			
			do {
				
				while(scanUp <= scanDown && array[scanUp] <= rootValue) {
					scanUp++;
				}
				
				while(array[scanDown] > rootValue) {
					scanDown--;
				}
				
				if(scanUp < scanDown) {
					swap(scanUp, scanDown);
				}
				
			} while(scanUp < scanDown);
			
			array[first] = array[scanDown];
			array[scanDown] = rootValue;
			
			return scanDown;
		}
			
		private void swap (int left, int right) {
			int temp = array[left];
			array[left] = array[right];
			array[right] = temp;
		}
		
	}
	
}
