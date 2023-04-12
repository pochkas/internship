package com.digdes.school;

import com.digdes.school.expression.ConditionParserToken;

public enum Operand implements ConditionParserToken {
    AND("AND"),
    OR("OR");
    private final String text;
    Operand(String text){
        this.text=text;
    }
    public String getText() {
        return this.text;
    }
    public static Operand fromString(String text) {
        for (Operand operand : Operand.values()) {
            if (operand.text.equalsIgnoreCase(text)) {
                return operand;
            }
        }
        return null;
    }
}
