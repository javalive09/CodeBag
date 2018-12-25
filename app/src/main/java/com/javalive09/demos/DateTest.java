package com.javalive09.demos;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.javalive09.annotation.Run;
import com.javalive09.codebag.CodeActivity;

/**
 * Created by peter on 2018/11/10
 */
public class DateTest {

    @Run
    public void date2string(CodeActivity codeActivity) throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(1980, 4, 7);
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy/MM/dd");
        String formatted1 = format1.format(cal.getTime());

        SimpleDateFormat format2 = new SimpleDateFormat("yyyy年MM月dd日");
        String formatted2 = format2.format(cal.getTime());

        Date date = format1.parse(formatted1);
        cal.setTime(date);

        codeActivity.showText(formatted1 + "\n" +
                formatted2 + "\n" +
                (cal.get(Calendar.YEAR)) + "\n" +
                (cal.get(Calendar.MONTH)) +  "\n" +
                cal.get(Calendar.DAY_OF_MONTH));
    }




}
