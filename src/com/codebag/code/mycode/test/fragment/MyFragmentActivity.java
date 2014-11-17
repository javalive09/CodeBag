package com.codebag.code.mycode.test.fragment;

import com.codebag.R;
import com.codebag.bag.CodeBag;
import com.codebag.code.mycode.utils.Log;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MyFragmentActivity extends FragmentActivity {

	int rootId = 123456;
	int id = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.addLog("peter", this, "FragmentActivity====" + "onCreate");
		
		setContentView(((CodeBag) getApplication()).getRootViewRes());
		
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.add(R.id.container, new MyFragment() {
			public View onCreateView(LayoutInflater inflater, ViewGroup container,
					Bundle savedInstanceState) {
				super.onCreateView(inflater, container, savedInstanceState);
				Button bt = new Button(MyFragmentActivity.this);
				bt.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						FragmentManager fragmentManager = getSupportFragmentManager();
						FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
						Fragment ft = new MyFragment() {
							public View onCreateView(LayoutInflater inflater, ViewGroup container,
									Bundle savedInstanceState) {
								FrameLayout fl = new FrameLayout(MyFragmentActivity.this);
								fl.setBackgroundColor(Color.BLACK);
								TextView tv = new TextView(MyFragmentActivity.this);
								tv.setText("FragmentActivity-MyFragment");
								fl.addView(tv);
								return fl;
							}
						};
						fragmentTransaction.replace(R.id.container, ft);
						fragmentTransaction.addToBackStack(null);
						fragmentTransaction.commit();
					}
				});
				bt.setText("new fragment" + id++);
				return bt;
				
			}
		}, "new");
		fragmentTransaction.commit();
		
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		Log.addLog("peter", this, "FragmentActivity====" + "onStart");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.addLog("peter", this, "FragmentActivity====" + "onResume");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.addLog("peter", this, "FragmentActivity====" + "onPause");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.addLog("peter", this, "FragmentActivity====" + "onStop");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.addLog("peter", this, "FragmentActivity====" + "onDestroy");
	}
	
	public static class MyFragment extends Fragment{

		@Override
		public void onActivityResult(int requestCode, int resultCode,
				Intent data) {
			super.onActivityResult(requestCode, resultCode, data);
			Log.addLog("peter", this, "MyFragment====" + "onActivityResult");
		}

		@Override
		public void onAttach(Activity activity) {
			// TODO Auto-generated method stub
			super.onAttach(activity);
			Log.addLog("peter", this, "MyFragment====" + "onAttach");
		}

		@Override
		public void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			Log.addLog("peter", this, "MyFragment====" + "onCreate");
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			Log.addLog("peter", this, "MyFragment====" + "onCreateView");
			return super.onCreateView(inflater, container, savedInstanceState);
			
		}

		@Override
		public void onViewCreated(View view, Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onViewCreated(view, savedInstanceState);
			Log.addLog("peter", this, "MyFragment====" + "onViewCreated");
		}

		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onActivityCreated(savedInstanceState);
			Log.addLog("peter", this, "MyFragment====" + "onActivityCreated");
		}

		@Override
		public void onStart() {
			// TODO Auto-generated method stub
			Log.addLog("peter", this, "MyFragment====" + "onStart");
			super.onStart();
		}

		@Override
		public void onResume() {
			// TODO Auto-generated method stub
			Log.addLog("peter", this, "MyFragment====" + "onResume");
			super.onResume();
		}

		@Override
		public void onPause() {
			// TODO Auto-generated method stub
			Log.addLog("peter", this, "MyFragment====" + "onPause");
			super.onPause();
		}

		@Override
		public void onStop() {
			// TODO Auto-generated method stub
			Log.addLog("peter", this, "MyFragment====" + "onStop");
			super.onStop();
		}

		@Override
		public void onDestroyView() {
			// TODO Auto-generated method stub
			Log.addLog("peter", this, "MyFragment====" + "onDestroyView");
			super.onDestroyView();
		}

		@Override
		public void onDestroy() {
			// TODO Auto-generated method stub
			Log.addLog("peter", this , "MyFragment====" + "onDestroy");
			super.onDestroy();
		}

		@Override
		public void onDetach() {
			// TODO Auto-generated method stub
			Log.addLog("peter", this, "MyFragment====" + "onDetach");
			super.onDetach();
		}
		
	}
	
}
