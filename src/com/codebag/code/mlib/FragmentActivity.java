package com.codebag.code.mlib;

import com.codebag.R;
import com.codebag.bag.Log;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FragmentActivity extends Activity {

	int rootId = 123456;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.addLog("FragmentActivity====" + "onCreate");
		
		setContentView(R.layout.activity_fragment);
		
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.add(R.id.myfragment, new MyFragment() {
			public View onCreateView(LayoutInflater inflater, ViewGroup container,
					Bundle savedInstanceState) {
				super.onCreateView(inflater, container, savedInstanceState);
				TextView tv = new TextView(FragmentActivity.this);
				tv.setText("FragmentActivity-MyFragment");
				return tv;
				
			}
		}, "new");
		fragmentTransaction.commit();
		
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.addLog("FragmentActivity====" + "onStart");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.addLog("FragmentActivity====" + "onResume");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.addLog("FragmentActivity====" + "onPause");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.addLog("FragmentActivity====" + "onStop");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.addLog("FragmentActivity====" + "onDestroy");
	}
	
	public static class MyFragment extends Fragment{

		@Override
		public void onActivityResult(int requestCode, int resultCode,
				Intent data) {
			// TODO Auto-generated method stub
			super.onActivityResult(requestCode, resultCode, data);
			Log.addLog("MyFragment====" + "onActivityResult");
		}

		@Override
		public void onAttach(Activity activity) {
			// TODO Auto-generated method stub
			super.onAttach(activity);
			Log.addLog("MyFragment====" + "onAttach");
		}

		@Override
		public void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			Log.addLog("MyFragment====" + "onCreate");
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			Log.addLog("MyFragment====" + "onCreateView");
			return super.onCreateView(inflater, container, savedInstanceState);
			
		}

		@Override
		public void onViewCreated(View view, Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onViewCreated(view, savedInstanceState);
			Log.addLog("MyFragment====" + "onViewCreated");
		}

		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onActivityCreated(savedInstanceState);
			Log.addLog("MyFragment====" + "onActivityCreated");
		}

		@Override
		public void onStart() {
			// TODO Auto-generated method stub
			Log.addLog("MyFragment====" + "onStart");
			super.onStart();
		}

		@Override
		public void onResume() {
			// TODO Auto-generated method stub
			Log.addLog("MyFragment====" + "onResume");
			super.onResume();
		}

		@Override
		public void onPause() {
			// TODO Auto-generated method stub
			Log.addLog("MyFragment====" + "onPause");
			super.onPause();
		}

		@Override
		public void onStop() {
			// TODO Auto-generated method stub
			Log.addLog("MyFragment====" + "onStop");
			super.onStop();
		}

		@Override
		public void onDestroyView() {
			// TODO Auto-generated method stub
			Log.addLog("MyFragment====" + "onDestroyView");
			super.onDestroyView();
		}

		@Override
		public void onDestroy() {
			// TODO Auto-generated method stub
			Log.addLog("MyFragment====" + "onDestroy");
			super.onDestroy();
		}

		@Override
		public void onDetach() {
			// TODO Auto-generated method stub
			Log.addLog("MyFragment====" + "onDetach");
			super.onDetach();
		}
		
	}
	
}
