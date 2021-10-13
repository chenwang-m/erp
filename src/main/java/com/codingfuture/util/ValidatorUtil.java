package com.codingfuture.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wangchen
 * @description:校验工具类
 */
public class ValidatorUtil {

    private static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    private static final String REGEX_PHONE = "^((13[0-9])|(14[5,7,9])|(15[0-3,5-9])|(166)|(17[3,5,6,7,8])|(18[0-9])|(19[8,9]))\\d{8}$";

    /**
     * 判断输入的内容是否是汉字
     *
     * @param name
     * @return boolean值
     */
    public static boolean checkNameIsChinese(String name) {
        String regExp = "[\u4e00-\u9fa5]+";
        if (name.matches(regExp)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 手机号验证
     *
     * @param str
     * @return 验证通过返回true
     */
    public static boolean isMobile(final String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile(REGEX_PHONE); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }

    /**
     * 电话号码验证
     *
     * @param str
     * @return 验证通过返回true
     */
    public static boolean isPhone(final String str) {
        Pattern p1 = null, p2 = null;
        Matcher m = null;
        boolean b = false;
        p1 = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$"); // 验证带区号的
        p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$");     // 验证没有区号的
        if (str.length() > 9) {
            m = p1.matcher(str);
            b = m.matches();
        } else {
            m = p2.matcher(str);
            b = m.matches();
        }
        return b;
    }


    //判断邮箱地址格式
    public static boolean isEmailAddress(String emailAddress) {
        Pattern p = Pattern.compile(REGEX_EMAIL);
        Matcher m = p.matcher(emailAddress);
        return m.matches();
    }

    public static boolean isTelNumber(String phoneNumber) {
        return isMobile(phoneNumber) || isPhone(phoneNumber);
    }
}