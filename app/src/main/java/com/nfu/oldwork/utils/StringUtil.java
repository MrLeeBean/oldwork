package com.nfu.oldwork.utils;

/**
 * Created by Administrator on 2017/8/15.
 */



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ly on 17/8/15.
 */
public class StringUtil {

    private static Logger logger = Logger.getLogger("StringUtil");

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static boolean isNotEmpty(Object object) {
        if (object == null)
            return false;

        if (object instanceof String) {
            String s = object.toString();
            if (StringUtil.isBlank(s)) {
                return false;
            }
        } else if (object instanceof Integer) {
            if (StringUtil.isBlank(((Integer) object).intValue())) {
                return false;
            }
        } else if (object instanceof Long) {
            if (StringUtil.isBlank(((Long) object).longValue())) {
                return false;
            }
        } else if (object instanceof Short) {
            if (StringUtil.isBlank(((Short) object).shortValue())) {
                return false;
            }
        } else {
            //更多判断
        }
        return true;
    }

    public static boolean isNotEmptyInZero(Object object) {
        if (object == null)
            return false;

        if (object instanceof String) {
            String s = object.toString();
            if (StringUtil.isBlank(s)) {
                return false;
            }
        } else if (object instanceof Integer) {
            if (StringUtil.isBlankForInt(((Integer) object).intValue())) {
                return false;
            }
        } else if (object instanceof Long) {
            if (StringUtil.isBlankForLong(((Long) object).longValue())) {
                return false;
            }
        } else if (object instanceof Short) {
            if (StringUtil.isBlankForShort(((Short) object).shortValue())) {
                return false;
            }
        } else if (object instanceof Double) {
            if (StringUtil.isBlankForDouble(((Double) object).doubleValue())) {
                return false;
            }
            //更多判断
        }
        return true;
    }

    /**
     * @param intValue :Integer
     * @return boolean
     * 判断价格
     */
    public static boolean isBlankForInt(Integer intValue) {

        if (null == intValue || 0 > intValue) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isBlankForLong(Long longValue) {
        if (longValue == null || longValue < 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isBlankForShort(Short shortValue) {
        if (shortValue == null || shortValue < 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isBlankForDouble(Double doubleValue) {

        if (null == doubleValue || 0.00 > doubleValue) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isEmpty(Object object) {
        if (null == object) {
            return true;
        }
        return !isNotEmpty(object);
    }

    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if (null != cs && (strLen = cs.length()) != 0) {
            for (int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }
            return true;
        } else {
            return true;
        }
    }

    public static boolean isBlank(Long longValue) {
        if (longValue == null || longValue == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isBlank(Short shortValue) {
        if (shortValue == null || shortValue == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param intValue :Integer
     * @return boolean
     * 判断id是否为空
     */
    public static boolean isBlank(Integer intValue) {

        if (null == intValue || 0 == intValue) {
            return true;
        } else {
            return false;
        }
    }


    public static boolean isBlank(Date date) {
        if (date == null) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNotBlank(CharSequence cs) {
        return !isBlank(cs);
    }

    public static String formatTime(Date date) {
        return format.format(date);
    }

    public static boolean isMobile(String mobile) {
        if (isEmpty(mobile)) {
            return false;
        }
        //String regex = "^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$";
        // 修改手机号校验规则，改为校验 以1开头，11位数字
        String regex = "^(1[0-9])\\d{9}$";
        return match(regex, mobile);
    }

    public static boolean isEmail(String email) {
        String regex = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";
        return match(regex, email);
    }

    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * 验证数字输入
     *
     * @param str 待验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isNumber(String str) {
        String regex = "^[0-9]*$";
        return match(regex, str);
    }

    /**
     * 验证非零的正整数
     *
     * @param str 待验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isIntNumber(String str) {
        String regex = "^\\+?[1-9][0-9]*$";
        return match(regex, str);
    }

    public static boolean isPwdValid(String password) {
        return isPwdPatternValid(password) && isPwdLengthValid(password);
    }

    /**
     * 验证输入密码条件(字符与数据同时出现)
     *
     * @param str 待验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isPwdPatternValid(String str) {
        String regex = "[A-Za-z0-9]*";
        return match(regex, str);
    }

    /**
     * 验证输入密码长度 (6-18位)
     *
     * @param str 待验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isPwdLengthValid(String str) {
        String regex = "^\\d{6,18}$";
        return match(regex, str);
    }

    /**
     * 验证验证输入字母
     *
     * @param str 待验证的字符串
     * @return 如果是符合格式的字符串, 返回 <b>true </b>,否则为 <b>false </b>
     */
    public static boolean isLetter(String str) {
        String regex = "^[A-Za-z]+$";
        return match(regex, str);
    }

    /**
     * @param regex 正则表达式字符串
     * @param str   要匹配的字符串
     * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
     */
    private static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public static String MD5(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String UUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String generateRandom() {
        int random = new Random().nextInt(900000) + 100000;
        return String.valueOf(random);
    }



}