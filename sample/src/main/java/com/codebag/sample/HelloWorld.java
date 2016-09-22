package com.codebag.sample;

import android.content.Intent;
import android.graphics.Color;
import android.widget.Button;

import com.codebag.bag.Entry;

/**
 * Created by peter on 16/9/21.
 */

public class HelloWorld extends Entry {

    public void showText() {
        showTxt("hello world !");
    }

    public void showView() {
        Button button = new Button(getActivity());
        button.setText("hello world !");
        button.setTextColor(Color.BLUE);
        showView(button);
    }

    public void showViewById() {
        showView(R.layout.helloworld_method_layout);
    }

    public void startActivity() {
        startActivity(new Intent(getActivity(), HelloWorldActivity.class));
    }

}
