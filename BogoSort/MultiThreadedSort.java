import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.Scanner;

/**
 * Aims to sort a random set of data with a given sorting algorithm. Requires an
 *  object that contains the sorting algorithm.
 * By design, one thread will have much less work to do than the others, 
 * if the number of elements cannot be evenly divided by the number of threads.
 * @author Reed
 *
 */
public class MultiThreadedSort extends Thread implements Sorter {
	private int[] container; //Array to be sorted
	private Sorter sort; //Sort object to perform the search
	private int tag;

	/**
	 * 
	 * @param container The array to be sorted.
	 * @param s The Sort object that performs the sort.
	 */
	public MultiThreadedSort(int[] container, Sorter s, int t) {
		this.container = container;
		this.sort = s;
		this.tag = t;
	}

	public static void main(String[] args) {
		//Timing
		long startTime = System.currentTimeMillis();

		MergeSort m = new MergeSort(); // Does the calculations, change for different sorting algorithm
		RandomSetGenerator r = new RandomSetGenerator(40000, 40000);
		Scanner s = new Scanner(System.in);

		int[] container = r.getSet();

		System.out.print("How many threads? ");
		int numThreads = 1;
		try { //Protects against crashes at least
			numThreads = s.nextInt();
		} catch (Exception e) {
			System.out.println("Invalid input.");
			System.exit(1);
		}
		s.close();

		if (numThreads > container.length) {
			System.out.println("You cannot have more threads than elements.");
			System.exit(1);
		}
		/* Divide the array into equal segments for each thread to sort */
		ArrayList<int[]> lists = new ArrayList<int[]>();

		for (int i = 0; i < numThreads; i++) {
			lists.add(new int[container.length / numThreads]);
		}

		if (container.length % numThreads != 0) { //Adds remainder overflow array
			lists.add(new int[container.length % numThreads]);
		} 

		int currentIndex = 0; //Iterate through original array
		int[] deepCopy = new int[container.length];
		System.arraycopy(container, 0, deepCopy, 0, container.length); //Copys array
		int max = 9999 / numThreads;

		Iterator<int[]> iter = lists.iterator();
		for (int i = 0; i < lists.size(); i++) { //Populate lists
			int[] temp = iter.next();
			int tempIndex = 0;

			while (temp[temp.length - 1] == 0) {
				if (currentIndex < deepCopy.length) {
					if (deepCopy[currentIndex] <= max && deepCopy[currentIndex] > 0) { //Successful assignment /* UP RANGE if the array isn't filled, keep looping */
						temp[tempIndex] = deepCopy[currentIndex];
						deepCopy[currentIndex] = 0;
						tempIndex++; currentIndex++;
					} else {
						currentIndex++; //Unsuccessful copy
					}
				} else {
					currentIndex = 0;
					max += max;
				}
			}
			max = max(temp);
			currentIndex = 0;//Reset to start over
		}

		/* Large array is now sorted into numThreads smaller arrays, plus remainder if needed.*/
		for (int i = 0; i < numThreads; i++) {
			MultiThreadedSort mThread = new MultiThreadedSort(lists.get(i), m, i);
			System.out.println("Thread " + (i + 1) + " started.");
			mThread.run();
		}

		/* Merge the arrays back together */
		int[] sorted = new int[container.length];
		Iterator<int[]> iter2 = lists.iterator();
		int sortedIndex = 0;
		for (int i = 0; i < lists.size(); i++) {
			currentIndex = 0;
			int[] temp = iter2.next();

			while (currentIndex < temp.length) {
				sorted[sortedIndex] = temp[currentIndex];
				sortedIndex++; currentIndex++;
			}
		}
		System.out.println("Sorting completed");
		
		//Timing
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;

		System.out.println(m.printArray(sorted) + "\n");

		//Adds commas to output
		System.out.println("An array of " + NumberFormat.getNumberInstance(
				Locale.US).format(r.getRandomNumber()) + " (10,000 - 100,000)" +
				" random elements sorted in " + NumberFormat.getNumberInstance(
						Locale.US).format(totalTime) + " milliseconds (~" + 
						(int) totalTime / 1000 + " seconds).");
	}

	/** 
	 * 
	 * @param a - The int array
	 * @return The maximum value in the int array.
	 */
	private static int max(int[] a) {
		int max = 0;

		for (int i: a) {
			if (i > max) {
				max = i;
			}
		}
		return max;
	}

	private static int min(int[] a) {
		int min = Integer.MAX_VALUE;

		for (int i: a) {
			if (i < min) {
				min = i;
			}
		}

		return min;
	}

	public void run() {
		sort.sort(container);
	}

	public void sort(int[] container) {
		sort.sort(container);
	}

	@Override
	public String printArray(int[] container) {
		return sort.printArray(container);
	}
}
