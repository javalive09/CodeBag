package com.javalive09.demos.algorithm;

import android.util.Log;

import com.javalive09.annotation.Run;
import com.javalive09.codebag.CodeActivity;

public class Quick {


    @Run
    public void show(CodeActivity codeActivity) {
        int[] arr = {2,3,7,8,4,5,6,1};
        quickSort(arr,0, arr.length-1);
        for(int i: arr) {
            Log.i("peter", "i:" + i);
        }
    }

    private void quickSort(int[] arr, int left, int right) {
        if(left < right) {
            int pivot = partition(arr, left, right);
            quickSort(arr,left, pivot-1);
            quickSort(arr,pivot+1, right);
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
            arr[right]=arr[left];
        }
        arr[left] = pivot;
        return left;
    }

}
