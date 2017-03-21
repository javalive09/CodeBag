package com.javalive09.sample.utils;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.fastaccess.permission.base.PermissionHelper;
import com.fastaccess.permission.base.callback.OnPermissionCallback;
import com.javalive09.codebag.Entry;
import com.javalive09.codebag.DetailActivity;
import com.javalive09.sample.R;
import com.javalive09.sample.thirdlibs.wheel.OnWheelScrollListener;
import com.javalive09.sample.thirdlibs.wheel.WheelView;
import com.javalive09.sample.thirdlibs.wheel.adapters.NumericWheelAdapter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * Created by peter on 16/9/13.
 */
public class Utils extends Entry {

    WheelView wv1, wv2, wv3, wv4;
    TextView sp;
    float currentDesnity = 160;
    RadioButton px2dp;
    RadioGroup rg, switchType;

    /**
     * px dp 互转工具
     */
    public void px_dp() {
        View content = showView(R.layout.px_dp_layout, null);
        wv1 = (WheelView) content.findViewById(R.id.th);
        wv2 = (WheelView) content.findViewById(R.id.h);
        wv3 = (WheelView) content.findViewById(R.id.ten);
        wv4 = (WheelView) content.findViewById(R.id.one);
        OnWheelScrollListener listener = new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {
            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                setRealValue();
            }
        };
        initWheel(wv1, listener, content.getContext());
        initWheel(wv2, listener, content.getContext());
        initWheel(wv3, listener, content.getContext());
        initWheel(wv4, listener, content.getContext());

        switchType = (RadioGroup) content.findViewById(R.id.switch_type);
        switchType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                setRealValue();
            }
        });
        rg = (RadioGroup) content.findViewById(R.id.density);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.mdpi:
                        currentDesnity = 160;
                        break;
                    case R.id.hdpi:
                        currentDesnity = 240;
                        break;
                    case R.id.xhdpi:
                        currentDesnity = 320;
                        break;
                    case R.id.xxhdpi:
                        currentDesnity = 480;
                        break;
                    case R.id.xxxhdpi:
                        currentDesnity = 640;
                        break;
                }
                setRealValue();
            }
        });
        sp = (TextView) content.findViewById(R.id.result);
        px2dp = (RadioButton) content.findViewById(R.id.px_dp);
    }

    private int getValue() {
        if (wv1 != null) {
            int a = wv1.getCurrentItem() * 1000;
            int b = wv2.getCurrentItem() * 100;
            int c = wv3.getCurrentItem() * 10;
            int d = wv4.getCurrentItem() * 1;
            return a + b + c + d;
        }
        return 0;
    }

    private void initWheel(WheelView wheel, OnWheelScrollListener listener, Context context) {
        NumericWheelAdapter adapter = new NumericWheelAdapter(context, 0, 9);
        adapter.setTextSize(50);
        wheel.setViewAdapter(adapter);
        wheel.setCurrentItem(0);
        wheel.setCyclic(true);
        wheel.setInterpolator(new AnticipateOvershootInterpolator());
        wheel.addScrollingListener(listener);
    }

    private void setRealValue() {
        if (px2dp != null) {
            int value = getValue();
            if (px2dp.isChecked()) {
                int result = px2dp(currentDesnity, value);
                sp.setText(result + "dp");
            } else {
                int result = dip2px(currentDesnity, getValue());
                sp.setText(result + "px");
            }
        }
    }

    private int dip2px(float densityDip, float dipValue) {
        return (int) (dipValue * (densityDip / 160) + 0.5f);
    }

    private int px2dp(float densityDip, float pxValue) {
        return (int) ((pxValue * 160) / densityDip + 0.5f);
    }


    private final static String PHONE_STATE = Manifest.permission.READ_PHONE_STATE;
    PermissionHelper permissionHelper;
    DetailActivity.ActivityCallback callback;

    /**
     * 手机参数工具
     */
    public void phoneData() {

        permissionHelper = PermissionHelper.getInstance(getActivity(), new OnPermissionCallback() {
            @Override
            public void onPermissionGranted(@NonNull String[] permissionName) {
                realshow(callback);
            }

            @Override
            public void onPermissionDeclined(@NonNull String[] permissionName) {

            }

            @Override
            public void onPermissionPreGranted(@NonNull String permissionsName) {
                realshow(callback);
            }

            @Override
            public void onPermissionNeedExplanation(@NonNull String permissionName) {
                showTxt("need " + permissionName, new DetailActivity.ActivityCallback() {

                    @Override
                    public void onDestory() {
                        permissionHelper.openSettingsScreen();
                    }
                });
            }
            @Override
            public void onPermissionReallyDeclined(@NonNull String permissionName) {}
            @Override
            public void onNoPermissionNeeded() {
                realshow(callback);
            }

        });

        permissionHelper.setForceAccepting(true).request(PHONE_STATE);

        callback = new DetailActivity.ActivityCallback() {

            @Override
            public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
                permissionHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }

            @Override
            public void onActivityResult(int requestCode, int resultCode, Intent data) {
                permissionHelper.onActivityForResult(requestCode);
            }

        };
    }

    private void realshow(DetailActivity.ActivityCallback callback) {

        DisplayMetrics dm = getActivity().getResources().getDisplayMetrics();

        View view = showView(R.layout.utils_phonedata_layout, callback);
        String modeStr = String.format(getActivity().getString(R.string.util_phone_mode), android.os.Build.MODEL);
        ((TextView) view.findViewById(R.id.phone_mode)).

                setText(modeStr);

        String sdkStr = String.format(getActivity().getString(R.string.util_phone_sdk), android.os.Build.VERSION.SDK_INT);
        ((TextView) view.findViewById(R.id.sdk_name)).

                setText(sdkStr);

        String firmWareStr = String.format(getActivity().getString(R.string.util_phone_firmware), android.os.Build.VERSION.RELEASE);
        ((TextView) view.findViewById(R.id.firmware)).

                setText(firmWareStr);


        String resolution = dm.widthPixels + " x " + dm.heightPixels;
        String resolutionStr = String.format(getActivity().getString(R.string.util_phone_resolution), resolution);
        ((TextView) view.findViewById(R.id.resolution)).

                setText(resolutionStr);

        String densityStr = String.format(getActivity().getString(R.string.util_phone_density), dm.densityDpi);
        ((TextView) view.findViewById(R.id.density)).

                setText(densityStr);

        TelephonyManager tm = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        String imeiStr = String.format(getActivity().getString(R.string.util_phone_imei), tm.getDeviceId());
        ((TextView) view.findViewById(R.id.imei)).

                setText(imeiStr);

        String resDir = getActivity().getString(R.string.values_marks);
        String resDirStr = String.format(getActivity().getString(R.string.util_phone_res_dir), resDir);
        ((TextView) view.findViewById(R.id.res_dir)).

                setText(resDirStr);


        String statusBarHStr = String.format(getActivity().getString(R.string.util_phone_status_bar_h), getStatusBarHeight(getActivity()));
        ((TextView) view.findViewById(R.id.status_bar_h)).

                setText(statusBarHStr);

        String brandStr = String.format(getActivity().getString(R.string.util_phone_brand), android.os.Build.BRAND);
        ((TextView) view.findViewById(R.id.brand)).

                setText(brandStr);
    }

    private int getStatusBarHeight(Context context) {
        Class<?> c;
        Object obj;
        Field field;
        int x, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }


    WheelView wvAlpha1, wvAlpha2, wvRed1, wvRed2, wvGreen1, wvGreen2, wvBlue1, wvBlue2;
    View showView;

    /**
     * 颜色显示
     */
    public void color() {
        View view = showView(R.layout.utils_color, null);

        showView = view.findViewById(R.id.color);
        wvAlpha1 = (WheelView) view.findViewById(R.id.alpha1);
        wvAlpha2 = (WheelView) view.findViewById(R.id.alpha2);
        wvRed1 = (WheelView) view.findViewById(R.id.red1);
        wvRed2 = (WheelView) view.findViewById(R.id.red2);
        wvGreen1 = (WheelView) view.findViewById(R.id.green1);
        wvGreen2 = (WheelView) view.findViewById(R.id.green2);
        wvBlue1 = (WheelView) view.findViewById(R.id.blue1);
        wvBlue2 = (WheelView) view.findViewById(R.id.blue2);

        initColorWheel(wvAlpha1);
        initColorWheel(wvAlpha2);
        initColorWheel(wvRed1);
        initColorWheel(wvRed2);
        initColorWheel(wvGreen1);
        initColorWheel(wvGreen2);
        initColorWheel(wvBlue1);
        initColorWheel(wvBlue2);

        wvAlpha1.setCurrentItem(15);
        wvAlpha2.setCurrentItem(15);

    }

    private void initColorWheel(WheelView wheel) {
        NumericWheelAdapter adapter = new NumericWheelAdapter(getActivity(), 0, 15, "%x");
        adapter.setTextSize(45);
        wheel.setViewAdapter(adapter);
        wheel.setCurrentItem(0);
        wheel.setCyclic(true);
        wheel.setInterpolator(new AnticipateOvershootInterpolator());
        wheel.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {
            }

            @Override
            public void onScrollingFinished(WheelView wheel) {

                try {
                    String alpha1 = Integer.toHexString(wvAlpha1.getCurrentItem());
                    String alpha2 = Integer.toHexString(wvAlpha2.getCurrentItem());
                    String red1 = Integer.toHexString(wvRed1.getCurrentItem());
                    String red2 = Integer.toHexString(wvRed2.getCurrentItem());
                    String green1 = Integer.toHexString(wvGreen1.getCurrentItem());
                    String green2 = Integer.toHexString(wvGreen2.getCurrentItem());
                    String blue1 = Integer.toHexString(wvBlue1.getCurrentItem());
                    String blue2 = Integer.toHexString(wvBlue2.getCurrentItem());
                    int alpha = Integer.parseInt(alpha1 + alpha2, 16);
                    int red = Integer.parseInt(red1 + red2, 16);
                    int green = Integer.parseInt(green1 + green2, 16);
                    int blue = Integer.parseInt(blue1 + blue2, 16);
                    int color = Color.argb(alpha, red, green, blue);
                    showView.setBackgroundColor(color);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    WheelView alpha2, alpha1;
    TextView alphaValue;

    /**
     * alpha 百分比
     */
    public void alpha() {
        View view = showView(R.layout.utils_alpha_percent, null);
        alphaValue = (TextView) view.findViewById(R.id.alpha_value);
        alpha1 = (WheelView) view.findViewById(R.id.alpha1);
        alpha2 = (WheelView) view.findViewById(R.id.alpha2);
        initAlphWheel(alpha1);
        initAlphWheel(alpha2);
    }

    private void initAlphWheel(WheelView wheel) {
        NumericWheelAdapter adapter = new NumericWheelAdapter(getActivity(), 0, 9);
        adapter.setTextSize(45);
        wheel.setViewAdapter(adapter);
        wheel.setCurrentItem(0);
        wheel.setCyclic(true);
        wheel.setInterpolator(new AnticipateOvershootInterpolator());
        wheel.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {
            }

            @Override
            public void onScrollingFinished(WheelView wheel) {

                try {
                    String o = Integer.toHexString(alpha1.getCurrentItem());
                    String t = Integer.toHexString(alpha2.getCurrentItem());
                    int intContent_alpha1 = Integer.parseInt(o);
                    int intContent_alpha2 = Integer.parseInt(t);
                    int intContent = intContent_alpha1 * 10 + intContent_alpha2;
                    int realContent = (int) (intContent / 100f * 255);
                    String content = Integer.toHexString(realContent);
                    alphaValue.setText(content);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    ProgressBar pb = null;
    Button bt = null;
    AsyncTask<Void, Integer, String> mAsynTask = null;
    String TEST = "点击测试";
    String TESTING = "测试中";
    String PREFIX = "测试\n";
    String SUFFIX = "kb/s";
    String[] URLS = {"www.baidu.com", "www.youku.com", "www.qq.com"};
    boolean cancelTest = false;

    /**
     * 网速测试
     */
    public void networkspeed() {
        View view = showView(R.layout.utils_networkspeed, new DetailActivity.ActivityCallback() {
            @Override
            public void onDestory() {
                cancelTest();
            }
        });
        pb = (ProgressBar) view.findViewById(R.id.loading);
        bt = (Button) view.findViewById(R.id.start);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start();
            }
        });
        bt.setText(TEST);
    }

    private String testUrlSpeed(String urls[]) {
        Float sumSpeed = 0f;
        Float averageSpeed;
        Float speed = 0f;
        for (String url : urls) {
            ArrayList<String> commands = new ArrayList<>();
            commands.add("ping");
            commands.add("-c");
            commands.add("5");
            commands.add("-s");
            commands.add("1024");
            commands.add("-i");
            commands.add("2");
            commands.add(url);

            ProcessBuilder pb = new ProcessBuilder(commands);
            try {
                if (cancelTest) {
                    return "";
                }
                Process process = pb.start();
                InputStream in = process.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                String s;
                ArrayList<Float> times = new ArrayList<>();
                while ((s = br.readLine()) != null) {
                    System.out.println(s);
                    Float time = matcher(s);
                    if (time != null) {
                        times.add(time);
                    }
                    System.out.println(time);
                }
                Float AllTimes = 0f;
                for (Float time : times) {
                    AllTimes += time;
                }

                Float averageTime = AllTimes / times.size();

                speed = 1024 * 8 / averageTime * 1000 / 1024;

            } catch (IOException e) {
                e.printStackTrace();
            }
            sumSpeed += speed;
        }
        averageSpeed = sumSpeed / urls.length;

        return getSpeedString(averageSpeed);

    }

    private void start() {
        mAsynTask = new AsyncTask<Void, Integer, String>() {

            @Override
            protected String doInBackground(Void... params) {
                return testUrlSpeed(URLS);
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                pb.setVisibility(View.VISIBLE);
                bt.setText(TESTING);
                bt.setClickable(false);
            }

            @Override
            protected void onPostExecute(String speed) {
                super.onPostExecute(speed);
                pb.setVisibility(View.INVISIBLE);
                speed = PREFIX + speed + SUFFIX;
                bt.setText(speed);
                bt.setClickable(true);
            }

        }.execute();
    }

    private void cancelTest() {
        cancelTest = true;
        if (mAsynTask != null) {
            mAsynTask.cancel(true);
        }
    }

    private String getSpeedString(Float speed) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(2);
        return df.format(speed);
    }

    private Float matcher(String str) {
        String regx = "time=.*ms";
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            String mat = matcher.group();
            int start = mat.indexOf("=") + 1;
            int end = mat.indexOf(" ");
            String time = mat.substring(start, end);
            Float d = Float.valueOf(time);
            return d;
        }
        return null;
    }


}
