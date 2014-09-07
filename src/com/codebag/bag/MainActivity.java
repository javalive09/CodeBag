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
import com.codebag.code.mycode.utils.Log;

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
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.SuperscriptSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	public static final String NODE = "node";
	private AlertDialog mDialog = null;
	 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getOverflowMenu();
        Intent intent = getIntent();
        Node currentNode = null;
        if(intent != null) {
        	currentNode = (Node) intent.getSerializableExtra(NODE);
        }
        
        if(currentNode == null) {//root 
        	showSplash();  
        }else {
        	showMainView(currentNode);
        }
        CodeBag codeBag = (CodeBag) getApplication();
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
//			getActionBar().setHomeButtonEnabled(true);
//			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
		
		switch(node.mType) {
			case Node.DIR:
//				getActionBar().setTitle(node.mName);
//				getActionBar().setIcon(R.drawable.folder);
				showDirView(node);
				break;
			case Node.CLASS:
//				getActionBar().setTitle(node.mName + ".java");
//				getActionBar().setIcon(R.drawable.file);
				showClassView(node);
				break;
			case Node.APP:
//				getActionBar().setTitle(node.mName);
//				getActionBar().setIcon(R.drawable.folder);
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
//		setContentView(R.layout.activity_main);
		setView(R.layout.activity_main);
		ListView listView = (ListView) findViewById(R.id.list);

		listView.setAdapter(new ListAdapter<Node>(MainActivity.this, node.mSubNodeList) {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				Node node = getItem(position);
				View v = View.inflate(MainActivity.this, R.layout.main_item, null);
				TextView tv = (TextView) v.findViewById(R.id.listitem_tv);
				
				try {
					PackageManager pm = getPackageManager();
					ApplicationInfo info = pm.getApplicationInfo(node.mFullName, 0);
					Drawable drawable = pm.getApplicationIcon(info);
					BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
					Drawable icon = getRightSizeIcon(bitmapDrawable);
					tv.setText(pm.getApplicationLabel(info));
					icon.setBounds(0, 0, icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
					tv.setCompoundDrawables(icon, null, null, null);
				} catch (NameNotFoundException e) {
					e.printStackTrace();
				}    
				
				if(getItemViewType(position) == NO_ENTRY) {
					tv.setBackgroundResource(android.R.color.darker_gray);
				}
				return v;
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
//				setContentView(caseListview);
				setView(caseListview);

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
					View v = View.inflate(MainActivity.this, R.layout.main_item, null);
					TextView tv = (TextView) v.findViewById(R.id.listitem_tv);
					tv.setText(getItem(position).getName() + " ( )");
					return v;
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
	
	public int getSubFileCount(Node node) {
		int count = 0;
		ArrayList<Node> nodes = node.mSubNodeList;
		if(nodes != null) {
			for(int i = 0, len = nodes.size(); i < len; i++) {
				Node n = nodes.get(i);
				if(n.mType == Node.CLASS || n.mType == Node.APP) {
					count++;
				}else if(n.mType == Node.DIR){
					count += getSubFileCount(n);
				}
			}
		}
		return count;
	}

	private void setView(int resId) {
		setContentView(R.layout.activity_root_view);
		FrameLayout root = (FrameLayout) findViewById(R.id.contentView);
		LayoutInflater factory = LayoutInflater.from(MainActivity.this);
        factory.inflate(resId, root);
	}
	
	private void setView(View content) {
		setContentView(R.layout.activity_root_view);
		FrameLayout root = (FrameLayout) findViewById(R.id.contentView);
		root.addView(content);
	}
	
	private void showDirView(Node node) {
//		setContentView(R.layout.activity_main);
		setView(R.layout.activity_main);
		ListView listView = (ListView) findViewById(R.id.list);
		
		listView.setAdapter(new ListAdapter<Node>(MainActivity.this, node.mSubNodeList) {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				Node node = getItem(position);
				View v = View.inflate(MainActivity.this, R.layout.main_item, null);
				TextView tv = (TextView) v.findViewById(R.id.listitem_tv);
				Drawable icon = null;
				CharSequence name = null;
				if(node.mType == Node.CLASS) {
					icon = getResources().getDrawable(R.drawable.file);
					name = node.mName + ".java";
				}else if(node.mType == Node.DIR) {
					icon = getResources().getDrawable(R.drawable.folder);
					name = node.mName + "  " + getSubFileCount(node) + "";
					name = getSpanStr(name.toString());
				}else if(node.mType == Node.APP) {
					icon = getResources().getDrawable(R.drawable.folder);
					name = node.mName + "  " + getSubFileCount(node) + "";
					name = getSpanStr(name.toString());
				}
				icon.setBounds(0, 0, icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
				tv.setCompoundDrawables(icon, null, null, null);
				tv.setText(name);
				if(getItemViewType(position) == NO_ENTRY) {
					v.setBackgroundResource(android.R.color.darker_gray);
				}
				tv.setMovementMethod(TextViewFixTouchConsume.LocalLinkMovementMethod.getInstance());
				return v;
			}
			
			private SpannableString getSpanStr(String str) {
				SpannableString ss = new SpannableString(str);
				SuperscriptSpan st = new SuperscriptSpan();
				ForegroundColorSpan fs = new ForegroundColorSpan(Color.BLUE);
				RelativeSizeSpan as = new RelativeSizeSpan(0.6f);
				ClickableSpan cs = new ClickableSpan(){

					public void updateDrawState(TextPaint ds) {
					    ds.setColor(ds.linkColor);
					    ds.setUnderlineText(false); //去掉下划线
					}
					
					@Override
					public void onClick(View widget) {
						TextView tv = (TextView) widget;
						String tvStr = tv.getText().toString();
						int start = tvStr.indexOf("  ") + 2;
						String count = tvStr.substring(start);
						String str = getResources().getString(R.string.item_subfile_count);
						str = String.format(str, count);
						showAlertDialog(str);
					}
					
				};
				int start = str.indexOf("  ") + 2;
				int end = str.length();
				ss.setSpan(st, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);    
				ss.setSpan(fs, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);     
				ss.setSpan(as, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);     
				ss.setSpan(cs, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);     
				return ss;
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
						intent.putExtra(NODE, mNode);
						startActivity(intent);
		    		}
		    	}else {
		    		int count = adapter.getCount();
		    		if(position == count - 1) {//footer
		    			Intent intent = new Intent(MainActivity.this, MainActivity.class);
						intent.putExtra(NODE, mNode);
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
    
//    private void getOverflowMenu() {
//        try {
//           ViewConfiguration config = ViewConfiguration.get(this);
//           Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
//           if(menuKeyField != null) {
//               menuKeyField.setAccessible(true);
//               menuKeyField.setBoolean(config, false);
//           }
//       } catch (Exception e) {
//           e.printStackTrace();
//       }
//   }

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
		codeBag.removeActivity(this);
	}
    
    public static class ListAdapter<T> extends BaseAdapter {
    	ArrayList<T> mList;
    	Context mContext;
    	public static final int NO_ENTRY = 0; //不含有入口方法
    	public static final int ENTRY = 1; //含有入口方法
    	

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
