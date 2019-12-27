package com.javalive09.demos.algorithm;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.TextView;

import com.javalive09.annotation.Run;
import com.javalive09.codebag.CodeActivity;

public class test {


    private void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int pivot = partition(arr, left, right);
            quickSort(arr, left, pivot - 1);
            quickSort(arr, pivot + 1, right);
        }
    }

    private int partition(int[] arr, int left, int right) {
        int pivot = arr[left];
        while (left < right) {
            while (left < right && arr[right] >= pivot) {
                --right;
            }
            arr[left] = arr[right];

            while (left < right && arr[left] >= pivot) {
                ++left;
            }
            arr[right] = arr[left];
        }
        arr[left] = pivot;
        return left;
    }


    private void bubble(int[] a) {
        for (int i = 0, len = a.length; i < len - 1; i++) {
            for (int k = i + 1; k < a.length; k++) {
                if (a[i] > a[k]) {
                    int temp = a[i];
                    a[i] = a[k];
                    a[k] = temp;
                }
            }
        }
    }

    @Run
    public void showNoFitDensity(CodeActivity codeActivity) {
        TextView textView = new TextView(codeActivity);
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(50.f);

        DisplayMetrics appDisplayMetrics = codeActivity.getResources().getDisplayMetrics();

        textView.setText(appDisplayMetrics.toString() + "\n" + appDisplayMetrics.hashCode());

        codeActivity.setContentView(textView);
    }

    @Run
    public void showfitensity(CodeActivity codeActivity) {
        fitDensity(codeActivity);
        TextView textView = new TextView(codeActivity);
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(50.f);

        DisplayMetrics appDisplayMetrics = codeActivity.getResources().getDisplayMetrics();

        textView.setText(appDisplayMetrics.toString() + "\n" + appDisplayMetrics.hashCode());
        codeActivity.setContentView(textView);
    }

    @Run
    public void showResource(CodeActivity codeActivity) {
        codeActivity.showText(codeActivity.getResources().toString()
        + "\n" +
        codeActivity.getApplication().getResources().toString()
        + "\n" +
        codeActivity.getApplicationContext().getResources().toString()
        );
    }

    @Run
    public void showDisplayMetrics(CodeActivity codeActivity) {
        codeActivity.showText(codeActivity.getResources().getDisplayMetrics().hashCode()
        + "\n" +
        codeActivity.getApplication().getResources().getDisplayMetrics().hashCode()
        + "\n" +
        codeActivity.getApplicationContext().getResources().getDisplayMetrics().hashCode()
        );
    }

    private void fitDensity(CodeActivity activity) {
        DisplayMetrics appDisplayMetrics = activity.getResources().getDisplayMetrics();
        float targetDensity = appDisplayMetrics.widthPixels * 1.0f / 100;
        int targetDensityDpi = (int) (targetDensity * 160);
        float targetScaledDensity = targetDensity;
        appDisplayMetrics.density = targetDensity;
        appDisplayMetrics.densityDpi = targetDensityDpi;
        appDisplayMetrics.scaledDensity = targetScaledDensity;
    }

    @Run
    public void getMetrics(CodeActivity codeActivity) {
        WindowManager wm = (WindowManager) codeActivity.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

//        display.getMetrics();
        Log.i("getMetrics", display + "  -   "+ display.hashCode());

        WindowManager wm1 = codeActivity.getWindowManager();
        Log.i("getMetrics", wm1.getDefaultDisplay() + "  -   "+ wm1.getDefaultDisplay().hashCode());
//        wm1.getDefaultDisplay().getMetrics();

        Resources resources = codeActivity.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Log.i("getMetrics", dm + "  -   "+ dm.hashCode());
    }


}
