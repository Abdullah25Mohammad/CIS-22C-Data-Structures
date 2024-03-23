public class NaturalMergeSorter {
	public int getSortedRunLength(int[] array, int arrayLength, 
	   int startIndex) {
		if (startIndex < 0 || startIndex >= arrayLength) {
			return 0;
		}
		int numSorted = 1;
		for(int i = startIndex; i < arrayLength - 1; i++) {
			if(array[i] <= array[i + 1]) {
				numSorted++;
			}
			else break;
		}
		return numSorted;
	}

	public void naturalMergeSort(int[] array, int arrayLength) {
		int sortedLength = 0, secondLength;
		while (sortedLength < arrayLength) {	
			sortedLength = getSortedRunLength(array, arrayLength, 0);
			secondLength = getSortedRunLength(array, arrayLength, sortedLength);
			if (secondLength == 0) {
				break;
			}
			merge(array, 0, sortedLength - 1, sortedLength + secondLength - 1);
		}		
	}
	
	public void merge(int[] numbers, int leftFirst, int leftLast, 
	   int rightLast) {
		int mergedSize = rightLast - leftFirst + 1;
		int[] mergedNumbers = new int[mergedSize];
		int mergePos = 0;
		int leftPos = leftFirst;
		int rightPos = leftLast + 1;
      
		// Add smallest element from left or right partition to merged numbers
		while (leftPos <= leftLast && rightPos <= rightLast) {
			if (numbers[leftPos] <= numbers[rightPos]) {
				mergedNumbers[mergePos] = numbers[leftPos];
				leftPos++;
			}
			else {
				mergedNumbers[mergePos] = numbers[rightPos];
				rightPos++;
			}
			mergePos++;
		}
      
		// If left partition isn't empty, add remaining elements to mergedNumbers
		while (leftPos <= leftLast) {
			mergedNumbers[mergePos] = numbers[leftPos];
			leftPos++;
			mergePos++;
		}
      
		// If right partition isn't empty, add remaining elements to mergedNumbers
		while (rightPos <= rightLast) {
			mergedNumbers[mergePos] = numbers[rightPos];
			rightPos++;
			mergePos++;
		}
      
		// Copy merged numbers back to numbers
		for (mergePos = 0; mergePos < mergedSize; mergePos++) {
			numbers[leftFirst + mergePos] = mergedNumbers[mergePos];
		}
      
		// Free temporary array
		mergedNumbers = null;
	}
}