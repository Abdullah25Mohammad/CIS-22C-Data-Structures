import java.io.PrintWriter;
import java.util.Arrays;

// RunLengthTestCase represents a test case for the NaturalMergeSorter class's
// getSortedRunLength() function.
public class RunLengthTestCase {
   public int[] array;
   public int arrayLength;
   public int startIndex;
   public int expectedReturnValue;

   public RunLengthTestCase(int[] array, int len, int start, 
      int expectedRet) {
	   this.array = array;
	   arrayLength = len;
	   startIndex = start;
	   expectedReturnValue = expectedRet;
   }
   
   // Executes the test case. If the test case passes, a message that starts
   // with "PASS" is printed and true is returned. Otherwise a message that
   // starts with "FAIL" is printed and false is returned.
   public final boolean execute(PrintWriter testFeedback) {
      // Create a NaturalMergeSorter instance
      NaturalMergeSorter userSorter = new NaturalMergeSorter();
      
      // Call the getSortedRunLength() method with the test case parameters
      int userRetVal = userSorter.getSortedRunLength(
         array, arrayLength, startIndex);
      
      // The test passed only if the actual return value equals the expected
      // return value
      final boolean passed = (userRetVal == expectedReturnValue);
      
      // Show a message about the test case's results
      if(passed) {
        testFeedback.write("PASS: ");
      }
      else {
        testFeedback.write("FAIL: ");
      }
      testFeedback.write("getSortedRunLength()" + "\n");
      testFeedback.write("  Array: " + Arrays.toString(array) + "\n");
      testFeedback.write("   Start index:           " + startIndex + "\n");
      testFeedback.write("   Expected return value: " + expectedReturnValue);
      testFeedback.write("\n");
      testFeedback.write("   Actual return value:   " + userRetVal + "\n");
      
      return passed;
   }

   // Writes comma-separated elements to PrintWriter
   public final void writeArray(PrintWriter output) {
      // Output occurs only if at least one array element exists
      if (arrayLength > 0)
      {
         // Write the first element without a comma
         output.write("" + array[0]);
         
         // Write each remaining element preceded by a comma
         for (int i = 1; i < arrayLength; i++) {
            output.write(", " + array[i]);
         }
      }
   }
}