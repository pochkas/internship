package com.digdes.school.expression.implemetation;

import java.util.Objects;

public class Value {

    protected String value;
    protected Object object;

    public Value(String valueStr) {
        if (valueStr.startsWith("'") && valueStr.endsWith("'")) {
            value = valueStr;
            object=valueStr;
        } else if (valueStr.contains(".")) {
            value = valueStr;
            object =  Double.parseDouble(valueStr);
        } else if (valueStr.equalsIgnoreCase("true") || valueStr.equalsIgnoreCase("false")) {
            value = valueStr;
            object = Boolean.parseBoolean(valueStr);
        } else {
            value = valueStr;
            object = Long.parseLong(valueStr);
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

    @Override
    public String toString() {
        return value;
    }
}
