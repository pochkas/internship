package com.digdes.school.expression;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class StringUtils {
    private static final Set<String> supportedFieldNames = new HashSet<>(Arrays.asList("id", "lastname", "age", "cost", "active"));

    public static boolean validColumnName(String column) {
        return supportedFieldNames.contains(column.toLowerCase());
    }

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
