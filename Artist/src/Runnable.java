import java.util.Scanner;

import by.epamlab.Artist;

public class Runnable {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.println("The artist begins to paint");
		System.out.println("Enter any key to stop");
		Artist artist = new Artist();
		artist.start();
		if(scanner.hasNext()) {
			artist.interrupt();
			System.out.println("The artist finished writing picture");
		}
	}
}
