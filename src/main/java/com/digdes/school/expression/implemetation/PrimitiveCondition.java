package com.digdes.school.expression.implemetation;

import com.digdes.school.Command;
import com.digdes.school.expression.Condition;
import com.digdes.school.expression.StringUtils;

import java.util.Map;
import java.util.Objects;

public class PrimitiveCondition implements Condition {

    protected String columnName;

    protected Command command;

    protected Value value;

    public PrimitiveCondition() {
    }

    public PrimitiveCondition(String columnName, Command command, Value value) {
        this.columnName = StringUtils.stripQuotes(columnName);
        this.command = command;
        this.value = value;
    }

    public boolean matches(Map<String, Object> row) {
        Object rowV = row.get(columnName);
        if (command.equals(Command.EQUALS)) {
            return  Objects.equals(rowV, value.object);
        } else if (command.equals(Command.NON_EQUALS)) {
            return !Objects.equals(rowV, value.object);
        } else if (command.equals(Command.like)) {
            return commandForLike(rowV);
        } else if (command.equals(Command.ilike)) {
            return commandForIlike(rowV);
        } else if (command.equals(Command.GREATER)) {
            if (rowV instanceof Long) {
                long valueLong = Long.parseLong(value.value);
                return (Long) rowV > valueLong;
            } else if (rowV instanceof Double) {
                double valueDouble = Double.parseDouble(value.value);
                return (Double) rowV > valueDouble;
            } else if (rowV instanceof String) {
                String valueStr = value.value;
                String a = (String) rowV;
                int i = a.compareTo(valueStr);
                if (i > 0) {
                    return true;
                } else {
                    return false;
                }
            }
        } else if (command.equals(Command.SMALLER)) {
            if (rowV instanceof Long) {
                long valueLong = Long.parseLong(value.value);
                return (Long) rowV < valueLong;
            } else if (rowV instanceof Double) {
                double valueLong = Double.parseDouble(value.value);
                return (Double) rowV < valueLong;
            } else if (rowV instanceof String) {
                String valueStr = value.value;
                int i = ((String) rowV).compareTo(valueStr);
                return i < 0;
            }
        } else if (command.equals(Command.GREATER_OR_EQUALS)) {
            if (rowV instanceof Long) {
                Long valueLong = Long.parseLong(value.value);
                return (Long) rowV >= valueLong;
            } else if (rowV instanceof Double) {
                Double valueLong = Double.parseDouble(value.value);
                return (Double) rowV >= valueLong;
            } else if (rowV instanceof String) {
                String valueStr = value.value;
                int i = ((String) rowV).compareTo(valueStr);
                if(i >= 0) {
                    return true;
                }
            }
        } else if (command.equals(Command.SMALLER_OR_EQUALS)) {
            if (rowV instanceof Long) {
                Long valueLong = Long.parseLong(value.value);
                return (Long) rowV <= valueLong;
            } else if (rowV instanceof Double) {
                Double valueLong = Double.parseDouble(value.value);
                return (Double) rowV <= valueLong;
            } else if (rowV instanceof String) {
                String valueStr = value.value;
                int i = ((String) rowV).compareTo(valueStr);
                if (i <= 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean commandForLike(Object rowV) {
        String rowVStr = (String) rowV;
        String valueStr = (String) value.object;
        if (!valueStr.contains("%")) {
            return rowVStr.equals(valueStr);
        } else if (valueStr.startsWith("%") && valueStr.endsWith("%")) {
            String newStr = valueStr.substring(1, valueStr.length() - 1);
            return rowVStr.contains(newStr);
        } else if (valueStr.startsWith("%")) {
            String newStr = valueStr.substring(1);
            return rowVStr.startsWith(newStr);
        } else if (valueStr.endsWith("%")) {
            String newStr = valueStr.substring(0, valueStr.length() - 1);
            return rowVStr.endsWith(newStr);
        } else {
            return false;
        }
    }

    public boolean commandForIlike(Object rowV) {
        String rowVStr = (String) rowV;
        String valueStr = (String) value.object;
        if (!valueStr.contains("%")) {
            return rowVStr.equalsIgnoreCase(valueStr);
        } else if (valueStr.startsWith("%") && valueStr.endsWith("%")) {
            String newStr = valueStr.substring(1, valueStr.length() - 1);
            return (rowVStr.toLowerCase()).contains(newStr.toLowerCase());
        } else if (valueStr.startsWith("%")) {
            String newStr = valueStr.substring(1);
            return (rowVStr.toLowerCase()).startsWith(newStr.toLowerCase());
        } else if (valueStr.endsWith("%")) {
            String newStr = valueStr.substring(0, valueStr.length() - 1);
            return (rowVStr.toLowerCase()).endsWith(newStr.toLowerCase());
        } else {
            return false;
        }
    }


    @Override
    public String toString() {
        return "PrimitiveCondition{" +
                "columnName='" + columnName + '\'' +
                ", command=" + command +
                ", value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrimitiveCondition that = (PrimitiveCondition) o;
        return Objects.equals(columnName, that.columnName) && command == that.command && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(columnName, command, value);
    }
}
