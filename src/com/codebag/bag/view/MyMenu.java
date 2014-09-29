package com.codebag.bag.view;

import java.util.ArrayList;

import com.codebag.R;
import com.codebag.code.mycode.utils.DisplayUtil;

import android.content.Context;
import android.graphics.drawable.PaintDrawable;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.PopupWindow;

public class MyMenu {
	
	private View mAnchor;

	private boolean mInit;
	
	private Context mContext;
	
	private ListView mListView;
	
	private PopupWindow mWindow;
	
	private ArrayList<MyMenuItem> items;
	
	private ItemViewCreater mItemViewCreater;

	private ItemViewOnClickListener mListener;
	
	public MyMenu(Context context) {
		mContext = context;
		items = new ArrayList<MyMenuItem>();
	}
	
	public void addMenuItem(int itemId, int order, CharSequence title) {
		MyMenuItem item = new MyMenuItem(itemId, order, title);
		items.add(itemId, item);
	}
	
	public void addMenuItem(int itemId, int order, int titleRes) {
		CharSequence title = mContext.getResources().getString(titleRes);
		addMenuItem(itemId, order, title);
	}
	
	public MyMenuItem getMenuItem(int itemId) {
		return items.get(itemId);
	}
	
	public void show() {
		if(!mInit) {
			initView();
		}
		mWindow.showAsDropDown(mAnchor);
	}
	
	public void dismiss() {
		mWindow.dismiss();
	}
	
	public boolean isShowing() {
		return mWindow.isShowing();
	}
	
	public void initView() {
		mListView = new ListView(mContext){

			@Override
			protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
				int expandSpec = MeasureSpec.makeMeasureSpec(Short.MAX_VALUE, MeasureSpec.AT_MOST);
				int width = DisplayUtil.dip2px(mContext, 150);
				widthMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
				super.onMeasure(widthMeasureSpec, expandSpec);
			}
			
			@Override
			public boolean dispatchKeyEvent(KeyEvent event) {
				if(event.getAction() == KeyEvent.ACTION_UP) {
					if(event.getKeyCode() == KeyEvent.KEYCODE_BACK 
							||event.getKeyCode() == KeyEvent.KEYCODE_MENU) {
						dismiss();
						return true;
					}
				}
				return super.dispatchKeyEvent(event);
			}
			
		    protected LayoutParams generateDefaultLayoutParams() {
		        return new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		    }
			
		};
		mWindow = new PopupWindow(mListView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
		mWindow.setOutsideTouchable(true);
		mWindow.setBackgroundDrawable(new PaintDrawable());
		mListView.setAdapter(new Adapter());
		mInit = true;
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if(mListener != null) {
					int order = getMenuItem(position).mOrder;
					mListener.OnItemClick(order);
				}
			}
		});
		mListView.setBackgroundResource(R.color.title_bar_color);
	}
	
	public void setAnchor(View anchor) {
		mAnchor = anchor;
	}
	
	public class Adapter extends BaseAdapter{

		@Override
		public int getCount() {
			return items.size();
		}

		@Override
		public Object getItem(int position) {
			return items.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if(mItemViewCreater != null) {
				return mItemViewCreater.createView(position, parent);
			}
			return null;
		}
		
	}
	
	public void setMenuItemCreater(ItemViewCreater creater) {
		mItemViewCreater = creater;
	}
	
	public void setMenuItemOnClickListener(ItemViewOnClickListener listener) {
		mListener = listener;
	}
	
	public interface ItemViewCreater{
		public View createView(int position, ViewGroup parent);
	}
	
	public interface ItemViewOnClickListener{
		public void OnItemClick(int order);
	}
	
}
