package com.javalive09.sample.project.xui.guide;

import com.javalive09.codebag.Entry;
import com.javalive09.sample.R;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 比较大的图片文件 放错res drawable位置 会引起卡顿
 */
public class SampleActivity extends Entry {

    private static final int[] resource = new int[] { R.drawable.guide1,
            R.drawable.guide2, R.drawable.guide3 };

    private static final String[] guide = new String[]{
            "想把歌曲、地图、应用传到车中吗？",
            "在家wifi下载",
            "到车中一键0流量无线传输！"
    };

    private static final String SUB_TITLE = "商店里的歌曲、地图、应用！";


    int currentPos = -1;

    public void invoke() {
        View view = showView(R.layout.xui_guide_sample);
        MyAdapter adpter = new MyAdapter();
        ColorAnimationView colorAnimationView = (ColorAnimationView) view.findViewById(R.id.ColorAnimationView);
        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        viewPager.setAdapter(adpter);
        colorAnimationView.setmViewPager(viewPager, 4, Color.parseColor("#ff30d9fd")
                ,Color.parseColor("#ffa7da5a")
                ,Color.parseColor("#fffdcd4b")
                ,Color.WHITE);
        colorAnimationView
                .setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position,
                                               float positionOffset, int positionOffsetPixels) {
                        currentPos = viewPager.getCurrentItem();
                    }

                    @Override
                    public void onPageSelected(int position) {
                        final int count = viewPager.getChildCount();
                        if(position > currentPos) {//向left
                            Log.i("peter", "向左滑动");

                            for(int i = 0 ; i< count; i++) {
                                View childRoot = viewPager.getChildAt(i);
                                View child = childRoot.findViewById(R.id.item_root);
                                int viewPos = (Integer) child.getTag();
                                View title = child.findViewById(R.id.title);
                                if(viewPos == position) {//要滑向的pos
                                    title.setVisibility(View.VISIBLE);
                                    ObjectAnimator.ofFloat(title, "translationX", 50, 0).setDuration(1000).start();
                                    if(viewPos == 1) {
                                        View titleHead = child.findViewById(R.id.title_head);
                                        titleHead.setVisibility(View.VISIBLE);
                                        ObjectAnimator.ofFloat(titleHead, "translationX", 50, 0).setDuration(1000).start();
                                    }
                                    if(viewPos == 3) {
                                        Toast.makeText(getActivity(), "end", Toast.LENGTH_SHORT).show();
                                        getActivity().finish();
                                    }
                                }else {
                                    title.setVisibility(View.INVISIBLE);
                                    if(viewPos == 1) {
                                        View titleHead = child.findViewById(R.id.title_head);
                                        titleHead.setVisibility(View.INVISIBLE);
                                    }
                                }
                            }

                        }else {//向right
                            Log.i("peter", "向右滑动");
                            for(int i = 0 ; i< count; i++) {
                                View childRoot = viewPager.getChildAt(i);
                                View child = childRoot.findViewById(R.id.item_root);
                                int viewPos = (Integer) child.getTag();
                                View title = child.findViewById(R.id.title);
                                if(viewPos == position) {//要滑向的pos
                                    title.setVisibility(View.VISIBLE);
                                    ObjectAnimator.ofFloat(title, "translationX", -50, 0).setDuration(1000).start();
                                    if(viewPos == 1) {
                                        View titleHead = child.findViewById(R.id.title_head);
                                        titleHead.setVisibility(View.VISIBLE);
                                        ObjectAnimator.ofFloat(titleHead, "translationX", -50, 0).setDuration(1000).start();
                                    }
                                }else {
                                    title.setVisibility(View.INVISIBLE);
                                    if(viewPos == 1) {
                                        View titleHead = child.findViewById(R.id.title_head);
                                        titleHead.setVisibility(View.INVISIBLE);
                                    }
                                }
                            }

                        }
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {}
                });
    }

    public class MyAdapter extends RecyclingPagerAdapter {

        LayoutInflater inflater;

        public MyAdapter() {
            inflater = LayoutInflater.from(getActivity());
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container) {
            View view = null;
            if(convertView == null) {
                view = inflater.inflate(R.layout.guide_item, container, false);
            }else {
                view = convertView;
            }

            ImageView iv = (ImageView) view.findViewById(R.id.img);
            String sp = "";
            if(position != 3) {
                iv.setImageResource(resource[position]);
                sp = guide[position];
            }

            SpannableString ss = new SpannableString(sp);
            TextView tv = (TextView) view.findViewById(R.id.title);
            view.setVisibility(View.VISIBLE);
			switch(position){
			case 0:
				tv.setVisibility(View.VISIBLE);
				tv.setShadowLayer(0, 5, 3, Color.parseColor("#083c5a"));
				tv.setText(ss);
				break;
			case 1:
				tv.setVisibility(View.INVISIBLE);
				tv.setShadowLayer(0, 5, 3, Color.parseColor("#44680c"));
				tv.setText(SUB_TITLE);

				TextView subTxt = (TextView) view.findViewById(R.id.title_head);
				ss.setSpan(new AbsoluteSizeSpan(30, true), 2, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
				subTxt.setText(ss);
				subTxt.setVisibility(View.INVISIBLE);
				subTxt.setShadowLayer(0, 5, 3, Color.parseColor("#44680c"));
				break;
			case 2:
				tv.setVisibility(View.INVISIBLE);
				ss.setSpan(new AbsoluteSizeSpan(30, true), 3, 8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
				tv.setShadowLayer(0, 5, 3, Color.parseColor("#836411"));
				tv.setText(ss);
				break;
			case 3:
				view.setVisibility(View.GONE);
				break;
			}
            view.setTag(position);
            return view;
        }

        @Override
        public int getCount() {
            return 4;
        }

    }

}
