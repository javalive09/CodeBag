package com.javalive09.demos.algorithm;

public class test1209 {

    public void bubble(int[] arr) {
        int len = arr.length;
        for (int n = 1; n < len; n++) {
            for (int i = 0; i < len - n; i++) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                }
            }
        }
    }


    public void quick(int[]arr, int left, int right) {
        if(left < right) {
            int pivot = partition(arr, left, right);
            quick(arr, left, pivot-1);
            quick(arr, pivot + 1, right);
        }
    }

    public int partition(int[]arr, int left, int right) {
        int pivot = arr[left];
        while (left < right) {
            while(left < right && arr[right] >= pivot) {
                --right;
            }
            arr[left] = arr[right];
            while(left < right && arr[left] <= pivot) {
                ++left;
            }
            arr[right]=arr[left];
        }
        arr[left] = pivot;
        return left;
    }

    public int binarySearch(int[] arr, int key, int left, int right) {
        if(left > right || key < arr[left] || key > arr[right]) {
            return -1;
        }
        int middle = (left + right) / 2;

        if(key > middle) {
            return binarySearch(arr, key, middle + 1, right);
        }else if(key < middle) {
            return binarySearch(arr, key, left, middle - 1);
        }else {
            return middle;
        }

    }

}
