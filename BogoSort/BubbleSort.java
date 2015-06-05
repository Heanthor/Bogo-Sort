
public class BubbleSort implements Sorter {

	@Override
	public void sort(int[] a) {
		int outer, inner;
		for (outer = a.length - 1; outer > 0; outer--) {
			for (inner = 0; inner < outer; inner++) {
				if (a[inner] > a[inner + 1]) {
					swap( inner, inner + 1, a);
				}
			}
		}
	}

	private void swap(int x, int y, int[] a) {
		int temp= a[x];
		a[x]= a[y];
		a[y]= temp;
	}

	@Override
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
