package com.codebag.code.mycode.test.fragmentlife;

import com.codebag.R;
import com.codebag.code.mycode.utils.Log;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ViewPagerFragmentActivity extends FragmentActivity {

	int rootId = 123456;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.addLog(this, "ViewPagerFragmentActivity====" + "onCreate");
		
		setContentView(R.layout.viewpager_fragment);
		
		ViewPager vp = (ViewPager) findViewById(R.id.page_container);
		
		Fragment[] fragments = new Fragment[]{
				new MyFragment() {
					@Override
					public View onCreateView(LayoutInflater inflater, ViewGroup container,
							Bundle savedInstanceState) {
						Log.addLog(this, "MyFragment====" + "onCreateView");
						TextView v = new TextView(ViewPagerFragmentActivity.this);
						v.setText("fragment1");
						v.setTextSize(50);
						return v;
						
					}
				},
				
				new MyFragment() {
					@Override
					public View onCreateView(LayoutInflater inflater, ViewGroup container,
							Bundle savedInstanceState) {
						Log.addLog(this, "MyFragment====" + "onCreateView");
						TextView v = new TextView(ViewPagerFragmentActivity.this);
						v.setText("fragment2");
						v.setTextSize(50);
						return v;
						
					}
				}
		};
		
		PageAdapter adapter = new PageAdapter(getSupportFragmentManager(), fragments);
		
		vp.setAdapter(adapter);
		
	}

	
    public class PageAdapter extends FragmentPagerAdapter {

    	Fragment[] mFragments;

		public PageAdapter(FragmentManager fm, Fragment[] fragments) {
			super(fm);
			mFragments = fragments;
		}

		@Override
		public Fragment getItem(int arg0) {
			return mFragments[arg0];
		}

		@Override
		public int getCount() {
			return mFragments.length;
		}

    }
	
	@Override
	protected void onStart() {
		super.onStart();
		Log.addLog(this, "ViewPagerFragmentActivity====" + "onStart");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.addLog(this, "ViewPagerFragmentActivity====" + "onResume");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.addLog(this, "ViewPagerFragmentActivity====" + "onPause");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.addLog(this, "ViewPagerFragmentActivity====" + "onStop");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.addLog(this, "ViewPagerFragmentActivity====" + "onDestroy");
	}
	
	public static class MyFragment extends Fragment{

		@Override
		public void onActivityResult(int requestCode, int resultCode,
				Intent data) {
			// TODO Auto-generated method stub
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
