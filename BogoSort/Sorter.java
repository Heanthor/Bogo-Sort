
/**
 * An object that sorts an int array somehow.
 * @author Reed
 *
 */
public interface Sorter {

	/**
	 * Runs the associated sorting method. This allows each type of sorting method
	 * to take the correct associated parameters.
	 * @param container The array to be sorted.
	 */
	public void sort(int[] container);
	
	/**
	 * Prints the array in a console-friendly format.
	 * @param container
	 * @return The sorted array string. Remember to print this!
	 */
	public String printArray(int[] container);
}
