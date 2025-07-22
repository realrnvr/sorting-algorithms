package sort;

import java.util.Arrays;

public class SortAlgorithm {
    private final static int[] NUMS = new int[] { -1, -2, 9, 0, -1, 1, 20, 15, 10 };

    /*
    * Reset number array
    * */
    public static void resetNums() {
        int[] temp = new int[] { -1, -2, 9, 0, -1, 1, 20, 15, 10 };

        for (int i = 0; i < NUMS.length; ++i) {
            NUMS[i] = temp[i];
        }
    }

    /*
    * Stringify number array
    * */
    public static String numsToString() {
        return Arrays.toString(NUMS);
    }

    /*
     * Bubble sort
     * Time complexity:
     * Worst/Average: O(n^2)
     * Best: O(n)
     * Space complexity:
     * O(1)
     * Stable: Yes
     */
    public static void bubbleSort(int[] nums) {
        int n = nums.length;

        for (int i = n - 1; i >= 0; --i) {
            boolean isSwap = false;

            for (int j = 0; j < i; ++j) {
                if (nums[j] > nums[j + 1]) {
                    isSwap = true;
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }

            if (!isSwap)
                break;
        }
    }

    public static void bubbleSort() {
        bubbleSort(NUMS);
    }

    /*
     * Selection sort
     * Time Complexity:
     * Best/Average/Worse: O(n^2)
     * Space Complexity:
     * O(1)
     * Stable: No
     */
    public static void selectionSort(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n; ++i) {
            int minIdx = i;
            for (int j = i + 1; j < n; ++j) {
                if (nums[minIdx] > nums[j]) {
                    minIdx = j;
                }
            }

            if(minIdx != i) {
                int temp = nums[i];
                nums[i] = nums[minIdx];
                nums[minIdx] = temp;
            }
        }
    }

    public static void selectionSort() {
        selectionSort(NUMS);
    }

    /*
     * Insertion sort
     * Time complexity:
     * Worst/Average: O(n^2)
     * Best: O(n)
     * Space complexity:
     * O(1)
     * Stable: Yes
     */
    public static void insertionSort(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n; ++i) {
            int j = i;

            while (j > 0 && nums[j - 1] > nums[j]) {
                int temp = nums[j];
                nums[j] = nums[j - 1];
                nums[j - 1] = temp;

                j--;
            }
        }
    }

    public static void insertionSort() {
        insertionSort(NUMS);
    }

    private static void mergeHelper(int[] nums, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];
        int left = low, right = mid + 1, idx = 0;

        while (left <= mid && right <= high) {
            if (nums[left] <= nums[right]) {
                temp[idx++] = nums[left];
                left++;
            } else {
                temp[idx++] = nums[right];
                right++;
            }
        }

        while (left <= mid) {
            temp[idx++] = nums[left++];
        }

        while (right <= high) {
            temp[idx++] = nums[right++];
        }

        for (int i = low; i <= high; ++i) {
            nums[i] = temp[i - low];
        }
    }

    private static void divideHelper(int[] nums, int low, int high) {
        if (low == high) {
            return;
        }

        int mid = (low + high) / 2;
        divideHelper(nums, low, mid);
        divideHelper(nums, mid + 1, high);

        mergeHelper(nums, low, mid, high);
    }

    /*
     * Merge sort:
     * Time Complexity:
     * Best/Average/Worst: O(nlog(n))
     * Space Complexity:
     * O(n) + O(n) [recursive stack space]
     * Stable: Yes
     *
     * Recurrence Relation:
     * T(n) = Θ(1) ; n = 1
     * T(n) = 2 * T(n / 2) + Θ(n) ; n > 1
     *
     * From repeated substitution:
     * n -> n / 2 then n / 2 -> n / 4 ...
     *
     * For some i:
     * T(n) = (2^i) * T(n / (2^i)) + c * i * n
     *
     * This series will terminate i.e., n will become one
     * => n / (2^i) = 1
     * => n = 2^i
     * => log(n) = i [base 2]
     *
     * Substitute known in the generalized relation
     *
     * T(n) = n * 1 + c * n * log(n)
     * => T(n) = c * n * log(n) + n
     *
     * Resulting Time Complexity:
     * O(nlog(n))
     */
    public static void mergeSort(int[] nums) {
        divideHelper(nums, 0, nums.length - 1);
    }

    public static void mergeSort() {
        mergeSort(NUMS);
    }

    private static int pivotHelper(int[] nums, int low, int high) {
        int i = low, j = high;
        int pivotVal = nums[low];

        while (i < j) {
            while (i <= high && nums[i] <= pivotVal) {
                i++;
            }

            while (j >= low && nums[j] > pivotVal) {
                j--;
            }

            if (i < j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }

        int temp = nums[j];
        nums[j] = pivotVal;
        nums[low] = temp;

        return j;
    }

    private static void quickHelper(int[] nums, int low, int high) {
        if (low >= high) {
            return;
        }

        int pivotIdx = pivotHelper(nums, low, high);

        quickHelper(nums, low, pivotIdx - 1);
        quickHelper(nums, pivotIdx + 1, high);
    }

    /*
     * Quick sort:
     * Time Complexity:
     * Best Time Complexity: Ω(n * log(n))
     * Average Time Complexity: Θ(n * log(n))
     * Worst Time Complexity: O(n^2)
     * Space Complexity:
     * Best Space Complexity: O(log(n)) [Recursive stack space]
     * Worst Space Complexity: O(n) [Recursive stack space] [Skewed Recursion Tree]
     * Stable: No
     *
     * Best Time Complexity:
     * Pivot will always be dividing the problem into two parts
     *
     * Recurrence Relationship will be:
     * T(n) = Θ(1) ; n = 1
     * T(n) = 2 * T(n / 2) + Θ(n) ; n > 1
     *
     * => T(n) = (2^i) * T(n / (2^i)) + c * i * n
     *
     * Worst Time Complexity:
     * When Array is Sorted resulting in single division
     *
     * Recurrence Relationship will be:
     * T(n) = T(n - 1) + Θ(n)
     *
     * => T(n) = T(1) + c * (2 + 3 + ... + n)
     * => T(n) = Θ(n^2)
     *
     * To make the worst case also of the order n * log(n)
     * We use Randomize quick sort where a random number from 1 to n is picked
     * Then the first element and the random element is swapped
     * now the pivot will be selected as the first element of the array
     * Then we apply the same division technique of vanilla quick sort
     *
     * Resulting Time Complexity:
     * O(n * log(n))
     */
    public static void quickSort(int[] nums) {
        quickHelper(nums, 0, nums.length - 1);
    }

    public static void quickSort() {
        quickSort(NUMS);
    }

    private static void heapify(int[] nums, int n, int i) {
        int largest = i;

        int leftChild = 2 * i + 1;
        int rightChild = 2 * i + 2;

        if(leftChild < n && nums[leftChild] > nums[largest]) {
            largest = leftChild;
        }

        if(rightChild < n && nums[rightChild] > nums[largest]) {
            largest = rightChild;
        }

        if(largest != i) {
            int temp = nums[largest];
            nums[largest] = nums[i];
            nums[i] = temp;

            heapify(nums, n, largest);
        }
    }

    private static void buildHeap(int[] nums) {
        int n = nums.length;

        for(int i = n / 2 - 1; i >= 0; --i) {
            heapify(nums, n, i);
        }
    }

    /*
     * Heap sort:
     * Time Complexity:
     * O(n * log(n))
     * Space Complexity:
     * O(1) [Iterative implementation of heapify]
     * O(log(n)) [Recursive implementation of heapify]
     * Stable: No
     *
     * Considered less efficient in comparison to Merge sort and Quick sort
     * As Heap sort involves number of swaps and comparisons
     * */
    public static void heapSort(int[] nums) {
        int n = nums.length;

        buildHeap(nums);

        for(int i = n - 1; i >= 0; --i) {
            int temp = nums[0];
            nums[0] = nums[i];
            nums[i] = temp;

            heapify(nums, i, 0);
        }
    }

    public static void heapSort() {
        heapSort(NUMS);
    }
}
