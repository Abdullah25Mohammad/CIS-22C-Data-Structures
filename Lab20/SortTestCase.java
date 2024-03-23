import java.util.*;
import java.io.PrintWriter;

public class SortTestCase {
	public ArrayList<Integer> data = new ArrayList<Integer>();

	public boolean showArrayOnPass;

	public SortTestCase(final ArrayList<Integer> arrayData) {
		this.data = new ArrayList<Integer>(arrayData);
		showArrayOnPass = true;
	}

	public final boolean execute(PrintWriter testFeedback) {
		int arrLen = (int)data.size();

		// Make two copies of the array: one to sort with the grading solution
     	 // and one to sort with the user's solution
		int[] actual = new int[arrLen];
		for (int i = 0; i < arrLen; i++) {
			actual[i] = data.get(i);
		}
		int[] expected = new int[arrLen];
		for (int i = 0; i < arrLen; i++) {
	    	expected[i] = data.get(i);
	    }

		// Sort the "expected" array with the solution
		GradingSorter gradingSorter = new GradingSorter();
		gradingSorter.naturalMergeSort_Solution(expected, (int)data.size());
		// Get info about calls to merge()
		ArrayList<CallInfo> solutionMergeInfo = new ArrayList<CallInfo>(
				gradingSorter.getCallInfo("merge"));
		gradingSorter.resetCallInfo();

		// Sort the array with the user's implementation. GradingSorter's
      	// naturalMergeSort() tracks the call, then calls the parent class's
      	// naturalMergeSort() function, which is implemented by the user.
		gradingSorter.naturalMergeSort(actual, arrLen);

		// Ensure that the array is sorted
		if (!LabUtility.isArraySorted(actual, arrLen, testFeedback)) {
			// isArraySorted will have printed the failure message
			return false;
		}
		testFeedback.write("PASS: Array with " + arrLen);
		testFeedback.write(" elements is sorted." + "\n");

		// Ensure that the sorted array's elements are the same as the original
      	// array's elements (but obviously in a different order)
		Tuple<Integer,Integer,Integer> mismatch = new Tuple<Integer,Integer,Integer>(-1, -1, -1);
		if (!LabUtility.doArraysHaveSameElements(expected, actual, arrLen, mismatch)) {
			// mismatch is a (element, arr1Frequency, arr2Frequency) tuple
			int element = mismatch.getVar0();
			int originalFrequency = mismatch.getVar1();
			int finalFrequency = mismatch.getVar2();

			// Write a message indicating the problem and an example
			testFeedback.write("FAIL: Sorted array's elements are not the same ");
			testFeedback.write("as original array's elements. Ex: Element ");
			testFeedback.write(element + " occurs ");
			if (1 == originalFrequency) {
				testFeedback.write("once");
			}
			else {
				testFeedback.write(originalFrequency + " times");
			}
			testFeedback.write(" in original array, but occurs ");
			if (1 == finalFrequency) {
				testFeedback.write("once");
			}
			else {
				testFeedback.write(finalFrequency + " times");
			}
			testFeedback.write(" in the sorted array." + "\n");

			return false;
		}
		testFeedback.write("PASS: Sorted array's elements are the same as the ");
		testFeedback.write("original array's elements." + "\n");

		// Get call information for the merge() function. merge() is provided to
      	// the user and must be used during sorting.
		ArrayList<CallInfo> mergeInfo = gradingSorter.getCallInfo("merge");

		// The following check ensures that merge() is called the EXACT number of
      	// times needed, no more, no less.
		int expectedMergeCallCount = (int)solutionMergeInfo.size();
		if (expectedMergeCallCount != mergeInfo.size()) {
			testFeedback.write("FAIL: NaturalMergeSorter's merge() method ");
			testFeedback.write("was called a different number of times (");
			testFeedback.write(mergeInfo.size() + ") than expected (");
			testFeedback.write(expectedMergeCallCount + "). ");
			testFeedback.write("Please make sure that the lengths of two ");
			testFeedback.write("consecutive runs are computed, then the runs are ");
			testFeedback.write("merged. Also, after merging two consecutive runs A ");
			testFeedback.write("and B, the next run starts after B. That is, do not ");
			testFeedback.write("use the result of merging A and B as the first run ");
			testFeedback.write("for the next merge." + "\n");
			return false;
		}
		testFeedback.write("PASS: merge() was called " + expectedMergeCallCount);
		testFeedback.write((1 == expectedMergeCallCount) ? " time" : " times");
		testFeedback.write(" when sorting array ");
		if (showArrayOnPass) {
			testFeedback.write("[");
			SortTestCase.writeArray(testFeedback, data, ", ");
			testFeedback.write("]." + "\n");
		}
		else {
			testFeedback.write("with " + arrLen + " elements." + "\n");
		}

		// Test passed
		return true;
	}

	public final int[] getArrayCopy() {
		int[] arrayCopyRaw = new int[data.size()];
		for (int i = 0; i < (int)data.size(); i++)
		{
			arrayCopyRaw[i] = data.get(i);
		}
		return arrayCopyRaw;
	}

	public static void writeArray(PrintWriter output, final java.util.ArrayList<Integer> arr) {
		writeArray(output, arr, ",");
	}

	// Writes comma-separated array elements to the output stream
	public static void writeArray(PrintWriter output, final ArrayList<Integer> arr, String delimiter) {
		// Output occurs only if at least one element exists
		if (arr.size() > 0) {
			// Write the first element without delimiter
			output.write((arr.get(0)).toString());

			// Write each remaining element preceded by the delimiter
			for (int i = 1; i < (int)arr.size(); i++) {
				output.write(delimiter + arr.get(i));
			}
		}
	}
}