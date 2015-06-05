import java.text.NumberFormat;
import java.util.HashSet;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

//The best sorting algorithm, now threaded.
public class MultiThreadedBogoSort extends Thread {

	long startTime = System.currentTimeMillis();
	int tag = 0;
	int[] container;

	public MultiThreadedBogoSort(int i, int[] container) {
		tag = i;
		this.container = container;
	}

	public static void main(String[] args) {

		//Unified input for all threads
		Scanner sc = new Scanner(System.in);
		System.out.println("Array size?: ");	
		int size = sc.nextInt();
		int[] container = new int[size];

		for (int i = 0; i < size; i++) {
			System.out.println("Enter element " + (i + 1) + ": ");
			String in = sc.next();

			try {
				int inI = Integer.parseInt(in);
				container[i] = inI;
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Try again.");
				i--;
			}
		}
		System.out.println("Done. Starting BogoSort.");

		sc.close();
		//Launches 5 threads for computation
		for (int i = 1; i < 6; i++) {
			MultiThreadedBogoSort b = new MultiThreadedBogoSort(i, container);
			b.start();
		}
	}

	public void run() {
		bogoSort(container);
	}

	public boolean isSorted(int[] container) {
		for (int i = 1; i < container.length; i++) {
			if (!(container[i - 1] <= container[i])) {
				return false;
			}
		}
		return true;
	}

	public int randomIndex(int[] container) {
		//Generating random indexes
		int size = container.length - 1;

		Random rand = new Random();
		int randomIndex = rand.nextInt((size + 1));

		return randomIndex;
	}

	public void bogoSort(int[] container) {
		int[] newContainer = new int[container.length];
		for (int i = 0; i < container.length; i++) {
			newContainer[i] = container[i];
		}

		int tries = 1;
		//Doing the sort
		while (!isSorted(newContainer)) {

			//Assures no index will be done twice
			Set<Integer> done = new HashSet<Integer>();

			//Sorting
			for (int i = 0; i < container.length; i++) {
				//Generating random indexes
				int randomIndex = -1;

				//Assures no index will be done twice
				boolean b = true;
				while (b == true) {
					randomIndex = randomIndex(newContainer);

					if (done.contains(randomIndex)) {
						//Generates it again
						b = true;
					} else {
						//Breaks the loop
						b = false;
					}
				}

				//Placing elements
				newContainer[i] = container[randomIndex];
				done.add(randomIndex);
			}
			//Counts how many iterations needed
			//Mod 100,000
			if (tries % 100000 == 0) {
				System.out.println("Thread " + tag + " completed " + 
						NumberFormat.getNumberInstance(Locale.US).
						format(tries) + " tries.");
			}
			tries++;
		}

		String toReturn = "[";
		for (int i = 0; i < newContainer.length; i++) {
			toReturn += newContainer[i];
			if (i == newContainer.length - 1) {
				toReturn += "]";
			} else {
				toReturn += " ";
			}
		}

		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;

		System.out.println("Array sorted by thread " + tag + 
				". Your sorted array is: " + 
				toReturn + ". Took " + NumberFormat.getNumberInstance(
						Locale.US).format(tries) + " tries and " +
				NumberFormat.getNumberInstance(Locale.US).format(totalTime) + 
				" milliseconds (~" + (int) totalTime / 1000 + " seconds).");
		//First thread that gets here terminates the rest
		System.exit(0);
	}
}
