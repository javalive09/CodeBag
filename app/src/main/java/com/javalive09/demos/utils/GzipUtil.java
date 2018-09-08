package com.javalive09.demos.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import android.util.Base64;

public class GzipUtil {
    /**
     * @param str
     * @return 压缩
     */
    public static String compress(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        ByteArrayOutputStream out = null;
        GZIPOutputStream gzip = null;
        String compress = "";
        try {
            out = new ByteArrayOutputStream();
            gzip = new GZIPOutputStream(out);
            gzip.write(str.getBytes());
            gzip.close();
            // 这里增加base64编码
            byte[] compressed = out.toByteArray();
            compress = new String(Base64.encode(compressed, Base64.DEFAULT));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return compress;
    }
 
    /**
     * @param str
     * @return 解压缩
     */
    public static String uncompress(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        ByteArrayOutputStream out = null;
        ByteArrayInputStream in = null;
        GZIPInputStream gzip = null;
        String uncompress = "";
        try {
            out = new ByteArrayOutputStream();
            // 这里增加base64解码
            byte[] compressed = Base64.decode(str.getBytes(), Base64.DEFAULT);
            in = new ByteArrayInputStream(compressed);
            gzip = new GZIPInputStream(in);
            byte[] buffer = new byte[1024];
            int offset = -1;
            while ((offset = gzip.read(buffer)) != -1) {
                out.write(buffer, 0, offset);
            }
            uncompress = out.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != gzip) {
                try {
                    gzip.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return uncompress;
    }

}