package com.digdes.school.expression.implemetation;

import com.digdes.school.Command;
import com.digdes.school.expression.Condition;

import java.util.Map;
import java.util.Objects;

public class PrimitiveCondition implements Condition {

    protected String columnName;

    protected Command command;

    protected Value value;

    public PrimitiveCondition(){}

    public PrimitiveCondition(String columnName, Command command, Value value){
        this.columnName=columnName;
        this.command=command;
        this.value=value;
    }

    public boolean matches(Map<String, Object> row) {
        if (command.equals(Command.EQUALS)) {
            Object rowV = row.get(columnName);
            return rowV.equals(value.value);
        } else if (command.equals(Command.NON_EQUALS)) {
            Object rowV = row.get(columnName);
            return !(rowV.equals(value.value));
        } else if (command.equals(Command.like)) {
            Object rowV = row.get(columnName);
            return rowV.equals(value.value);
        } else if (command.equals(Command.ilike)) {
            Object rowV = row.get(columnName);
            return rowV.equals(value.value);
        } else if (command.equals(Command.GREATER)) {
            Object rowV = row.get(columnName);
            if (rowV instanceof Long) {
                Long valueLong = Long.parseLong(value.value);
                return (Long) rowV > valueLong;
            } else if (rowV instanceof Double) {
                Double valueLong = Double.parseDouble(value.value);
                return (Double) rowV > valueLong;
            }
        } else if (command.equals(Command.SMALLER)) {
            Object rowV = row.get(columnName);
            if (rowV instanceof Long) {
                Long valueLong = Long.parseLong(value.value);
                return (Long) rowV < valueLong;
            } else if (rowV instanceof Double) {
                Double valueLong = Double.parseDouble(value.value);
                return (Double) rowV < valueLong;
            }
        } else if (command.equals(Command.GREATER_OR_EQUALS)) {
            Object rowV = row.get(columnName);
            if (rowV instanceof Long) {
                Long valueLong = Long.parseLong(value.value);
                return (Long) rowV >= valueLong;
            } else if (rowV instanceof Double) {
                Double valueLong = Double.parseDouble(value.value);
                return (Double) rowV >= valueLong;
            }
        } else if (command.equals(Command.SMALLER_OR_EQUALS)) {
            Object rowV = row.get(columnName);
            if (rowV instanceof Long) {
                Long valueLong = Long.parseLong(value.value);
                return (Long) rowV <= valueLong;
            } else if (rowV instanceof Double) {
                Double valueLong = Double.parseDouble(value.value);
                return (Double) rowV <= valueLong;
            }
        }
        return false;
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
