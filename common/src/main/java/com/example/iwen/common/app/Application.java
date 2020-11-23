package com.example.iwen.common.app;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.annotation.StringRes;

import com.example.iwen.common.R;
import com.example.iwen.common.widget.QQMsgPopup;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.enums.PopupAnimation;
import com.lxj.xpopup.interfaces.OnConfirmListener;
import com.lxj.xpopup.interfaces.OnInputConfirmListener;
import com.lxj.xpopup.interfaces.OnSelectListener;
import com.lxj.xpopupext.listener.CityPickerListener;
import com.lxj.xpopupext.listener.TimePickerListener;
import com.lxj.xpopupext.popup.CityPickerPopup;
import com.lxj.xpopupext.popup.TimePickerPopup;

import net.qiujuer.genius.kit.handler.Run;

import java.io.File;
import java.net.NetworkInterface;
import java.util.Date;
import java.util.Enumeration;
import java.util.UUID;

import es.dmoral.toasty.Toasty;

/**
 * author : Iwen大大怪
 * create : 2020/11/15 9:22
 */
public class Application extends android.app.Application {
    private static Application instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    /**
     * 外部获取单例
     *
     * @return Application
     */
    public static Application getInstance() {
        return instance;
    }

    /**
     * 获取缓存文件夹地址
     *
     * @return 当前App的缓存文件夹地址
     */
    public static File getCacheDirFile() {
        return instance.getCacheDir();
    }

    /**
     * 获取头像临时缓存地址
     *
     * @return 头像临时缓存地址
     */
    public static File getAvatarTempFile() {
        //得到头像目录的缓存地址
        File dir = new File(getCacheDirFile(), "avatar");
        //创建所有的对应的文件夹
        dir.mkdirs();
        //删除旧的缓存文件
        File[] files = dir.listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                file.delete();
            }
        }
        //返回一个当前时间戳的目录文件地址
        File path = new File(dir, System.currentTimeMillis() + "jpg");
        return path.getAbsoluteFile();
    }

    /**
     * 显示一个Toast
     *
     * @param msg 字符串
     */
    public static void showToast(final String msg) {
        // Toast 只能在主线程中显示，所有需要进行线程转换，
        // 保证一定是在主线程进行的show操作
        Run.onUiAsync(() -> {
            // 这里进行回调的时候一定就是主线程状态了
            Toasty.info(instance, msg, Toast.LENGTH_SHORT).show();
        });

    }

    /**
     * 显示一个Toast
     *
     * @param msgId 传递的是字符串的资源
     */
    public static void showToast(@StringRes int msgId) {
        showToast(instance.getString(msgId));
    }

    /**
     * 显示中间弹出的列表弹窗
     *
     * @param context 上下文
     * @param strings 选择列表
     */
    public static void showXPopupSelectList(Context context, String[] strings) {
        new XPopup.Builder(context)
                //.maxWidth(600)
                // new String[]{"条目1", "条目2", "条目3", "条目4"}
                .asCenterList("请选择一项", strings, new OnSelectListener() {
                    @Override
                    public void onSelect(int position, String text) {
                        // TODO 执行选择操作
                        Toast.makeText(instance, "选择了" + text, Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    /**
     * 显示从底部弹出的列表弹窗
     *
     * @param context 上下文
     * @param strings 选择列表
     */
    public static void showXPopupSelectBottomList(Context context, String[] strings) {
        // 这种弹窗从 1.0.0版本开始实现了优雅的手势交互和智能嵌套滚动
        new XPopup.Builder(context)
                // new String[]{"条目1", "条目2", "条目3", "条目4", "条目5"}
                .asBottomList("请选择一项", strings,
                        new OnSelectListener() {
                            @Override
                            public void onSelect(int position, String text) {
                                // TODO 执行选择操作
                                Toast.makeText(instance, "选择了" + text, Toast.LENGTH_SHORT).show();
                            }
                        })
                .show();
    }

    /**
     * 显示带输入框的确认和取消对话框
     *
     * @param context 上下文
     * @param title   弹窗标题
     * @param content 弹窗内容
     */
    public static void showXPopupInputSelect(Context context, String title, String content) {
        new XPopup.Builder(context)
                .hasBlurBg(true)
                .asInputConfirm(title, content,
                new OnInputConfirmListener() {
                    @Override
                    public void onConfirm(String text) {
                        // TODO 执行选择操作
                        Toast.makeText(instance, "输入了：" + text, Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    /**
     * 显示确认和取消对话框
     *
     * @param context 上下文
     * @param title   标题
     * @param content 内容
     */
    public static void showXPopupSelect(Context context, String title, String content) {
        new XPopup.Builder(context)
                .hasBlurBg(true)
                .asConfirm(title, content,
                new OnConfirmListener() {
                    @Override
                    public void onConfirm() {
                        // TODO 执行选择操作
                        Toast.makeText(instance, "输入了：", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    /**
     * 时间选择器TimerPickerPopup弹窗
     *
     * @param activity activity
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void showTimePickerPopup(Activity activity) {
        Calendar date = Calendar.getInstance();
        date.set(2000, 5, 1);
        Calendar date2 = Calendar.getInstance();
        date2.set(2020, 5, 1);
        TimePickerPopup popup = new TimePickerPopup(activity)
//                        .setDefaultDate(date)  //设置默认选中日期
//                        .setYearRange(1990, 1999) //设置年份范围
//                        .setDateRang(date, date2) //设置日期范围
                .setTimePickerListener(new TimePickerListener() {
                    @Override
                    public void onTimeChanged(Date date) {
                        //时间改变
                    }

                    @Override
                    public void onTimeConfirm(Date date, View view) {
                        // 点击确认时间
                        // TODO 执行选择操作
                        Toast.makeText(instance, "选择的时间：" + date.toLocaleString(), Toast.LENGTH_SHORT).show();
                    }
                });

        new XPopup.Builder(activity)
                .asCustom(popup)
                .show();
    }

    /**
     * 城市选择器
     *
     * @param activity Activity
     */
    public static void showCityPickerPopup(Activity activity) {
        CityPickerPopup popup = new CityPickerPopup(activity);
        popup.setCityPickerListener(new CityPickerListener() {
            @Override
            public void onCityConfirm(String province, String city, String area, View v) {
                Log.e("tag", province + " - " + city + " - " + area);
                Toast.makeText(instance, province + " - " + city + " - " + area, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCityChange(String province, String city, String area) {
                Log.e("tag", province + " - " + city + " - " + area);
                Toast.makeText(instance, province + " - " + city + " - " + area, Toast.LENGTH_SHORT).show();
            }
        });
        new XPopup.Builder(activity)
                .asCustom(popup)
                .show();
    }

    /**
     * 自定义Position弹窗
     *
     * @param context 上下文
     */
    public static void showPosition(Context context) {
        new XPopup.Builder(context)
                .popupAnimation(PopupAnimation.ScaleAlphaFromCenter)
                .isCenterHorizontal(true)
                .offsetY(200)
                .hasBlurBg(true)
                .asCustom(new QQMsgPopup(context))
                .show();
    }
}

