import java.util.Random;

/**
 * Generates an array containing a set number of integers of values 1-9999.
 * @author Reed
 * 
 */
public class RandomSetGenerator {
	private int randomNumber;
	private int[] in;
	
	/**
	 * @param min Smallest amount of values to be generated.
	 * @param max Largest amount of values to be generated.
	 */
	public RandomSetGenerator(int min, int max) {
		Random rand = new Random();
		randomNumber = rand.nextInt((max - min)+ 1) + min;
		in = new int[randomNumber];
		
		for (int i = 0; i < randomNumber; i++) {
			in[i] = rand.nextInt(9998) + 1;
		}
		System.out.println("Set of " + randomNumber + " elements created.\n");
	}
	
	/**
	 * 
	 * @return The generated set.
	 */
	public int[] getSet() {
		return in;
	}
	
	/**
	 * 
	 * @return The random number generated when object is created.
	 */
	public int getRandomNumber() {
		return randomNumber;
	}
}
