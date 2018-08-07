package com.javalive09.demos.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.javalive09.demos.R;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by peter on 2018/3/14.
 */

public class AppPicker extends ListView {

    private int showCount = 5;
    private @ColorInt
    int selectColor = Color.RED;
    private @ColorInt
    int normalColor = Color.BLACK;
    private int mFirstVisibleItem;
    private AppAdapter numAdapter;
    private int mTextSize;
    private int mDefaultSelectNum;
    private OnSelectListener selectListener;

    public AppPicker(Context context) {
        super(context);
    }

    public AppPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AppPicker setShowCount(int count) {
        this.showCount = count;
        return this;
    }

    public AppPicker setSelectItemColor(@ColorInt int selectColor) {
        this.selectColor = selectColor;
        return this;
    }

    public AppPicker setNormalItemColor(@ColorInt int normalColor) {
        this.normalColor = normalColor;
        return this;
    }

    public AppPicker setSelectListener(OnSelectListener selectListener) {
        this.selectListener = selectListener;
        return this;
    }

    public AppPicker setTextSize(int mTextSize) {
        this.mTextSize = mTextSize;
        return this;
    }

    public String getSelectItemString() {
        return numAdapter.getItem(mFirstVisibleItem + showCount / 2);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    public AppPicker setDefaultItemNum(char c) {
        this.mDefaultSelectNum = c - 'A';
        if (numAdapter != null) {
            setDefaultSelectNum();
        }
        return this;
    }

    private void postSetSection(final int num) {
        post(new Runnable() {
            @Override
            public void run() {
                setSelection(num);
            }
        });
    }

    private void setDefaultSelectNum() {
        if (mDefaultSelectNum > 0) {//had set default data
            int i = Integer.MAX_VALUE / 2 % 26;
            int offset = Math.abs(26 - showCount / 2 - i);
            postSetSection(Integer.MAX_VALUE / 2 + offset + mDefaultSelectNum);
        } else {
            int i = Integer.MAX_VALUE / 2 % 26;
            int offset = Math.abs(26 - showCount / 2 - i);
            setSelection(Integer.MAX_VALUE / 2 + offset);
            postSetSection(Integer.MAX_VALUE / 2 + offset);
        }
    }

    public ArrayList<AppModel> loadInBackground() {
        PackageManager mPm = getContext().getPackageManager();
        // retrieve the list of installed applications
        List<ApplicationInfo> apps = mPm.getInstalledApplications(0);

        if (apps == null) {
            apps = new ArrayList<ApplicationInfo>();
        }

        final Context context = getContext();

        // create corresponding apps and load their labels
        ArrayList<AppModel> items = new ArrayList<AppModel>(apps.size());
        for (int i = 0; i < apps.size(); i++) {
            String pkg = apps.get(i).packageName;

            // only apps which are launchable
            if (context.getPackageManager().getLaunchIntentForPackage(pkg) != null) {
                AppModel app = new AppModel(context, apps.get(i));
                app.loadLabel(context);
                items.add(app);
            }
        }

        // sort the list
//        Collections.sort(items, ALPHA_COMPARATOR);

        return items;
    }

    public void build() {
        setDividerHeight(0);
        setVerticalScrollBarEnabled(false);
        numAdapter = new AppAdapter(showCount, mTextSize);
        setAdapter(numAdapter);
        setDefaultSelectNum();
        setOnScrollListener(new OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
                    postSetSection(mFirstVisibleItem);
                    if (selectListener != null) {
                        selectListener.endSelect();
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                final View firstChild = view.getChildAt(0);
                if (firstChild != null) {
                    int height = firstChild.getMeasuredHeight();
                    int scrollYOffset = -getChildAt(0).getTop();
                    if (scrollYOffset > height / 2) { //auto up
                        mFirstVisibleItem = firstVisibleItem + 1;
                        refreshColor(view, showCount / 2 + 1);
                    } else {//auto down
                        mFirstVisibleItem = firstVisibleItem;
                        refreshColor(view, showCount / 2);
                    }
                }
            }

            private void refreshColor(AbsListView view, int selectItem) {
                for (int i = 0; i < showCount; i++) {
                    TextView child = (TextView) view.getChildAt(i);
                    if (i == selectItem) {
                        if (child != null) {
                            child.setTextColor(selectColor);
                        }
                    } else {
                        if (child != null) {
                            child.setTextColor(normalColor);
                        }
                    }
                }
            }

        });
    }

    private static final class AppAdapter extends BaseAdapter {

        private int showCount;
        private int mTextSize;

        AppAdapter(int showCount, int mTextSize) {
            this.showCount = showCount;
            this.mTextSize = mTextSize;
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public String getItem(int position) {
            return String.valueOf((char) (65 + position % 26));
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = new TextView(parent.getContext());
                TextView numView = (TextView) convertView;
                numView.setGravity(Gravity.CENTER);
                int listViewH = parent.getMeasuredHeight();
                if (listViewH > 0) {
                    numView.setHeight(listViewH / showCount);
                }
                Drawable drawableLeft = parent.getResources().getDrawable(R.drawable.bitmap);
                numView.setCompoundDrawablesWithIntrinsicBounds(drawableLeft, null, null, null);
                numView.setCompoundDrawablePadding(4);
                if (mTextSize > 0) {
                    numView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
                }
            }
            ((TextView) convertView).setText(getItem(position));
            return convertView;
        }
    }

    public interface OnSelectListener {
        void endSelect();
    }

}
