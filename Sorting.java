import java.util.Random;

/**
 * The Sorting class provides several static methods for sorting integer arrays
 * using different algorithms.
 * The class also keeps track of the number of comparisons made during sorting.
 */
public class Sorting {
    // Private static variable to keep track of the number of comparisons made during sorting
    private static long numComparisons;

    /**
     * Constructor for the Sorting class.
     * Initializes the numComparisons variable to 0.
     */
    public Sorting() {
        numComparisons = 0;
    }

    /**
     * Returns the number of comparisons made during sorting.
     * 
     * @return the number of comparisons made during sorting.
     */
    public static long getNumComparisons() {
        return numComparisons;
    }

    /**
     * Resets the number of comparisons made during sorting to 0.
     */
    public static void resetComparisons() {
        numComparisons = 0;
    }

    /**
     * Sorts an integer array arr using the Selection Sort algorithm.
     * 
     * @param arr the array to be sorted.
     */
    public static void selectionSort(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                numComparisons++;
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }

            int temp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = temp;
        }
    }

    /**
     * Sorts an integer array arr using the Merge Sort algorithm.
     * 
     * @param arr the array to be sorted.
     */
    public static void mergeSort(int[] arr) {
        mergeSortHelper(arr, 0, arr.length - 1);
    }

    /**
     * Helper method for mergeSort. Recursively sorts a subarray of arr using the
     * Merge Sort algorithm.
     * 
     * @param arr   the array to be sorted.
     * @param left  the index of the leftmost element in the subarray.
     * @param right the index of the rightmost element in the subarray.
     */
    private static void mergeSortHelper(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSortHelper(arr, left, mid);
            mergeSortHelper(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    /**
     * Merges two sorted subarrays of arr into a single sorted subarray.
     * 
     * @param arr   the array containing the two subarrays to be merged.
     * @param left  the index of the leftmost element in the first subarray.
     * @param mid   the index of the rightmost element in the first subarray.
     * @param right the index of the rightmost element in the second subarray.
     */
    private static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j <= right) {
            numComparisons++;
            if (arr[i] < arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        while (j <= right) {
            temp[k++] = arr[j++];
        }

        for (int p = 0; p < temp.length; p++) {
            arr[left + p] = temp[p];
        }
    }

    /**
     * Sorts an integer array arr using the Heap Sort algorithm.
     * 
     * @param arr the array to be sorted.
     */
    public static void heapSort(int[] arr) {
        int n = arr.length;

        // Build max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // Extract elements from heap
        for (int i = n - 1; i >= 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }
    }

    /**
     * Helper method for heapSort. Creates a max heap from a subarray of arr.
     * 
     * @param arr the array containing the subarray to be heapified.
     * @param n   the size of the subarray.
     * @param i   the index of the root of the subarray.
     */
    private static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && arr[l] > arr[largest]) {
            numComparisons++;
            largest = l;
        }

        if (r < n && arr[r] > arr[largest]) {
            numComparisons++;
            largest = r;
        }

        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            heapify(arr, n, largest);
        }
    }

    /**
     * Sorts an integer array arr using the Quick Sort algorithm with the first
     * element as the pivot.
     * 
     * @param arr the array to be sorted.
     */
    public static void quickSortFirstPivot(int[] arr) {
        quickSortFirstPivotHelper(arr, 0, arr.length - 1);
    }

    /**
     * Helper method for quickSortFirstPivot. Recursively sorts a subarray of arr
     * using Quick Sort with the first element as the pivot.
     * 
     * @param arr  the array to be sorted.
     * @param low  the index of the leftmost element in the subarray.
     * @param high the index of the rightmost element in the subarray.
     */
    private static void quickSortFirstPivotHelper(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partitionFirstPivot(arr, low, high);
            quickSortFirstPivotHelper(arr, low, pivotIndex - 1);
            quickSortFirstPivotHelper(arr, pivotIndex + 1, high);
        }
    }

    /**
     * Partitions a subarray of arr using the first element as the pivot.
     * 
     * @param arr  the array containing the subarray to be partitioned.
     * @param low  the index of the leftmost element in the subarray.
     * @param high the index of the rightmost element in the subarray.
     * @return the index of the pivot after partitioning.
     */
    private static int partitionFirstPivot(int[] arr, int low, int high) {
        int pivot = arr[low];
        int i = low + 1;
        for (int j = low + 1; j <= high; j++) {
            numComparisons++;
            if (arr[j] < pivot) {
                int temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
                i++;
            }
        }

        int temp = arr[low];
        arr[low] = arr[i - 1];
        arr[i - 1] = temp;
        return i - 1;
    }

    /**
     * Sorts an integer array arr using the Quick Sort algorithm with a random
     * pivot.
     * 
     * @param arr the array to be sorted.
     */
    public static void quickSortRandomPivot(int[] arr) {
        quickSortRandomPivotHelper(arr, 0, arr.length - 1);
    }

    /**
     * Helper method for quickSortRandomPivot. Recursively sorts a subarray of arr
     * using Quick Sort with a random pivot.
     * 
     * @param arr  the array to be sorted.
     * @param low  the index of the leftmost element in the subarray.
     * @param high the index of the rightmost element in the subarray.
     */
    private static void quickSortRandomPivotHelper(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partitionRandomPivot(arr, low, high);
            quickSortRandomPivotHelper(arr, low, pivotIndex - 1);
            quickSortRandomPivotHelper(arr, pivotIndex + 1, high);
        }
    }

    /**
     * Partitions a subarray of arr using a random element as the pivot.
     * 
     * @param arr  the array containing the subarray to be partitioned.
     * @param low  the index of the leftmost element in the subarray.
     * @param high the index of the rightmost element in the subarray.
     * @return the index of the pivot after partitioning.
     */
    private static int partitionRandomPivot(int[] arr, int low, int high) {
        Random rand = new Random();
        int pivotIndex = rand.nextInt(high - low + 1) + low;
        int pivot = arr[pivotIndex];

        int temp = arr[high];
        arr[high] = arr[pivotIndex];
        arr[pivotIndex] = temp;

        int i = low - 1;
        for (int j = low; j < high; j++) {
            numComparisons++;
            if (arr[j] < pivot) {
                i++;
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }
}
