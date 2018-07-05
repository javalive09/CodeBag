package com.javalive09.demos.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by peter on 2018/3/14.
 */

public class NumPicker extends ListView {

    private int showCount = 5;
    private int maxValue = 60;
    private @ColorInt int selectColor = Color.RED;
    private @ColorInt int normalColor = Color.BLACK;
    private int mFirstVisibleItem;
    private NumAdapter numAdapter;
    private int mTextSize;
    private boolean loop;

    public NumPicker(Context context) {
        super(context);
    }

    public NumPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NumPicker setShowCount(int count) {
        this.showCount = count;
        return this;
    }

    public NumPicker setMaxValue(int maxValue) {
        this.maxValue = maxValue;
        return this;
    }

    public NumPicker setSelectItemColor(@ColorInt int selectColor) {
        this.selectColor = selectColor;
        return this;
    }

    public NumPicker setNormaoItemColor(@ColorInt int normalColor) {
        this.normalColor = normalColor;
        return this;
    }

    public NumPicker setTextSize(int mTextSize) {
        this.mTextSize = mTextSize;
        return this;
    }

    public NumPicker setLoop(boolean loop) {
        this.loop = loop;
        return this;
    }

    public String getSelectItem() {
        return numAdapter.getItem(mFirstVisibleItem + showCount / 2);
    }

    public void build() {
        setDividerHeight(0);
        setVerticalScrollBarEnabled(false);
        numAdapter = new NumAdapter(showCount, maxValue, mTextSize, loop);
        setAdapter(numAdapter);
        if (loop) {
            post(() -> {
                int i = Integer.MAX_VALUE / 2 % (maxValue + 1);
                int offset = Math.abs(maxValue + 1 - showCount / 2 - i);
                setSelection(Integer.MAX_VALUE / 2 + offset);
            });
        }
        setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    setSelection(mFirstVisibleItem);
                    Toast.makeText(view.getContext(), getSelectItem(), Toast.LENGTH_SHORT).show();
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
                    if (i == selectItem) {
                        ((TextView) view.getChildAt(i)).setTextColor(selectColor);
                    } else {
                        ((TextView) view.getChildAt(i)).setTextColor(normalColor);
                    }
                }
            }

        });
    }

    private static final class NumAdapter extends BaseAdapter {

        private int showCount;
        private boolean loop;
        private int maxValue;
        private int mTextSize;

        NumAdapter(int showCount, int maxValue, int mTextSize, boolean loop) {
            this.showCount = showCount;
            this.loop = loop;
            this.maxValue = maxValue + 1;
            this.mTextSize = mTextSize;
        }

        @Override
        public int getCount() {
            return loop ? Integer.MAX_VALUE : maxValue + showCount / 2 * 2;
        }

        @Override
        public String getItem(int position) {
            String value = "";
            if (loop) {
                value = String.valueOf(position % maxValue);
            } else {
                if (position >= showCount / 2) {
                    if (position < maxValue + showCount / 2) {
                        value = String.valueOf(position - showCount / 2);
                    }
                }
            }
            return value;
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
                if (mTextSize > 0) {
                    numView.setTextSize(mTextSize);
                }
            }
            ((TextView) convertView).setText(getItem(position));
            return convertView;
        }
    }

}
