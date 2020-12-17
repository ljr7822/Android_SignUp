package com.example.iwen.common.utils;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * 对文件或者字符串进行Hash算法，返回MD5值
 *
 * @author : Iwen大大怪
 * create : 2020/11/15 2:37
 */
public class HashUtil {
    private static final char HEX_DIGITS[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f'};

    private static String convertToHexString(byte[] b) {
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (byte a : b) {
            sb.append(HEX_DIGITS[(a & 0xf0) >>> 4]);
            sb.append(HEX_DIGITS[a & 0x0f]);
        }
        return sb.toString();
    }

    /**
     * Get a String's HashCode
     *
     * @param str String
     * @return HashCode
     */
    public static String getMD5String(String str) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        md5.update(str.getBytes());
        return convertToHexString(md5.digest());
    }

    /**
     * Get a File's HashCode
     *
     * @param file File
     * @return HashCode
     */
    public static String getMD5String(File file) {
        // Create md5
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        // Stream
        InputStream in = null;
        byte[] buffer = new byte[1024];
        int numRead;
        // Read
        try {
            in = new FileInputStream(file);
            while ((numRead = in.read(buffer)) > 0) {
                md5.update(buffer, 0, numRead);
            }
            return convertToHexString(md5.digest());
        } catch (Exception e) {
            return null;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 对一个字符串进行Base64编码
     *
     * @param str 原始字符串
     * @return 进行Base64编码后的字符串
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String encodeBase64(String str) {
        return Base64
                .getEncoder()
                .encodeToString(str.getBytes());
    }
}
