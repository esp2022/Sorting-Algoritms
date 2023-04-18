import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The SortDriver class provides a command-line interface for testing various
 * sorting algorithms on input data provided in a text file.
 */
public class SortDriver {

    /**
     * Reads the input data from a file and returns an array of integers
     * representing the data in the file.
     *
     * @param fileName the name of the input file
     * @return an array of integers representing the data in the input file
     */
    private static int[] readInputFile(String fileName) {
        int[] inputData = null;
        try {
            Scanner scanner = new Scanner(new File(fileName));
            String inputString = scanner.nextLine(); // read the entire line
            String[] inputTokens = inputString.trim().split("\\s+"); // split by whitespace
            inputData = new int[inputTokens.length];
            for (int i = 0; i < inputTokens.length; i++) {
                inputData[i] = Integer.parseInt(inputTokens[i]);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found: " + fileName);
            System.exit(1);
        }
        return inputData;
    }

    /**
     * Main method for the SortDriver class. Prompts the user to select a sorting
     * algorithm and sorts the input data accordingly.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java SortDriver [input-file]");
            System.exit(1);
        }

        String fileName = args[0];
        int[] inputData = readInputFile(fileName);

        System.out.println("selection-sort (s) merge-sort (m) heap-sort (h) quick-sort-fp (q) quick-sort-rp (r)");
        System.out.print("Enter the algorithm: ");
        Scanner scanner = new Scanner(System.in);
        String algorithm = scanner.nextLine();

        // long startTime = System.currentTimeMillis();

        switch (algorithm) {
            case "s":
                System.out.println("Selection Sort");
                Sorting.selectionSort(inputData);
                printArray(inputData);
                System.out.println("#Selection-sort comparisons: " + Sorting.getNumComparisons());
                break;
            case "m":
                System.out.println("Merge Sort");
                Sorting.mergeSort(inputData);
                printArray(inputData);
                System.out.println("#Merge-sort comparisons: " + Sorting.getNumComparisons());
                break;
            case "h":
                System.out.println("Heap Sort");
                Sorting.heapSort(inputData);
                printArray(inputData);
                System.out.println("#Heap-sort comparisons: " + Sorting.getNumComparisons());
                break;
            case "q":
                System.out.println("Quick Sort (first pivot)");
                Sorting.quickSortFirstPivot(inputData);
                printArray(inputData);
                System.out.println("#Quick-sort comparisons: " + Sorting.getNumComparisons());
                break;
            case "r":
                System.out.println("Quick Sort (random pivot)");
                Sorting.quickSortRandomPivot(inputData);
                printArray(inputData);
                System.out.println("#Quick-sort comparisons: " + Sorting.getNumComparisons());
                break;
            default:
                System.out.println("Invalid algorithm: " + algorithm);
                System.exit(1);
        }

        // long endTime = System.currentTimeMillis();
        // System.out.println("Time taken: " + (endTime - startTime) + " milliseconds");

        scanner.close();
    }

    /**
     * Prints the contents of an integer array to the console.
     *
     * @param array the array to be printed
     */
    private static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

}
