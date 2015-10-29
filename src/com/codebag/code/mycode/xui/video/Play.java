package com.codebag.code.mycode.xui.video;

import android.net.Uri;
import android.widget.VideoView;

import com.codebag.R;
import com.codebag.bag.Entry;
import com.codebag.bag.MyCode;
import com.codebag.bag.main.InovkedViewActivity;

public class Play extends MyCode {

	public Play(InovkedViewActivity act) {
		super(act);
	}

	@Entry
	public void play() {
		VideoView view = new VideoView(getActivity());
		String path = "android.resource://" + getActivity().getPackageName() + "/" + R.raw.video;
		view.setVideoURI(Uri.parse(path));
		view.start();
		showView(view);
	}
}
