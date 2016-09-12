package com.codebag.code;

import android.graphics.Color;
import android.widget.TextView;
import com.codebag.R;
import com.codebag.bag.Entry;

/**
 * Created by peter on 16/4/26.
 */
public class HelloWorld extends Entry {

    public void showText() {
        showTxt("hello world !");
    }

    public void showView() {
        TextView button = new TextView(getActivity());
        button.setTextColor(Color.BLACK);
        button.setText("hello world !");
        showView(button);
    }

    public void showViewById() {
        showView(R.layout.helloworld);
    }

}