package com.codebag.code.mycode.function.delete_file_and4_4;

import java.io.File;
import java.io.IOException;

import android.content.ContentResolver;

public class MediaFileUtil {

	public static boolean deleteFile(ContentResolver contentResolver, String path) {
		boolean result = false;
		try {
			result = new MediaFile(contentResolver, new File(path)).delete();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static boolean deleteFile(ContentResolver contentResolver, File file) {
		boolean result = false;
		try {
			result = new MediaFile(contentResolver, file).delete();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
