package com.cqworld.util;

import java.math.BigDecimal;


public class NumberUtils {


    public static String formatDouble(double number) {
        if (Math.round(number) - number == 0) {
            return String.valueOf((long) number);
        }
        return String.valueOf(number);
    }

    public static String roundToString(double d) {
        return new BigDecimal(d).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
    }

    public static String roundToString(String s) {
        return new BigDecimal(s).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
    }


    public static String addSeparator(String str) {
        return addSeparatorp(roundToString(str));
    }

    public static String addSeparator(double d) {
        return addSeparatorp(roundToString(d));
    }

    private static String addSeparatorp(String str) {
        try {
            String reverseStr = new StringBuilder(str).reverse().toString();

            String left = "";
            if (reverseStr.contains(".")) {
                left = reverseStr.substring(0, reverseStr.indexOf(".") + 1);
                reverseStr = reverseStr.substring(reverseStr.indexOf(".") + 1, reverseStr.length());
            }

            String strTemp = "";
            for (int i = 0; i < reverseStr.length(); i++) {
                if (i * 3 + 3 > reverseStr.length()) {
                    strTemp += reverseStr.substring(i * 3, reverseStr.length());
                    break;
                }
                strTemp += reverseStr.substring(i * 3, i * 3 + 3) + ",";
            }
            if (strTemp.endsWith(",")) {
                strTemp = strTemp.substring(0, strTemp.length() - 1);
            }


            String resultStr = new StringBuilder(left + strTemp).reverse().toString();
            return resultStr;
        } catch (Exception e) {
            return str;
        }

    }
}
