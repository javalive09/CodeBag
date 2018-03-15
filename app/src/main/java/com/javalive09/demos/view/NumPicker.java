package com.javalive09.demos.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by peter on 2018/3/14.
 */

public class NumPicker extends ListView {

    public NumPicker(Context context) {
        super(context);
        setDividerHeight(0);
    }

    public NumPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        setDividerHeight(0);
    }

    public NumPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setDividerHeight(0);
    }

    public static final class Adapter extends BaseAdapter {

        private ListView listView;

        private String[] value =
                {"", "", "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15",
                        "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31",
                        "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47",
                        "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "", ""};
        private int[] key =
                {-1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25,
                        26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49,
                        50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, -1, -1};

        private static final int MAX_SHOW = 5;
        private Context context;

        public Adapter(Context context) {
            this.context = context;
        }

        public int getValue() {
            int currentPos = listView.getFirstVisiblePosition() + MAX_SHOW/2;
            return key[currentPos];
        }

        @Override
        public int getCount() {
            return value.length;
        }

        @Override
        public String getItem(int position) {
            return value[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            listView = (ListView) parent;
            if (convertView == null) {
                convertView = new TextView(context);
            }
            TextView numView = (TextView) convertView;
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(-1, -1);
            numView.setLayoutParams(params);
            numView.setGravity(Gravity.CENTER);
            int listViewH = listView.getMeasuredHeight();
            if (listViewH > 0) {
                numView.setHeight(listViewH / MAX_SHOW);
            }
            String value = getItem(position);
            numView.setText(value);
            int firstPos = listView.getFirstVisiblePosition();
            int centerPos = firstPos + MAX_SHOW / 2;
            if (position == centerPos) {
                numView.setTextColor(Color.parseColor("#ff0000"));
            } else {
                numView.setTextColor(Color.parseColor("#000000"));
            }
            return convertView;
        }
    }

}
