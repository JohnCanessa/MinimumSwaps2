import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 
 */
public class MinimumSwaps2 {


    /**
     * Complete the minimumSwaps function below.
     * Based on Selection Sort.
     */
    static int minimumSwaps0(int[] arr) {

        // **** initialization ****
        int n       = arr.length;
        int swaps   = 0;
        int min     = 0;

        // **** one by one move boundary of unsorted subarray ****
        for (int i = 0; i < n - 1; i++) {

            // **** check if this element is in place ****
            if (arr[i] == i + 1) continue;

            // **** min is the index of the smallest element 
            //      with an index greater or equal to i ****
            min = i;
            for (int j = i + 1; j < n; j++)
                if (arr[j] < arr[min])
                    min = j;
 
            // ???? ????
            System.out.println("<<< arr: " + Arrays.toString(arr) + " swapping (" + i + "," + min + ")");

            // **** swap the i-th and min-th elements ****
            int temp = arr[min];
            arr[min] = arr[i];
            arr[i] = temp;

            // **** count this swap ****
            swaps++;
        }

        // ???? ????
        System.out.println("<<< arr: " + Arrays.toString(arr));

        // **** return number of swaps ****
        return swaps;
    }


    /**
     * Complete the minimumSwaps function below.
     * Takes advantage of the values in the range [1 : n - 1]
     * Accepted by HackerRank.
     */
    static int minimumSwaps1(int[] arr) {

        // **** initialization ****
        int swaps       = 0;
        int minValue    = arr[0];
        int minIndex    = 0;

        // **** find min value (and min index) - O(n) ****
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < minValue) {
                minValue    = arr[i];
                minIndex    = i;
            }
        }

        // ???? ????
        System.out.println("<<< minValue: " + minValue + " minIndex: " + minIndex);

        // **** put smallest value into first position (if needed) ****
        if (minIndex != 0) {

            // ???? ????
            System.out.println("<<< arr: " + Arrays.toString(arr) + " swapping (" + 0 + "," + minIndex + ")");

            // **** swap ****
            int temp        = arr[minIndex];
            arr[minIndex]   = arr[0];
            arr[0]          = temp;

            // **** count this swap ****
            swaps++;
        }

        // **** traverse array one position at a time - O(n) ****
        for (int i = 1; i < arr.length; i++) {

            // **** compute position for move (i <-> pos) ****
            int pos = arr[i] - minValue;

            // **** loop swapping elements ****
            while (arr[i] != arr[pos]) {

                // ???? ????
                System.out.println("<<< arr: " + Arrays.toString(arr) + " swapping (" + i + "," + pos + ")");

                // **** swap ****
                int temp    = arr[i];
                arr[i]      = arr[pos];
                arr[pos]    = temp;

                // **** count this swap ****
                swaps++;

                // **** update position for move ****
                pos = arr[i] - minValue;
            }
        }

        // ???? ????
        System.out.println("<<< arr: " + Arrays.toString(arr));

        // **** return number of swaps ****
        return swaps;
    }


    /**
     * Complete the minimumSwaps function below.
     * Takes advantage of the values in the range [1 : n - 1]
     * Optimized approach.
     * Accepted by HackerRank.
     */
    static int minimumSwaps(int[] arr) {

        // **** initialization ****
        int swaps   = 0;
        int i       = 0;
        int temp;

        // **** ****
        while (i < arr.length) {

            // **** swap (if needed) ****
            if (arr[i] != i + 1) {

                // ???? ????
                System.out.println("<<< arr: " + Arrays.toString(arr) + " swapping (" + i + "," + (arr[i] - 1) + ")");

                // **** swap ****
                temp            = arr[i];
                arr[i]          = arr[arr[i] - 1];
                arr[temp - 1]   = temp;

                // **** count this swap ****
                swaps++;
            } else i++;
        }

        // ???? ????
        System.out.println("<<< arr: " + Arrays.toString(arr));

        // **** return number of swaps ****
        return swaps;
    }


    /**
     * Test scaffold.
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        
        // **** open buffered reader ****
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // **** read and discard line holding # of elements ****
        br.readLine();

        // **** read unsorted integers into array ****
        int[] arr = Arrays.stream(br.readLine().trim().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();

        // **** close buffered reader ****
        br.close();

        // ???? ????
        // System.out.println("main <<< arr: " + Arrays.toString(arr));

        // **** make a copy of the array (arr will be sorted) ****
        int[] baseArr = Arrays.copyOf(arr, arr.length);

        // **** call function of interest and display result ****
        System.out.println("main <<< swaps: " + minimumSwaps0(arr));

        // **** make a copy of the base array ****
        arr = Arrays.copyOf(baseArr, baseArr.length);

        // **** call function of interest and display result ****
        System.out.println("main <<< swaps: " + minimumSwaps1(arr));

        // **** make a copy of the base array ****
        arr = Arrays.copyOf(baseArr, baseArr.length);

        // **** call function of interest and display result ****
        System.out.println("main <<< swaps: " + minimumSwaps(arr));
    }
}