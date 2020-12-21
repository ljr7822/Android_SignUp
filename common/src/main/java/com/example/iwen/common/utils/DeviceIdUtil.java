package com.example.iwen.common.utils;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import java.security.MessageDigest;
import java.util.Locale;
import java.util.UUID;

/**
 * 获取唯一标识符号
 * 使用imei + android_id + serial + 硬件UUID（使用Build属性生成，如果硬件信息不变，则UUID值不变）
 * 我们先拼接好DeviceId数据，取其SHA1值，再转16进制即可（统一40位长度）
 *
 * @author : Iwen大大怪
 * create : 11-21 021 14:43
 */
public class DeviceIdUtil {
    /**
     * 获得设备硬件标识
     *
     * @param context 上下文
     * @return 设备硬件标识
     */
    public static String getDeviceId(Context context) {
        StringBuilder sbDeviceId = new StringBuilder();

        // 获得设备默认IMEI（>=6.0 需要ReadPhoneState权限）
        String imei = getIMEI(context);
        // 获得AndroidId（无需权限）
        String androidid = getAndroidId(context);
        // 获得设备序列号（无需权限）
        String serial = getSERIAL();
        // 获得硬件uuid（根据硬件相关属性，生成uuid）（无需权限）
        String uuid = getDeviceUUID().replace("-", "");

        // 追加imei
        if (imei != null && imei.length() > 0) {
            sbDeviceId.append(imei);
            sbDeviceId.append("|");
        }
        // 追加androidid
        if (androidid != null && androidid.length() > 0) {
            sbDeviceId.append(androidid);
            sbDeviceId.append("|");
        }
        // 追加serial
        if (serial != null && serial.length() > 0) {
            sbDeviceId.append(serial);
            sbDeviceId.append("|");
        }
        // 追加硬件uuid
        if (uuid != null && uuid.length() > 0) {
            sbDeviceId.append(uuid);
        }

        // 生成SHA1，统一DeviceId长度
        if (sbDeviceId.length() > 0) {
            try {
                // 取SHA1
                byte[] hash = getHashByString(sbDeviceId.toString());
                // 转16进制字符串
                String sha1 = bytesToHex(hash);
                if (sha1 != null && sha1.length() > 0) {
                    //返回最终的DeviceId
                    return sha1;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        // 如果以上硬件标识数据均无法获得，
        // 则DeviceId默认使用系统随机数，这样保证DeviceId不为空
        return UUID.randomUUID().toString().replace("-", "");
    }

    // 需要获得READ_PHONE_STATE权限，>=6.0，默认返回null
    private static String getIMEI(Context context) {
        try {
            TelephonyManager tm = (TelephonyManager)
                    context.getSystemService(Context.TELEPHONY_SERVICE);
            return tm.getDeviceId();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    /**
     * 获得设备的AndroidId
     *
     * @param context 上下文
     * @return 设备的AndroidId
     */
    private static String getAndroidId(Context context) {
        try {
            return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    /**
     * 获得设备序列号（如：WTK7N16923005607）, 个别设备无法获取
     *
     * @return 设备序列号
     */
    private static String getSERIAL() {
        try {
            return Build.SERIAL;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    /**
     * 获得设备硬件uuid
     * 使用硬件信息，计算出一个随机数
     *
     * @return 设备硬件uuid
     */
    private static String getDeviceUUID() {
        try {
            String dev = "3883756" +
                    Build.BOARD.length() % 10 +
                    Build.BRAND.length() % 10 +
                    Build.DEVICE.length() % 10 +
                    Build.HARDWARE.length() % 10 +
                    Build.ID.length() % 10 +
                    Build.MODEL.length() % 10 +
                    Build.PRODUCT.length() % 10 +
                    Build.SERIAL.length() % 10;
            return new UUID(dev.hashCode(),
                    Build.SERIAL.hashCode()).toString();
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    /**
     * 取SHA1
     *
     * @param data 数据
     * @return 对应的hash值
     */
    private static byte[] getHashByString(String data) {
        try {
            // MessageDigest此类为应用程序提供消息摘要算法的功能，例如SHA-1或SHA-256。
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            // 重置摘要以备将来使用。
            messageDigest.reset();
            // 使用指定的字节数组更新摘要。
            messageDigest.update(data.getBytes("UTF-8"));
            // 通过执行诸如填充之类的最终操作来完成哈希计算。进行此调用后，摘要将重置。
            return messageDigest.digest();
        } catch (Exception e) {
            return "".getBytes();
        }
    }

    /**
     * 转16进制字符串
     *
     * @param data 数据
     * @return 16进制字符串
     */
    private static String bytesToHex(byte[] data) {
        StringBuilder sb = new StringBuilder();
        String stmp;
        for (int n = 0; n < data.length; n++) {
            stmp = (Integer.toHexString(data[n] & 0xFF));
            if (stmp.length() == 1)
                sb.append("0");
            sb.append(stmp);
        }
        return sb.toString().toUpperCase(Locale.CHINA);
    }
}