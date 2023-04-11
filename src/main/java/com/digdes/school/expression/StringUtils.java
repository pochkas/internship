package com.digdes.school.expression;

public class StringUtils {
    public static String stripQuotes(String str) {
        char start = 8216;
        char end = 8217;
        char[] array = str.toCharArray();
        if ((str.startsWith("'") && str.endsWith("'")) || (array[0] == start && array[array.length - 1] == end)) {
            return str.substring(1, str.length() - 1);
        }
        return str;
    }
}
