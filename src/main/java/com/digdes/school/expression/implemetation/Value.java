package com.digdes.school.expression.implemetation;

import com.digdes.school.exception.CommandException;

import java.util.Objects;

public class Value {
    protected String value;
    protected Object object;

    public Value(String valueStr) {
        value = valueStr;
        char start = 8216;
        char end = 8217;
        char[] array = valueStr.toCharArray();
        try {
            if (valueStr.equalsIgnoreCase("null")) {
                object = null;
            } else if ((valueStr.startsWith("'") && valueStr.endsWith("'")) || (array[0] == start && array[array.length - 1] == end)) {
                object = valueStr.substring(1, valueStr.length() - 1);
            } else if (valueStr.contains(".")) {
                object = Double.parseDouble(valueStr);
            } else if (valueStr.equalsIgnoreCase("true") || valueStr.equalsIgnoreCase("false")) {
                object = Boolean.parseBoolean(valueStr);
            } else {
                object = Long.parseLong(valueStr);
            }
        } catch (Exception e) {
            throw new CommandException(valueStr + " could not be parsed");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Value value1 = (Value) o;
        return Objects.equals(value, value1.value) && Objects.equals(object, value1.object);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, object);
    }
}
