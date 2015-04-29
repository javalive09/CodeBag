package com.codebag.code.mycode.view.drawable.scaledrawable;

import android.graphics.drawable.ScaleDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.codebag.R;
import com.codebag.bag.Entry;
import com.codebag.bag.MainActivity;
import com.codebag.bag.MyCode;

public class Invoke extends MyCode {

	public Invoke(MainActivity act) {
		super(act);
	}
	
	@Entry
	public void showScale() {
		View v = View.inflate(getActivity(), R.layout.drawable_scale, null);
		ImageView iv = (ImageView) v.findViewById(R.id.image);
		final ScaleDrawable sd = (ScaleDrawable) iv.getBackground();
		sd.setLevel(10000);
		SeekBar seb = (SeekBar) v.findViewById(R.id.seekbar);
		seb.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				sd.setLevel(progress);
			}
		});
		
		showView(v);
	}

}
