package com.codebag.bag.main;

import java.lang.reflect.Field;
import java.util.LinkedList;

import com.codebag.R;
import com.codebag.bag.Node;
import com.codebag.code.mycode.utils.Log;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewConfiguration;

public class BaseActivity extends AppCompatActivity {

	private static LinkedList<BaseActivity> mActContainer = new LinkedList<BaseActivity>();
	Node mNode;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initActionBar();
		mActContainer.add(BaseActivity.this);
	}

	private void initActionBar() {
		setOverflowShowingAlways();
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		Intent intent = getIntent();
		if (intent != null) {
			mNode = (Node) intent.getSerializableExtra("node");
			String name = mNode.name;
			switch(mNode.type) {
			case Node.APP:
			case Node.DIR:
				break;
			case Node.CLASS:
				name += ".java";
				break;
			case Node.METHOD:
				name += "( )";
				break;
			}
			getSupportActionBar().setTitle(name);
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		mActContainer.remove(BaseActivity.this);
	}

	private void exit() {
		for(BaseActivity act : mActContainer) {
			act.finish();
		}
	}
	
	private void setOverflowShowingAlways() {
		try {
			ViewConfiguration config = ViewConfiguration.get(this);
			Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
			menuKeyField.setAccessible(true);
			menuKeyField.setBoolean(config, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case android.R.id.home:
			finish();
			break;
		case R.id.action_help:
			showAlertDialog(getString(R.string.action_help), getString(R.string.action_help_msg));
			break;
		case R.id.action_about:
			showAlertDialog(getString(R.string.action_about), getString(R.string.action_about_msg));
			break;
			
		case R.id.action_showlog:
			showAlertDialog("log", Log.getLog());
			break;
			
		case R.id.action_clearlog:
			Log.clearLog();
			break;
			
		case R.id.action_exit:
			exit();
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void showAlertDialog(String title, String content) {
		AlertDialog dialog = new AlertDialog.Builder(BaseActivity.this, AlertDialog.THEME_HOLO_DARK).create();
		dialog.setCanceledOnTouchOutside(true);
		dialog.setTitle(title);
		dialog.setMessage(content);
		dialog.show();
	}
}
