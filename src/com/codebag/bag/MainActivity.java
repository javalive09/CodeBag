package com.codebag.bag;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Looper;
import android.os.MessageQueue.IdleHandler;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

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
		
		if(!node.mName.equals(CodeBag.ROOT_DIR)) {
			getActionBar().setHomeButtonEnabled(true);
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
		
		switch(node.mType) {
			case Node.DIR:
				getActionBar().setTitle(node.mName);
				getActionBar().setIcon(R.drawable.folder);
				showDirView(node);
				break;
			case Node.CLASS:
				getActionBar().setTitle(node.mName + ".java");
				getActionBar().setIcon(R.drawable.file);
				showClassView(node);
				break;
			case Node.METHOD:
				getActionBar().setDisplayShowHomeEnabled(false);
				getActionBar().setTitle(node.mName + " ( )");
				CodeBag codeBage = (CodeBag) getApplication();
				setContentView(codeBage.getCurrentMethodView());
			case Node.APP:
				getActionBar().setTitle(node.mName);
				getActionBar().setIcon(R.drawable.folder);
				showAppDemoView(node);
				break;
		}
//		Debug.stopMethodTracing();
	}
	
	private Drawable getRightSizeIcon(BitmapDrawable drawable) {
		Drawable rightDrawable = getResources().getDrawable(R.drawable.ic_launcher);
		int rightSize = rightDrawable.getIntrinsicWidth();
		Bitmap bitmap = drawable.getBitmap();
		int width = bitmap.getWidth();
		float widths = width;
		float scale = rightSize / widths;
		Matrix matrix = new Matrix();
		matrix.setScale(scale, scale);
		Bitmap bm = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
				bitmap.getHeight(), matrix, true);
		return new BitmapDrawable(getResources(), bm);
	}
	
	
	private void showAppDemoView(Node node) {
		setContentView(R.layout.activity_main);
		ListView listView = (ListView) findViewById(R.id.list);

		listView.setAdapter(new ListAdapter<Node>(MainActivity.this, node.mSubNodeList) {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				if(convertView == null) {
					Node node = getItem(position);
					
					LinearLayout l = new LinearLayout(mContext);
					l.setOrientation(LinearLayout.HORIZONTAL);
					l.setMinimumHeight(80); 
					l.setGravity(Gravity.CENTER_VERTICAL);
					
					TextView tv = new TextView(mContext);
					tv.setGravity(Gravity.CENTER_VERTICAL);
					
					ImageView appIcon = new ImageView(mContext);
					try {
						PackageManager pm = getPackageManager();
						
						ApplicationInfo info = pm.getApplicationInfo(node.mFullName, 0);
						
						Drawable drawable = pm.getApplicationIcon(info);
						BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
						Drawable icon = getRightSizeIcon(bitmapDrawable);
						appIcon.setImageDrawable(icon);
						tv.setText(pm.getApplicationLabel(info));
						l.addView(appIcon);
						l.addView(tv);
					} catch (NameNotFoundException e) {
						e.printStackTrace();
					}    
					
					if(getItemViewType(position) == NO_ENTRY) {
						l.setBackgroundResource(android.R.color.darker_gray);
					}
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
				ListAdapter<Node> adapter = (ListAdapter<Node>) parent.getAdapter();
		    	Node mNode = (Node) adapter.getItem(position);
		    	if(mNode != null) {
		    		Intent intent = getPackageManager().getLaunchIntentForPackage(mNode.mFullName);
					startActivity(intent);
		    	}

			}
		});
		
	}

	private void showClassView(Node node) {
		//class file
			String className = node.mFullName;
			CaseListView caseListview = null;
			ArrayList<Method> list = new ArrayList<Method>();
			try {
				Class<?> cls = Class.forName(className);
				Constructor<?> con = cls.getConstructor(Context.class);
				
				if(!CaseListView.class.isAssignableFrom(cls)) {
					return;
				}
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
				if(m.isAnnotationPresent(Entry.class)) {
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
						
						TextView tv = new TextView(mContext);
						tv.setGravity(Gravity.CENTER_VERTICAL);
						tv.setText(" " + getItem(position).getName() + " ( )");

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
					Method method = listAdapter.getItem(position);
					try {
						method.invoke(parent);
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
					Toast.makeText(MainActivity.this, method.getName(), Toast.LENGTH_SHORT).show();
				}
			});
	}
	
	private View getFootView() {
		LinearLayout l = new LinearLayout(getBaseContext());
		l.setOrientation(LinearLayout.HORIZONTAL);
		l.setMinimumHeight(80); 
		l.setGravity(Gravity.CENTER_VERTICAL);
		
		TextView tv = new TextView(getBaseContext());
		tv.setGravity(Gravity.CENTER_VERTICAL);
		tv.setText("other app demo");
		tv.setTextColor(Color.BLACK);
		
		ImageView icon = new ImageView(getBaseContext());
		icon.setImageResource(R.drawable.folder);
		l.addView(icon);
		l.addView(tv);
		return l;
	}

	private void showDirView(Node node) {
		setContentView(R.layout.activity_main);
		ListView listView = (ListView) findViewById(R.id.list);

		if(CodeBag.ROOT_DIR.equals(node.mName) ) {
			listView.addFooterView(getFootView());
		}
		
		listView.setAdapter(new ListAdapter<Node>(MainActivity.this, node.mSubNodeList) {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				if(convertView == null) {
					Node node = getItem(position);
					
					LinearLayout l = new LinearLayout(mContext);
					l.setOrientation(LinearLayout.HORIZONTAL);
					l.setMinimumHeight(80); 
					l.setGravity(Gravity.CENTER_VERTICAL);
					
					TextView tv = new TextView(mContext);
					tv.setGravity(Gravity.CENTER_VERTICAL);
					
					ImageView icon = new ImageView(mContext);
					if(node.mType == Node.CLASS) {
						icon.setImageResource(R.drawable.file);
						tv.setText(node.mName + ".java");
					}else if(node.mType == Node.DIR) {
						icon.setImageResource(R.drawable.folder);
						tv.setText(node.mName);
					}
					
					l.addView(icon);
					l.addView(tv);
					
					
					if(getItemViewType(position) == NO_ENTRY) {
						l.setBackgroundResource(android.R.color.darker_gray);
					}
					convertView = l;
				}
				return convertView;
			}
			
			

			@Override
			public int getItemViewType(int position) {
				Node node = (Node) getItem(position);
				if(node.mType == Node.CLASS) {
					String className = node.mFullName;
					try {
						Class<?> cls = Class.forName(className);
						if(CaseListView.class.isAssignableFrom(cls)) {
							return ENTRY;
						}else{
							return NO_ENTRY;
						}
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
				return super.getItemViewType(position);
			}

			
		});
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Adapter adapter = parent.getAdapter();
		    	Node mNode = (Node) adapter.getItem(position);
		    	if(mNode != null) {
		    		if(adapter.getItemViewType(position) == ListAdapter.ENTRY) {
						Intent intent = new Intent(MainActivity.this, MainActivity.class);
						CodeBag codeBag = (CodeBag) getApplication();
						codeBag.setCurrentNode(mNode);
						startActivity(intent);
		    		}
		    	}else {
		    		int count = adapter.getCount();
		    		if(position == count - 1) {//footer
		    			Intent intent = new Intent(MainActivity.this, MainActivity.class);
						CodeBag codeBag = (CodeBag) getApplication();
						codeBag.setCurrentNode(codeBag.getAppDemoNode());
						startActivity(intent);
		    		}
		    	}
			}
		});
		
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				Adapter adapter = parent.getAdapter();
		    	Node mNode = (Node) adapter.getItem(position);
		    	if(mNode != null) {
		    		if(mNode.mFullName != null) {
		    			Log.addLog(MainActivity.this, mNode.mFullName);
		    			String dir = mNode.mFullName.replace(".", "/") + ".java";
		    			Log.addLog(MainActivity.this, dir);
		    			InputStream is = null;
						try {
							is = getAssets().open(dir);
						} catch (IOException e) {
							e.printStackTrace();
						}
						if(is != null) {
							String content = readTextFile(is);
							showAlertDialog(content);
						}
		    		}
		    		
		    	}
				return true;
			}
		});

	}
	
	private String readTextFile(InputStream inputStream) { 

		  ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); 

		  byte buf[] = new byte[1024]; 

		  int len; 

		  try { 

		   while ((len = inputStream.read(buf)) != -1) { 

		    outputStream.write(buf, 0, len); 

		   } 

		   outputStream.close(); 

		   inputStream.close(); 

		  } catch (IOException e) { 

		  } 

		  return outputStream.toString(); 

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
        case android.R.id.home:
        	finish();
        	break;
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
        	showAlertDialog(Log.getLog());
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
    
    private void showAlertDialog(String text) {
    	AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
    	ScrollView view = new ScrollView(MainActivity.this);
		TextView log = new TextView(MainActivity.this);
		log.setText(text);
		view.addView(log);
    	builder.setView(view);
    	builder.create().show();
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
    	public static final int NO_ENTRY = 0;
    	public static final int ENTRY = 1;
    	

    	public ListAdapter(Context context, ArrayList<T> list) {
    		mContext = context;
    		mList = list;
    	}
    	
    	@Override
		public int getCount() {
			return mList.size();
		}
		@Override
		public T getItem(int position) {
			return mList.get(position);
		}
		@Override
		public long getItemId(int position) {
			return position;
		}
		
		@Override
		public int getItemViewType(int position) {
			return ENTRY;
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
