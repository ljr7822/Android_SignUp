package com.example.iwen.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 *
 * @author : Iwen大大怪
 * create : 2020/11/15 2:41
 */
public class DateTimeUtil {

    private String time;

    /**
     * 得到系统当前格式为yyyy-MM-dd HH:mm:ss 的时间
     * 例如 2011-11-11 11:11:11
     */
    public String getTime() {
        java.text.SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        this.time = sm.format(date);

        return time;
    }



    public String getTimeymdhms(Date date) {
        java.text.SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.time = sm.format(date);
        return time;
    }

    /**
     * 得到格式为yyyy-MM-dd的日期
     *
     * @param date
     * @return
     */
    public String getTimeymd(Date date) {
        java.text.SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
        this.time = sm.format(date);
        return time;
    }
    public long getDateLong(String date) throws ParseException {
        java.text.SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
        return sm.parse(date).getTime();
    }
    /**
     * 得到格式为yyyy-MM-dd的日期
     *
     * @return
     */
    public String getTimeymd() {
        java.text.SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        this.time = sm.format(date);
        return time;
    }

    /**
     * 得到YYYY年MM月DD日
     *
     * @return
     */
    public String getTimeymdzw() {
        java.text.SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String times = sm.format(date);

        String yyyy = times.substring(0, times.indexOf("-")) + "年";
        String mm = times.substring(5, times.lastIndexOf("-")) + "月";
        String dd = times.substring(times.lastIndexOf("-") + 1, times.length()) + "日";

        String stime = yyyy + mm + dd;
        return stime;
    }

    /**
     * 得到格式为yyyy的日期
     *
     * @return
     */
    public String getTimeyyyy() {
        java.text.SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String times = sm.format(date);
        String yyyy = times.substring(0, 4);
        this.time = yyyy;
        return time;
    }

    /**
     * 得到格式为MM的日期
     *
     * @return
     */
    public String getTimemm() {
        java.text.SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String times = sm.format(date);
        String mm = times.substring(5, 7);
        this.time = mm;
        return time;
    }

    /**
     * 得到格式为DD的日期
     *
     * @return
     */
    public String getTimedd() {
        java.text.SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String times = sm.format(date);
        String dd = times.substring(8, 10);
        this.time = dd;
        return time;
    }

    /**
     * 得到String型的无符号年月日时分秒的日期。例如：200808081818
     *
     * @return
     */
    public String getymdhm() {
        java.text.SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String times = sm.format(date);
        String yyyy = times.substring(0, 4);
        String mm = times.substring(5, 7);
        String dd = times.substring(8, 10);

        String h = times.substring(11, 13);
        String m = times.substring(14, 16);
        this.time = yyyy + mm + dd + h + m;
        return time;
    }

    /**
     * 得到String型的无符号年月日时分秒的日期。例如：20080808181818
     *
     * @return
     */
    public String getymdhms() {
        java.text.SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String times = sm.format(date);
        String yyyy = times.substring(0, 4);
        String mm = times.substring(5, 7);
        String dd = times.substring(8, 10);

        String h = times.substring(11, 13);
        String m = times.substring(14, 16);
        String s = times.substring(17, 19);
        this.time = yyyy + mm + dd + h + m + s;
        return time;
    }

    /**
     * 把yyyy-MM-dd hh:mm:ss格式的日期参数封装转换成整形返回。例如：2008-08-08 10:10:10封装转换成20080808101010
     *
     * @return
     */
    public String getymdhms(String times) {

        String yyyy = times.substring(0, times.indexOf("-"));
        String mm = times.substring(times.indexOf("-") + 1, times.lastIndexOf("-"));
        String dd = times.substring(times.lastIndexOf("-") + 1, times.indexOf(" "));

        String h = times.substring(times.indexOf(" ") + 1, times.indexOf(":"));
        String m = times.substring(times.indexOf(":") + 1, times.lastIndexOf(":"));
        String s = times.substring(times.lastIndexOf(":") + 1, times.length());

        this.time = yyyy + mm + dd + h + m + s;

        return time;
    }

    /**
     * 把yyyy-MM-dd hh:mm:ss格式的日期参数封装转换成整形返回。例如：2008-08-08 10:10:10封装转换成2008年08月08日10:10
     *
     * @return
     */
    public String getymdhm(String times) {

        String yyyy = times.substring(0, times.indexOf("-"));
        String mm = times.substring(times.indexOf("-") + 1, times.lastIndexOf("-"));
        String dd = times.substring(times.lastIndexOf("-") + 1, times.indexOf(" "));

        String h = times.substring(times.indexOf(" ") + 1, times.indexOf(":"));
        String m = times.substring(times.indexOf(":") + 1, times.lastIndexOf(":"));

        this.time = yyyy + "年" + mm + "月" + dd + "日" + h + ":" + m;

        return time;
    }

    /**
     * 得到String型的无符号年月日日期。例如：20080808
     *
     * @return
     */
    public String getymd() {
        java.text.SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String times = sm.format(date);
        String yyyy = times.substring(0, 4);
        String mm = times.substring(5, 7);
        String dd = times.substring(8, 10);
        this.time = yyyy + mm + dd;
        return time;
    }

    /**
     * 把yyyy-MM-dd格式的日期参数封装转换成整形返回。例如：2008-08-08转换成20080808
     *
     * @param ymd
     * @return
     */
    public String getymd(String ymd) {

        String times = ymd;
        String yyyy = times.substring(0, 4);
        String mm = times.substring(5, 7);
        String dd = times.substring(8, 10);
        this.time = yyyy + mm + dd;
        return time;
    }
}
