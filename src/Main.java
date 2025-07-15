import static sort.SortAlgorithm.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Array: " + numsToString());

        bubbleSort();
        System.out.println("Bubble Sort: " + numsToString());
        resetNums();

        selectionSort();
        System.out.println("Selection Sort: " + numsToString());
        resetNums();

        insertionSort();
        System.out.println("Insertion Sort: " + numsToString());
        resetNums();

        mergeSort();
        System.out.println("Merge Sort: " + numsToString());
        resetNums();

        quickSort();
        System.out.println("Quick Sort: " + numsToString());
        resetNums();

        System.out.println("Unsorted Array: " + numsToString());
    }
}