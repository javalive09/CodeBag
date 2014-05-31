package com.codebag.bag;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import com.codebag.R;
import com.codebag.bag.CodeBag.Node;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Looper;
import android.os.MessageQueue.IdleHandler;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends Activity {

	AlertDialog mDialog = null;
	 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getOverflowMenu();
        CodeBag codeBag = (CodeBag) getApplication();
        Node currentNode = codeBag.getCurrentNode();
        if(currentNode == null) {//root 
        	showSplash();  
        }else {
        	showMainView(currentNode);
        }
        codeBag.addActivity(this);
    }

	private void showSplash() {
		setContentView(new SplashView1(MainActivity.this));
		
		IdleHandler handler = new IdleHandler() {

			@Override
			public boolean queueIdle() {
				CodeBag app = (CodeBag) getApplication();
				app.init();
				CodeBag codeBag = (CodeBag) getApplication();
				showMainView(codeBag.getRootNode());
				return false;
			}
			
		};
		Looper.myQueue().addIdleHandler(handler);
	}

	private void showMainView(Node node) {
		getActionBar().setTitle(node.mName);
		
		switch(node.mType) {
			case Node.DIR:
				getActionBar().setIcon(R.drawable.folder);
				showDirView(node);
				break;
			case Node.CLASS:
				getActionBar().setIcon(R.drawable.file);
				showClassView(node);
				break;
			case Node.METHOD:
				getActionBar().setIcon(R.drawable.method);
				CodeBag codeBage = (CodeBag) getApplication();
				setContentView(codeBage.getCurrentMethodView());
				break;
		}
//		Debug.stopMethodTracing();
	}

	private void showClassView(Node node) {
		//class file
			String className = node.mFullName;
			CaseListView caseListview = null;
			ArrayList<Method> list = new ArrayList<Method>();
			try {
				@SuppressWarnings("rawtypes")
				Class cls = Class.forName(className);
				@SuppressWarnings({ "rawtypes", "unchecked" })
				Constructor con = cls.getConstructor(Context.class);
				caseListview = (CaseListView) con.newInstance(MainActivity.this);
				setContentView(caseListview);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			
			Method[] methods = caseListview.getClass().getDeclaredMethods();
			for(Method m : methods) {
				if(m.getName().indexOf(CaseListView.METHOD_PREFIX) >= 0) {
					list.add(m);
				}
			}
			
			caseListview.setAdapter(new ListAdapter<Method>(MainActivity.this, list) {
				@Override
				public View getView(int position, View convertView, ViewGroup parent) {
					if(convertView == null) {
						LinearLayout l = new LinearLayout(mContext);
						l.setOrientation(LinearLayout.HORIZONTAL);
						l.setMinimumHeight(80); 
						l.setGravity(Gravity.CENTER_VERTICAL);
						
						ImageView icon = new ImageView(mContext);
						icon.setImageResource(R.drawable.method);
						
						TextView tv = new TextView(mContext);
						tv.setGravity(Gravity.CENTER_VERTICAL);
						tv.setText(getList().get(position).getName());
						
						l.addView(icon);
						l.addView(tv);
						convertView = l;
					}
					return convertView;
				}
			});
			caseListview.setOnItemClickListener(new OnItemClickListener() {

				@SuppressWarnings("unchecked")
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					ListAdapter<Method> listAdapter = (ListAdapter<Method>) parent.getAdapter();
					ArrayList<Method> list = listAdapter.getList();
					try {
						list.get(position).invoke(parent);
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			});
	}

	private void showDirView(Node node) {
		setContentView(R.layout.activity_main);
		ListView listView = (ListView) findViewById(R.id.list);

		listView.setAdapter(new ListAdapter<Node>(MainActivity.this, node.mSubNodeList) {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				if(convertView == null) {
					Node node = getList().get(position);
					
					LinearLayout l = new LinearLayout(mContext);
					l.setOrientation(LinearLayout.HORIZONTAL);
					l.setMinimumHeight(80); 
					l.setGravity(Gravity.CENTER_VERTICAL);
					
					ImageView icon = new ImageView(mContext);
					if(node.mType == Node.CLASS) {
						icon.setImageResource(R.drawable.file);
					}else if(node.mType == Node.DIR) {
						icon.setImageResource(R.drawable.folder);
					}
					
					TextView tv = new TextView(mContext);
					tv.setGravity(Gravity.CENTER_VERTICAL);
					tv.setText(node.mName);
					
					l.addView(icon);
					l.addView(tv);
					
					convertView = l;
				}
				return convertView;
			}
			
		});
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
		    	ListAdapter<Node> adapter = ((ListAdapter<Node>) parent.getAdapter());
		    	Node mNode = adapter.getList().get(position);
		    	
		    	if(mNode != null) { 
					Intent intent = new Intent(MainActivity.this, MainActivity.class);
					CodeBag codeBag = (CodeBag) getApplication();
					codeBag.setCurrentNode(mNode);
					startActivity(intent);
		    	}
			}
		});
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    private void getOverflowMenu() {
        try {
           ViewConfiguration config = ViewConfiguration.get(this);
           Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
           if(menuKeyField != null) {
               menuKeyField.setAccessible(true);
               menuKeyField.setBoolean(config, false);
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
   }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	if(mDialog == null) {
    		mDialog = new AlertDialog.Builder(MainActivity.this).create();
    	} 
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        switch(item.getItemId()) {
        case R.id.action_help:
        	mDialog.setTitle(getResources().getString(R.string.action_help));
        	mDialog.setMessage(getResources().getString(R.string.action_help_msg));
        	mDialog.show();
        	break;
        case R.id.action_about:
        	mDialog.setTitle(getResources().getString(R.string.action_about));
        	mDialog.setMessage(getResources().getString(R.string.action_about_msg));
        	mDialog.show();
        	break;
        case R.id.action_feedback:
        	break;
        case R.id.action_showlog:
        	AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        	
        	ScrollView view = new ScrollView(MainActivity.this);
    		TextView log = new TextView(MainActivity.this);
    		log.setText(Log.getLog());
    		view.addView(log);
        	builder.setView(view);
        	builder.create().show();
        	
        	break;
        case R.id.action_clearlog:
        	Log.clearLog();
        	break;
        case R.id.action_exit:
        	CodeBag codeBag = (CodeBag) getApplication();
        	codeBag.exit();
        	break;
        }
        return super.onOptionsItemSelected(item);
    }
    
	@Override
	protected void onDestroy() {
		super.onDestroy();
		CodeBag codeBag = (CodeBag) getApplication();
		codeBag.setCurrentNode(null);
		codeBag.setCurrentMethodView(null);
		codeBag.removeActivity(this);
	}
    
    public static class ListAdapter<T> extends BaseAdapter {
    	ArrayList<T> mList;
    	Context mContext;

    	public ListAdapter(Context context, ArrayList<T> list) {
    		mContext = context;
    		mList = list;
    	}
    	
    	@Override
		public int getCount() {
			return mList.size();
		}
		@Override
		public Object getItem(int position) {
			return mList.get(position);
		}
		@Override
		public long getItemId(int position) {
			return position;
		}
		public ArrayList<T> getList() {
			return mList;
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			return null;
		}
    }
	 
	 public class SplashView extends FrameLayout {

			public SplashView(Context context) {
				super(context);
				init(context);
			}

			private void init(Context context) {
				TextView tv = new TextView(context);
				tv.setText("Loading...");
				tv.setTextColor(Color.BLACK);
				tv.setTextSize(50);
				tv.setGravity(Gravity.CENTER);
				LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
				params.gravity = Gravity.CENTER;
				tv.setLayoutParams(params);
				addView(tv, params);
			}
	 }
	 
	 public class SplashView1 extends TextView {

			public SplashView1(Context context) {
				super(context);
				init(context);
			}

			private void init(Context context) {
				setText("Loading...");
				setTextColor(Color.BLACK);
				setTextSize(50);
				setGravity(Gravity.CENTER);
			}
	 }

}
