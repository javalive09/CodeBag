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
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.SuperscriptSpan;
import android.view.Gravity;
import android.view.Menu;
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
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	public static final String NODE = "node";
	private AlertDialog mDialog = null;

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

	private void showMainView(Node node) {

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

	public void setView(CharSequence titleTxt, int titleIconResId, View view) {
		setContentView(R.layout.activity_root);
		FrameLayout container = (FrameLayout) findViewById(R.id.container);
		TextView title = (TextView) findViewById(R.id.title);
		title.setText(titleTxt);
		Drawable titleIcon = getResources().getDrawable(titleIconResId);
		titleIcon.setBounds(0, 0, titleIcon.getMinimumWidth(),
				titleIcon.getMinimumHeight());
		title.setCompoundDrawables(titleIcon, null, null, null);
		container.addView(view);
	}

	private Drawable getRightSizeIcon(BitmapDrawable drawable) {
		Drawable rightDrawable = getResources().getDrawable(
				R.drawable.ic_launcher);
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
		ListView listView = new ListView(MainActivity.this);
		setView(node.name, R.drawable.folder, listView);
		listView.setAdapter(new ListAdapter<Node>(MainActivity.this,
				node.mSubNodeList) {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				Node node = getItem(position);
				if (convertView == null) {
					convertView = View.inflate(MainActivity.this,
							R.layout.main_item, null);
				}
				TextView tv = (TextView) convertView
						.findViewById(R.id.listitem_tv);

				try {
					PackageManager pm = getPackageManager();
					ApplicationInfo info = pm.getApplicationInfo(node.name, 0);
					Drawable drawable = pm.getApplicationIcon(info);
					BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
					Drawable icon = getRightSizeIcon(bitmapDrawable);
					tv.setText(pm.getApplicationLabel(info));
					icon.setBounds(0, 0, icon.getIntrinsicWidth(),
							icon.getIntrinsicHeight());
					tv.setCompoundDrawables(icon, null, null, null);
				} catch (NameNotFoundException e) {
					e.printStackTrace();
				}
				return convertView;
			}
		});

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Adapter adapter = parent.getAdapter();
				Node mNode = (Node) adapter.getItem(position);
				if (mNode != null) {
					Intent intent = getPackageManager()
							.getLaunchIntentForPackage(mNode.className);
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
		setView(node.name + ".java", R.drawable.file, caseListview);
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
							convertView = View.inflate(MainActivity.this,
									R.layout.main_item, null);
						}
						TextView tv = (TextView) convertView
								.findViewById(R.id.listitem_tv);
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
				Intent intent = new Intent(MainActivity.this,
						MainActivity.class);
				intent.putExtra(NODE, node);
				startActivity(intent);
				Toast.makeText(MainActivity.this, node.name, Toast.LENGTH_SHORT)
						.show();
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
		setView(node.name, R.drawable.folder, listView);
		listView.setAdapter(new ListAdapter<Node>(MainActivity.this,
				node.mSubNodeList) {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				Node node = getItem(position);
				if (convertView == null) {
					convertView = View.inflate(MainActivity.this,
							R.layout.main_item, null);
				}
				TextView tv = (TextView) convertView
						.findViewById(R.id.listitem_tv);
				Drawable icon = null;
				CharSequence name = null;
				if (node.type == Node.CLASS) {
					icon = getResources().getDrawable(R.drawable.file);
					name = node.name + ".java";
				} else if (node.type == Node.DIR) {
					icon = getResources().getDrawable(R.drawable.folder);
					name = node.name + "  " + getSubFileCount(node) + "";
					name = getSpanStr(name.toString());
				} else if (node.type == Node.APP) {
					icon = getResources().getDrawable(R.drawable.folder);
					name = node.name + "  " + getSubFileCount(node) + "";
					name = getSpanStr(name.toString());
				}
				icon.setBounds(0, 0, icon.getIntrinsicWidth(),
						icon.getIntrinsicHeight());
				tv.setCompoundDrawables(icon, null, null, null);
				tv.setText(name);
				if (getItemViewType(position) == NO_ENTRY) {
					convertView
							.setBackgroundResource(android.R.color.darker_gray);
				}
				tv.setMovementMethod(TextViewFixTouchConsume.LocalLinkMovementMethod
						.getInstance());
				return convertView;
			}

			private SpannableString getSpanStr(String str) {
				SpannableString ss = new SpannableString(str);
				SuperscriptSpan st = new SuperscriptSpan();
				ForegroundColorSpan fs = new ForegroundColorSpan(Color.BLUE);
				RelativeSizeSpan as = new RelativeSizeSpan(0.6f);
				ClickableSpan cs = new ClickableSpan() {

					public void updateDrawState(TextPaint ds) {
						ds.setColor(ds.linkColor);
						ds.setUnderlineText(false); // 去掉下划线
					}

					@Override
					public void onClick(View widget) {
						TextView tv = (TextView) widget;
						String tvStr = tv.getText().toString();
						int start = tvStr.indexOf("  ") + 2;
						String count = tvStr.substring(start);
						String str = getResources().getString(
								R.string.item_subfile_count);
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
				Node mNode = (Node) adapter.getItem(position);
				if (mNode != null) {
					if (adapter.getItemViewType(position) == ListAdapter.ENTRY) {
						Intent intent = new Intent(MainActivity.this,
								MainActivity.class);
						intent.putExtra(NODE, mNode);
						startActivity(intent);
					}
				} else {
					int count = adapter.getCount();
					if (position == count - 1) {// footer
						Intent intent = new Intent(MainActivity.this,
								MainActivity.class);
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
				if (mNode != null) {
					if (mNode.className != null) {
						Log.addLog(MainActivity.this, mNode.className);
						String dir = mNode.className.replace(".", "/")
								+ ".java";
						Log.addLog(MainActivity.this, dir);
						InputStream is = null;
						try {
							is = getAssets().open(dir);
						} catch (IOException e) {
							e.printStackTrace();
						}
						if (is != null) {
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
			e.printStackTrace();
		}
		return outputStream.toString();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDialog == null) {
			mDialog = new AlertDialog.Builder(MainActivity.this).create();
		}
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break;
		case R.id.action_help:
			mDialog.setTitle(getResources().getString(R.string.action_help));
			mDialog.setMessage(getResources().getString(
					R.string.action_help_msg));
			mDialog.show();
			break;
		case R.id.action_about:
			mDialog.setTitle(getResources().getString(R.string.action_about));
			mDialog.setMessage(getResources().getString(
					R.string.action_about_msg));
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
