public class IsArraySorted {
	public static boolean isSorted(int[] arr, int arrSize) {
		for (int i = 1; i < arrSize; i++) { 
			if (arr[i] < arr[i - 1]) {
				return false;
			}
	   }
		return true;
	}
}
