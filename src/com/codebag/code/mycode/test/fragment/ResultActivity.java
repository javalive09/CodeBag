package com.codebag.code.mycode.test.fragment;

import com.codebag.R;
import com.codebag.bag.CodeBag;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.TextView;

public class ResultActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		TextView tv = new TextView(ResultActivity.this);
		tv.setText("ResultActivity");
		setContentView(tv);
		tv.setBackgroundColor(Color.BLUE);
		setResult(8);
		tv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showDialog();
			}
		});
		
		
		setContentView(R.layout.activity_root);
		FrameLayout fl = (FrameLayout) findViewById(R.id.container);
		
		fl.addView(tv);
	}
	
	private void showDialog() {
		new AlertDialog.Builder(ResultActivity.this)
        .setTitle("title")
        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                /* User clicked OK so do some stuff */
            }
        })
        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                /* User clicked Cancel so do some stuff */
            }
        })
        .create().show();
	}
}
