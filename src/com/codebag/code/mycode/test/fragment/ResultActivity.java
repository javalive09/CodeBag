package com.codebag.code.mycode.test.fragment;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView tv = new TextView(ResultActivity.this);
		tv.setText("ResultActivity");
		setContentView(tv);
		tv.setBackgroundColor(Color.BLACK);
		setResult(8);
	}
}
