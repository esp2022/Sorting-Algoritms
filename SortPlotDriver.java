import java.util.Random;
import java.util.Scanner;

/**
 * A class that provides a command-line interface for testing the various
 * sorting algorithms implemented in the Sorting class.
 */
public class SortPlotDriver {

    /**
     * Prompts the user for the size of the input data and generates an array
     * of random integers with the specified size.
     *
     * @return an array of random integers with the specified size
     */
    private static int[] generateRandomData() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the size of the input data: ");
        int size = scanner.nextInt();
        scanner.close();

        Random random = new Random();
        int[] data = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = random.nextInt(size);
        }

        return data;
    }

    /**
     * Prints the contents of an integer array to the console.
     *
     * @param array the array to be printed
     */
/*     private static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    } */

    /**
     * Main method for the SortDriver class. Prompts the user to select a sorting
     * algorithm and the size of the input data, generates the input data, sorts
     * it accordingly, and prints the sorted data and the number of comparisons.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("s: Selection Sort");
        System.out.println("m: Merge Sort");
        System.out.println("h: Heap Sort");
        System.out.println("qf: Quick Sort (first pivot)");
        System.out.println("qr: Quick Sort (random pivot)");
        System.out.print("Enter the sorting algorithm: ");
        Scanner scanner = new Scanner(System.in);
        String algorithm = scanner.nextLine();

        int[] data = generateRandomData();

        // long startTime = System.currentTimeMillis();

        switch (algorithm) {
            case "s":
                System.out.println("Selection Sort");
                Sorting.selectionSort(data);
                //printArray(data);
                System.out.println("#Selection-sort comparisons: " + Sorting.getNumComparisons());
                break;
            case "m":
                System.out.println("Merge Sort");
                Sorting.mergeSort(data);
                //printArray(data);
                System.out.println("#Merge-sort comparisons: " + Sorting.getNumComparisons());
                break;
            case "h":
                System.out.println("Heap Sort");
                Sorting.heapSort(data);
                //printArray(data);
                System.out.println("#Heap-sort comparisons: " + Sorting.getNumComparisons());
                break;
            case "qf":
                System.out.println("Quick Sort (first pivot)");
                Sorting.quickSortFirstPivot(data);
                //printArray(data);
                System.out.println("#Quick-sort comparisons: " + Sorting.getNumComparisons());
                break;
            case "qr":
                System.out.println("Quick Sort (random pivot)");
                Sorting.quickSortRandomPivot(data);
                //printArray(data);
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
}
