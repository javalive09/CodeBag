package com.javalive09.demos.algorithm;

import android.util.Log;

import com.javalive09.annotation.Run;
import com.javalive09.codebag.CodeActivity;

public class Bubbing {

    private static int countIn = 0;
    private static int countOut = 0;

    private void bubbleSort(int[] a) {
        for(int i = 0, len = a.length; i< len - 1; i++) {
            for(int k = i + 1; k < len; k++) {
                if(a[i] > a[k]) {
                    int temp = a[i];
                    a[i] = a[k];
                    a[k] = temp;
                    Log.i("peter", "countIn=" + ++countIn);
                }
                Log.i("peter", "countOut=" + ++countOut + ";i="+i + ";k="+k);
            }
        }
    }

    @Run
    public void showBubbleSort(CodeActivity codeActivity) {
        int[] a = {1, 2, 3, 5, 6, 9, 8, 7, 4};
        bubbleSort(a);
        for(int i : a) {
            Log.i("peter", "i=" + i);
        }
    }

    private void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivot = partition(arr, low, high);
            quickSort(arr, low, pivot - 1);
            quickSort(arr, pivot + 1, high);
        }
    }

    private int partition(int[] arr, int low, int high) {
        int pivot = arr[low];
        while (low < high) {
            while (low < high && arr[high] >= pivot) {
                --high;
            }
            arr[low] = arr[high];

            while (low < high && arr[low] <= pivot) {
                ++low;
            }
            arr[high] = arr[low];
        }
        arr[low] = pivot;
        return low;
    }

}
