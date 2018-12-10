package com.liupeng.learning.sort;

public class BinarySearchTest {
    public static void main(String[] args) {
        int[] arr = Constants.ORDER_ARR;
        System.out.println(binarySearch(arr,11));
    }

    public static int binarySearch(int[] arr, int target){
        int pos = -1;

        int low = 0, high = arr.length-1, mid;
        while (low <= high) {
            mid = (low + high) / 2;
            if (arr[mid] == target) {
                pos = mid;
                return pos;
            } else if (target < arr[mid]) {
                high = mid - 1;
            } else if (target > arr[mid]) {
                low = mid + 1;
            }
        }

        return pos;
    }
}
