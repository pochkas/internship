package com.digdes.school;

public enum Command {

    like("like"),
    ilike("ilike"),
    EQUALS("="),
    NON_EQUALS("!="),
    GREATER(">"),
    SMALLER("<"),
    GREATER_OR_EQUALS(">="),
    SMALLER_OR_EQUALS("<=");

    private  String text;

    Command(String text){
        this.text=text;
    }

    public String getText() {
        return this.text;
    }

    public static Command fromString(String text) {
        for (Command command : Command.values()) {
            if (command.text.equalsIgnoreCase(text)) {
                return command;
            }
        }
        return null;
    }



}
