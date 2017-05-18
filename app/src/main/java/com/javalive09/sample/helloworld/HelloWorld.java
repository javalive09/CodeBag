package com.javalive09.sample.helloworld;

import android.content.Intent;
import android.graphics.Color;
import android.widget.Button;

import com.javalive09.codebag.Entry;
import com.javalive09.codebag.LogUtil;
import com.javalive09.codebag.logger.Log;
import com.javalive09.sample.R;

/**
 * a sample for entry
 *
 * Created by peter on 16/9/21.
 */

public class HelloWorld extends Entry {

    public HelloWorld() {

    }

    //show text
    public void showText() {
        showTxt("hello world !");
    }

    //show view
    public void showView() {
        Button button = new Button(getActivity());
        button.setText("hello world !");
        button.setTextColor(Color.BLUE);
        showView(button);
    }

    //show view by id
    public void showViewById() {
        showView(R.layout.helloworld_method_layout);
    }

    //start activity
    public void startActivity() {
        Intent intent = new Intent(getActivity(), HelloWorldActivity.class);
        getActivity().startActivity(intent);
    }

    //show log
    public void printLog() {
        Log.i("hello world", "print log!");
    }

}
