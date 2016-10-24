package com.javalive09.sample.view.drawable.scaledrawable;

import android.graphics.drawable.ScaleDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.javalive09.codebag.Entry;
import com.javalive09.sample.R;

public class Invoke extends Entry {

	public void showScale() {
		View v = View.inflate(getViewActivity(), R.layout.drawable_scale, null);
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
