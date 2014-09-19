package com.codebag.bag;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
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
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnMenuItemClickListener{

	private static final String NODE = "node";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		Node currentNode = null;
		if (intent != null) {
			currentNode = (Node) intent.getSerializableExtra(NODE);
		}

		if (currentNode == null) {// root
			showSplash();
		} else {
			showMainView(currentNode);
		}
		CodeBag codeBag = (CodeBag) getApplication();
		codeBag.addActivity(this);
	}

	private void showSplash() {
		setContentView(new SplashView(MainActivity.this));

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

	public void onClick(View view) {
		switch(view.getId()) {
		case R.id.back:
			finish();
			break;
		case R.id.menu:
			showMenu();
			break;
		default:
			break;
		}
	}
	
	private void showMenu() {
		PopupMenu popup = new PopupMenu(this, findViewById(R.id.menu));
		popup.setOnMenuItemClickListener(this);
		MenuInflater inflater = popup.getMenuInflater();
		inflater.inflate(R.menu.main, popup.getMenu());
		popup.show();
	}
	
	@Override
	public boolean onMenuItemClick(MenuItem item) {
		AlertDialog mDialog = new AlertDialog.Builder(MainActivity.this).create();
		mDialog.setCanceledOnTouchOutside(true);
		switch (item.getItemId()) {
		case R.id.action_help:
			showAlertDialog(getString(R.string.action_help), getString(R.string.action_help_msg));
			break;
		case R.id.action_about:
			showAlertDialog(getString(R.string.action_about), getString(R.string.action_about_msg));
			break;
		case R.id.action_feedback:
			break;
		case R.id.action_showlog:
			showAlertDialog("log", Log.getLog());
			break;
		case R.id.action_clearlog:
			Log.clearLog();
			break;
		case R.id.action_exit:
			((CodeBag) getApplication()).exit();
			break;
		}
		return true;
	}

	private void showAlertDialog(String title, String content) {
		AlertDialog dialog = new AlertDialog.Builder(MainActivity.this).create();
		dialog.setCanceledOnTouchOutside(true);
		ScrollView view = new ScrollView(MainActivity.this);
		TextView msg = new TextView(MainActivity.this);
		msg.setText(content);
		view.addView(msg);
		dialog.setTitle(title);
		dialog.setView(view);
		dialog.show();
	}
	
	private void showMainView(Node node) {
		setContentView(R.layout.activity_root);
		switch (node.type) {
		case Node.DIR:
			showDirView(node);
			break;
		case Node.CLASS:
			showClassView(node);
			break;
		case Node.METHOD:
			showMethodView(node);
			break;
		case Node.APP:
			showAppDemoView(node);
			break;
		}
		// Debug.stopMethodTracing();
	}

	private void setTitle(CharSequence titleTxt, int titleIconResId) {
		TextView title = (TextView) findViewById(R.id.title);
		title.setText(titleTxt);
		Drawable titleIcon = getResources().getDrawable(titleIconResId);
		titleIcon.setBounds(0, 0, titleIcon.getMinimumWidth(),
				titleIcon.getMinimumHeight());
		title.setCompoundDrawables(titleIcon, null, null, null);
	}

	public void showView(View view) {
		FrameLayout container = (FrameLayout) findViewById(R.id.container);
		container.removeAllViews();
		container.addView(view);
	}
	
	public void showView(View view, FrameLayout.LayoutParams params) {
		FrameLayout container = (FrameLayout) findViewById(R.id.container);
		container.removeAllViews();
		container.addView(view, params);
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
		Bitmap bm = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
		return new BitmapDrawable(getResources(), bm);
	}

	private void showAppDemoView(Node node) {
		ListView listView = new ListView(MainActivity.this);
		setTitle(node.name, R.drawable.folder);
		showView(listView);
		listView.setAdapter(new ListAdapter<Node>(MainActivity.this, node.mSubNodeList) {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				Node node = getItem(position);
				if (convertView == null) {
					convertView = View.inflate(MainActivity.this, R.layout.main_item, null);
				}
				TextView tv = (TextView) convertView.findViewById(R.id.listitem_tv);
				try {
					PackageManager pm = getPackageManager();
					ApplicationInfo info = pm.getApplicationInfo(node.name, 0);
					Drawable drawable = pm.getApplicationIcon(info);
					BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
					Drawable icon = getRightSizeIcon(bitmapDrawable);
					tv.setText(pm.getApplicationLabel(info));
					icon.setBounds(0, 0, icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
					tv.setCompoundDrawables(icon, null, null, null);
				} catch (NameNotFoundException e) {
					e.printStackTrace();
				}
				return convertView;
			}
		});

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Adapter adapter = parent.getAdapter();
				Node mNode = (Node) adapter.getItem(position);
				if (mNode != null) {
					Intent intent = getPackageManager().getLaunchIntentForPackage(mNode.className);
					startActivity(intent);
				}
			}
		});
	}

	private void showMethodView(Node node) {
		String methodName = node.name;
		String className = node.className;
		try {
			Class<?> cls = Class.forName(className);
			Constructor<?> con = cls.getConstructor(MainActivity.class);
			Object obj = con.newInstance(MainActivity.this);
			Method method = cls.getDeclaredMethod(methodName);
			setTitle(method.getName(), R.drawable.file);
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

	private void showClassView(Node node) {
		// class file
		final String className = node.className;
		ListView caseListview = new ListView(MainActivity.this);
		setTitle(node.name + ".java", R.drawable.file);
		showView(caseListview);
		ArrayList<String> list = new ArrayList<String>();
		try {
			Class<?> cls = Class.forName(className);
			Method[] methods = cls.getDeclaredMethods();
			for (Method m : methods) {
				if (m.isAnnotationPresent(Entry.class)) {
					list.add(m.getName());
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		caseListview.setAdapter(new ListAdapter<String>(MainActivity.this, list) {

					@Override
					public View getView(int position, View convertView,
							ViewGroup parent) {
						if (convertView == null) {
							convertView = View.inflate(MainActivity.this, R.layout.main_item, null);
						}
						TextView tv = (TextView) convertView.findViewById(R.id.listitem_tv);
						tv.setText(getItem(position) + " ( )");
						return convertView;
					}
				});
		caseListview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String methodName = (String) parent.getAdapter().getItem(
						position);
				Node node = new Node(methodName, Node.METHOD, className);
				Intent intent = new Intent(MainActivity.this, MainActivity.class);
				intent.putExtra(NODE, node);
				startActivity(intent);
				Toast.makeText(MainActivity.this, node.name, Toast.LENGTH_SHORT).show();
			}
		});
	}

	public int getSubFileCount(Node node) {
		int count = 0;
		ArrayList<Node> nodes = node.mSubNodeList;
		if (nodes != null) {
			for (int i = 0, len = nodes.size(); i < len; i++) {
				Node n = nodes.get(i);
				if (n.type == Node.CLASS || n.type == Node.APP) {
					count++;
				} else if (n.type == Node.DIR) {
					count += getSubFileCount(n);
				}
			}
		}
		return count;
	}

	private void showDirView(Node node) {
		ListView listView = new ListView(MainActivity.this);
		setTitle(node.name, R.drawable.folder);
		showView(listView);
		listView.setAdapter(new ListAdapter<Node>(MainActivity.this, node.mSubNodeList) {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				Node node = getItem(position);
				if (convertView == null) {
					convertView = View.inflate(MainActivity.this, R.layout.main_item, null);
				}
				TextView tv = (TextView) convertView.findViewById(R.id.listitem_tv);
				Drawable icon = null;
				CharSequence name = node.name;
				if (node.type == Node.CLASS) {
					icon = getResources().getDrawable(R.drawable.file);
					name = name + ".java";
				} else if (node.type == Node.DIR) {
					icon = getResources().getDrawable(R.drawable.folder);
				} else if (node.type == Node.APP) {
					icon = getResources().getDrawable(R.drawable.folder);
				}
				icon.setBounds(0, 0, icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
				tv.setCompoundDrawables(icon, null, null, null);
				tv.setText(name);
				if (getItemViewType(position) == NO_ENTRY) {
					convertView.setBackgroundResource(android.R.color.darker_gray);
				}
				tv.setMovementMethod(TextViewFixTouchConsume.LocalLinkMovementMethod.getInstance());
				return convertView;
			}

			@Override
			public int getItemViewType(int position) {
				Node node = (Node) getItem(position);
				if (node.type == Node.CLASS) {
					String className = node.className;
					try {
						Class<?> cls = Class.forName(className);
						Method[] methods = cls.getDeclaredMethods();
						for (Method m : methods) {
							if (m.isAnnotationPresent(Entry.class)) {
								return ENTRY;
							}
						}
						return NO_ENTRY;
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
				return ENTRY;
			}

		});

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Adapter adapter = parent.getAdapter();
				Node node = (Node) adapter.getItem(position);
				if (node != null) {
					if (adapter.getItemViewType(position) == ListAdapter.ENTRY) {
						Intent intent = new Intent(MainActivity.this, MainActivity.class);
						intent.putExtra(NODE, node);
						startActivity(intent);
					}
				} else {
					int count = adapter.getCount();
					if (position == count - 1) {// footer
						Intent intent = new Intent(MainActivity.this, MainActivity.class);
						intent.putExtra(NODE, node);
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
				Node node = (Node) adapter.getItem(position);
				if (node != null) {
					if(node.type == Node.CLASS) {
						Log.addLog(MainActivity.this, node.className);
						String dir = node.className.replace(".", "/") + ".java";
						Log.addLog(MainActivity.this, dir);
						InputStream is = null;
						try {
							is = getAssets().open(dir);
						} catch (IOException e) {
							e.printStackTrace();
						}
						if (is != null) {
							String content = readTextFile(is);
							showAlertDialog(node.name, content);
						}
					}else if(node.type == Node.DIR) {
						int count = getSubFileCount(node);
						showAlertDialog("java文件个数", count + "个");
					}else if(node.type == Node.APP) {
						int count = getSubFileCount(node);
						showAlertDialog("app demo 个数", count + "个");
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
			e.printStackTrace();
		}
		return outputStream.toString();
	}
	
	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_MENU) {
			showMenu();
			return true;
		}
		return super.onKeyUp(keyCode, event);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return false;
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
		public static final int NO_ENTRY = 0; // 不含有入口方法
		public static final int ENTRY = 1; // 含有入口方法

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

	public class SplashView extends TextView {

		public SplashView(Context context) {
			super(context);
			init(context);
		}

		private void init(Context context) {
			setText("Loading...");
			setTextColor(Color.BLACK);
			setTextSize(50);
			setBackgroundColor(Color.WHITE);
			setGravity(Gravity.CENTER);
		}
	}

}
