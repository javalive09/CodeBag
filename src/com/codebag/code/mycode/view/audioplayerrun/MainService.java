package com.codebag.code.mycode.view.audioplayerrun;

import com.codebag.R;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

public class MainService extends Service {

	private MediaPlayer player;

	private AudioManager mAm;

	private MyOnAudioFocusChangeListener mListener;

	@Override
	public void onCreate() {

		player = MediaPlayer.create(this, R.raw.child); // 在res目录下新建raw目录，复制一个
														// mp3文件到此目录下。
		player.setLooping(false);

		mAm = (AudioManager) getApplicationContext().getSystemService(
				Context.AUDIO_SERVICE);
		mListener = new MyOnAudioFocusChangeListener();
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onStart(Intent intent, int startid) {
		Toast.makeText(this, "My Service Start", Toast.LENGTH_LONG).show();

		// Request audio focus for playback
		int result = mAm.requestAudioFocus(mListener,
				AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);

		if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
			// Start playback.
			player.start();
		} else {

		}
	}

	@Override
	public void onDestroy() {
		Toast.makeText(this, "My Service Stoped", Toast.LENGTH_LONG).show();
		player.stop();

		mAm.abandonAudioFocus(mListener);
	}

	private class MyOnAudioFocusChangeListener implements
			OnAudioFocusChangeListener {
		@Override
		public void onAudioFocusChange(int focusChange) {

		}
	}
}