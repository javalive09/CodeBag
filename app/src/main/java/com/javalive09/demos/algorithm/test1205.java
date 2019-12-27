package com.javalive09.demos.algorithm;

public class test1205 {

    public void bubble(int[] arr) {
        for (int i = 0, len = arr.length; i < len - 1; i++) {
            for (int k = i + 1; k < len; k++) {
                if (arr[i] > arr[k]) {
                    int temp = arr[i];
                    arr[i] = arr[k];
                    arr[k] = temp;
                }
            }
        }
    }

    public void quick(int[] arr, int left, int right) {
        if (left < right) {
            int pivot = partition(arr, left, right);
            quick(arr, left, pivot - 1);
            quick(arr, pivot + 1, right);
        }
    }

    private int partition(int[] arr, int left, int right) {
        int pivot = arr[left];
        while (left < right) {
            while (left < right && arr[right] >= pivot) {
                --right;
            }
            arr[left] = arr[right];
            while (left < right && arr[left] <= pivot) {
                ++left;
            }
            arr[right] = arr[left];
        }
        arr[left] = pivot;
        return left;
    }

    public int binarySearch(int[] arr, int key, int low, int high) {
        if (key < arr[low] && key > arr[high] && low > high) {
            return -1;
        }
        int middle = (high - low) / 2;
        if (key > middle) {
            return binarySearch(arr, key, middle + 1, high);
        } else if (key < middle) {
            return binarySearch(arr, key, low, middle - 1);
        } else {
            return middle;
        }
    }

}
