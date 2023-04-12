package com.digdes.school.expression.implemetation;

import com.digdes.school.Command;
import com.digdes.school.exception.CommandException;
import com.digdes.school.exception.ConditionException;
import com.digdes.school.expression.Condition;
import com.digdes.school.expression.StringUtils;

import java.util.*;

// Conditions without AND/OR
public class PrimitiveCondition implements Condition {
    protected String columnName;
    protected Command command;
    protected Value value;

    public PrimitiveCondition(String columnName, Command command, Value value) {
        this.columnName = StringUtils.stripQuotes(columnName);
        this.command = command;
        this.value = value;
    }
    public boolean matches(Map<String, Object> row) {
        Object rowV = row.get(columnName);
        if (!StringUtils.validColumnName(columnName)){
            throw new CommandException(columnName+" is not a valid field name.");
        }
        if (command.equals(Command.EQUALS)) {
            return  Objects.equals(rowV, value.object);
        } else if (command.equals(Command.NON_EQUALS)) {
            return !Objects.equals(rowV, value.object);
        } else if (command.equals(Command.like)) {
            if(rowV==null){
                return false;
            } else if(!(rowV instanceof String)){
                throw new ConditionException("Wrong type for command like. Expected String.");
            }
            return commandForLike(rowV);
        } else if (command.equals(Command.ilike)) {
            if(rowV==null){
                return false;
            } else if(!(rowV instanceof String)){
                throw new ConditionException("Wrong type for command ilike. Expected String.");
            }
            return commandForIlike(rowV);
        } else if (command.equals(Command.GREATER)) {
            if (rowV instanceof Long) {
                long valueLong = Long.parseLong(value.value);
                return (Long) rowV > valueLong;
            } else if (rowV instanceof Double) {
                double valueDouble = Double.parseDouble(value.value);
                return (Double) rowV > valueDouble;
            } else if(rowV!=null) {
                throw new ConditionException("Wrong type for this command GREATER. Expected Long or Double.");
            }
        } else if (command.equals(Command.SMALLER)) {
            if (rowV instanceof Long) {
                long valueLong = Long.parseLong(value.value);
                return (Long) rowV < valueLong;
            } else if (rowV instanceof Double) {
                double valueLong = Double.parseDouble(value.value);
                return (Double) rowV < valueLong;
            } else if(rowV!=null) {
                throw new ConditionException("Wrong type for this command SMALLER. Expected Long or Double.");
            }
        } else if (command.equals(Command.GREATER_OR_EQUALS)) {
            if (rowV instanceof Long) {
                long valueLong = Long.parseLong(value.value);
                return (Long) rowV >= valueLong;
            } else if (rowV instanceof Double) {
                double valueLong = Double.parseDouble(value.value);
                return (Double) rowV >= valueLong;
            } else if(rowV!=null) {
                throw new ConditionException("Wrong type for this command GREATER_OR_EQUALS. Expected Long or Double.");
            }
        } else if (command.equals(Command.SMALLER_OR_EQUALS)) {
            if (rowV instanceof Long) {
                long valueLong = Long.parseLong(value.value);
                return (Long) rowV <= valueLong;
            } else if (rowV instanceof Double) {
                double valueLong = Double.parseDouble(value.value);
                return (Double) rowV <= valueLong;
            } else if(rowV!=null) {
                throw new ConditionException("Wrong type for this command SMALLER_OR_EQUALS. Expected Long or Double.");
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
