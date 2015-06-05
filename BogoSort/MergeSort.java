import java.text.NumberFormat;
import java.util.Locale;

public class MergeSort implements Sorter {

	public static void main(String[] args) {
		//Timing
		long startTime = System.currentTimeMillis();
		
		RandomSetGenerator r = new RandomSetGenerator(40000, 40000);
		int[] in = r.getSet();
		
		System.out.println("Generated array.");
		MergeSort m = new MergeSort();
		m.sort(in);
		
		System.out.println("Sorting done.");
		//Calculating time
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		
		String toReturn = m.printArray(in);
		
		System.out.println(toReturn);
		//Adds commas to output
		System.out.println("An array of " + NumberFormat.getNumberInstance(
				Locale.US).format(r.getRandomNumber()) + " (10,000 - 100,000)" +
				" random elements sorted in " + NumberFormat.getNumberInstance(
						Locale.US).format(totalTime) + " milliseconds (~" + 
						(int) totalTime / 1000 + " seconds).");
	}
	
	public void merge(int[] in, int startIndex, int endIndex, int midIndex){
		//Declarations
		System.out.println("Merge " + startIndex + " start, " + endIndex + " end, " +
		midIndex + " mid.");
		
		int size = endIndex - startIndex + 1, left = startIndex,
				right = midIndex + 1;
		
		int[] temp = new int[in.length];
		
		for (int i = 0; i < size; i++) {
			if (left > midIndex) {
				temp[i] = in[right++];
			} else {
				if (right > endIndex || in[left] < in[right]) {
					temp[i] = in[left++];
				} else {
					temp[i] = in[right++];
				}
			}
		}
		for (int i = 0; i < size; i++) {
			in[startIndex + i] = temp[i];
		}
	}
	
	/**
	 * 
	 * @param in The array to be sorted.
	 * @param startIndex Beginning of the region to be sorted.
	 * @param endIndex End of the region. Initially, in.length - 1.
	 */
	public void mergeSort(int[] in, int startIndex, int endIndex) {
		int mid = (startIndex + endIndex) / 2;
		
		if (startIndex != endIndex) {
			mergeSort(in, startIndex, mid);
			mergeSort(in, mid + 1, endIndex);
			merge(in, startIndex, endIndex, mid);
		}
	}

	public void sort(int[] container) {
		mergeSort(container, 0, container.length - 1);
	}
	
	public String printArray(int[] in) {
		String toReturn = "[";
		for (int i = 0; i < in.length; i++) {
			toReturn += in[i];
			if (i % 50 == 0) {
				toReturn += "\n";
			}
			if (i == in.length - 1) {
				toReturn += "]";
			} else {
				toReturn += " ";
			}
		}
		return toReturn;
	}
}
