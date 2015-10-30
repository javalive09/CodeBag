package com.codebag.bag.main;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import com.codebag.R;
import com.codebag.bag.Node;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

public class InovkedViewActivity extends BaseActivity {

	FrameLayout root;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_invoke_view);
			root = (FrameLayout) findViewById(R.id.invoke_view);
			root.post(new Runnable() {
				
				@Override
				public void run() {
					InvokeMethod(mNode);
				}
			});
	}
	
	private void InvokeMethod(Node node) {
		String methodName = node.name;
		String className = node.className;
		try {
			Class<?> cls = Class.forName(className);
    		Constructor<?> con = cls.getConstructor(InovkedViewActivity.class);
		    Object obj = con.newInstance(InovkedViewActivity.this);
			Method method = cls.getDeclaredMethod(methodName);
			method.invoke(obj);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	public void showMethodView(View view, String methodName, FrameLayout.LayoutParams params) {
		if(params == null) {
			root.addView(view);
		}else {
			root.addView(view, params);
		}
	}
	
	public void showMethodView(int resId, String methodName) {
		LayoutInflater factory = LayoutInflater.from(InovkedViewActivity.this);
		factory.inflate(resId, root, true);
	}

}
