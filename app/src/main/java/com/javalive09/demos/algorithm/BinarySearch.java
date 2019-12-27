package com.javalive09.demos.algorithm;

public class BinarySearch {

    public int binarySearch(int[] arr, int key, int low, int high) {
        if (key < arr[low] && key > arr[high] && low > high) {
            return -1;
        }
        int middle = (low + high) / 2;
        if (arr[middle] < key) {
            return binarySearch(arr, key, low, middle - 1);
        } else if (arr[middle] > key) {
            return binarySearch(arr, key, middle + 1, high);
        } else {
            return middle;
        }
    }

}
