package com.digdes.school;

public enum Operand {

    AND("AND"),
    OR("OR");

    private String text;

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
