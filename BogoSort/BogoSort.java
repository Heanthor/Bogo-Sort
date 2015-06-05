import java.util.*;

//The best sorting algorithm
public class BogoSort {

	public static void main(String[] args) {
		BogoSort b = new BogoSort();
		b.bogoSort(b.input());
	}

	public int[] input() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Array size?: ");
		int size = sc.nextInt();
		int[] container = new int[size];

		for (int i = 0; i < size; i++) {
			System.out.println("Enter element " + (i + 1) + ": ");
			container[i] = sc.nextInt();
		}
		System.out.println("Done. Starting BogoSort.");

		sc.close();
		return container;
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
			System.out.println("Try number: " + tries);
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
		System.out.println("Array Sorted. Your sorted array is: " + 
				toReturn + ". Took " + tries + " tries");
	}
}
