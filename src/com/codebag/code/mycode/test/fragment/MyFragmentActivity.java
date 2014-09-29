package com.codebag.code.mycode.test.fragment;

import com.codebag.R;
import com.codebag.bag.CodeBag;
import com.codebag.code.mycode.utils.Log;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyFragmentActivity extends FragmentActivity {

	int rootId = 123456;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.addLog(this, "FragmentActivity====" + "onCreate");
		
		setContentView(((CodeBag) getApplication()).getRootViewRes());
		
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.add(R.id.container, new MyFragment() {
			public View onCreateView(LayoutInflater inflater, ViewGroup container,
					Bundle savedInstanceState) {
				super.onCreateView(inflater, container, savedInstanceState);
				TextView tv = new TextView(MyFragmentActivity.this);
				tv.setText("FragmentActivity-MyFragment");
				return tv;
				
			}
		}, "new");
		fragmentTransaction.commit();
		
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.addLog(this, "FragmentActivity====" + "onStart");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.addLog(this, "FragmentActivity====" + "onResume");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.addLog(this, "FragmentActivity====" + "onPause");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.addLog(this, "FragmentActivity====" + "onStop");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.addLog(this, "FragmentActivity====" + "onDestroy");
	}
	
	public static class MyFragment extends Fragment{

		@Override
		public void onActivityResult(int requestCode, int resultCode,
				Intent data) {
			super.onActivityResult(requestCode, resultCode, data);
			Log.addLog(this, "MyFragment====" + "onActivityResult");
		}

		@Override
		public void onAttach(Activity activity) {
			// TODO Auto-generated method stub
			super.onAttach(activity);
			Log.addLog(this, "MyFragment====" + "onAttach");
		}

		@Override
		public void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			Log.addLog(this, "MyFragment====" + "onCreate");
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			Log.addLog(this, "MyFragment====" + "onCreateView");
			return super.onCreateView(inflater, container, savedInstanceState);
			
		}

		@Override
		public void onViewCreated(View view, Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onViewCreated(view, savedInstanceState);
			Log.addLog(this, "MyFragment====" + "onViewCreated");
		}

		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onActivityCreated(savedInstanceState);
			Log.addLog(this, "MyFragment====" + "onActivityCreated");
		}

		@Override
		public void onStart() {
			// TODO Auto-generated method stub
			Log.addLog(this, "MyFragment====" + "onStart");
			super.onStart();
		}

		@Override
		public void onResume() {
			// TODO Auto-generated method stub
			Log.addLog(this, "MyFragment====" + "onResume");
			super.onResume();
		}

		@Override
		public void onPause() {
			// TODO Auto-generated method stub
			Log.addLog(this, "MyFragment====" + "onPause");
			super.onPause();
		}

		@Override
		public void onStop() {
			// TODO Auto-generated method stub
			Log.addLog(this, "MyFragment====" + "onStop");
			super.onStop();
		}

		@Override
		public void onDestroyView() {
			// TODO Auto-generated method stub
			Log.addLog(this, "MyFragment====" + "onDestroyView");
			super.onDestroyView();
		}

		@Override
		public void onDestroy() {
			// TODO Auto-generated method stub
			Log.addLog(this , "MyFragment====" + "onDestroy");
			super.onDestroy();
		}

		@Override
		public void onDetach() {
			// TODO Auto-generated method stub
			Log.addLog(this, "MyFragment====" + "onDetach");
			super.onDetach();
		}
		
	}
	
}
