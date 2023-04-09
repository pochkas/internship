package com.digdes.school.expression.implemetation;

import com.digdes.school.Command;
import com.digdes.school.Operand;
import com.digdes.school.expression.Condition;
import com.digdes.school.expression.ConditionParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConditionParserImpl implements ConditionParser {


    @Override
    public List<String> parseCondition(String conditionWithWhere) {

        char[] chars = conditionWithWhere.toCharArray();

        ArrayList<String> tokens = new ArrayList<>();

        //SELECT WHERE ‘age’>=30 and ‘lastName’ ilike ‘%п%’ -> [age, >=, 30, and, lastname, ilike, %p%]
        int i = 0;
        while (i < chars.length) {

            if (chars[i] == 8216 || chars[i] == 39) {
                StringBuilder builder = new StringBuilder();
                builder.append(chars[i]);
                i++;
                while (i < chars.length && (chars[i] != 8217 && chars[i] != 39)) {
                    builder.append(chars[i]);
                    i++;
                }
                if (i < chars.length) {
                    builder.append(chars[i]);
                    i++;

                }
                tokens.add(builder.toString());
                continue;


            }
            if (Character.isDigit(chars[i])) {
                StringBuilder builder = new StringBuilder();

                while (i < chars.length && (Character.isDigit(chars[i]) || chars[i] == '.')) {
                    builder.append(chars[i]);
                    i++;
                }
                tokens.add(builder.toString());
                continue;
            }
            if (chars[i] == '>' || chars[i] == '=' || chars[i] == '<' || chars[i] == '!' && !Character.isLetterOrDigit(chars[i])) {
                StringBuilder builder = new StringBuilder();
                while (i < chars.length && (chars[i] != ' ' && chars[i] != 8216 && !(Character.isLetterOrDigit(chars[i])))) {
                    builder.append(chars[i]);
                    i++;
                }
                tokens.add(builder.toString());
                continue;
            }

            if (Character.isLetter(chars[i])) {
                StringBuilder builder = new StringBuilder();
                while (i < chars.length && Character.isLetter(chars[i])) {
                    builder.append(chars[i]);
                    i++;
                }
                tokens.add(builder.toString());
                continue;
            } else {
                StringBuilder builder = new StringBuilder();
                if (chars[i] == ' ') {
                    i++;
                } else {
                    while (i < chars.length && chars[i] != ' ') {
                        builder.append(chars[i]);
                        i++;
                    }
                    tokens.add(builder.toString());
                }
            }

        }
        return tokens;
    }

    @Override
    public Condition createCondition(List<String> tokens) {

        PrimitiveCondition primitiveCondition = null;
        HigherCondition higherCondition = null;

        if (tokens.size() <= 4) {
            for (int i = 0; i < tokens.size(); i++) {
                primitiveCondition = new PrimitiveCondition(tokens.get(1), Command.fromString(tokens.get(2)), new Value(tokens.get(3)));

                return primitiveCondition;
            }
        } else if (tokens.size() > 7) {
            for (int i = 0; i < tokens.size() - 7; i++) {
                primitiveCondition = new PrimitiveCondition(tokens.get(i + 1), Command.fromString(tokens.get(i + 2)), new Value(tokens.get(i + 3)));

                String operand = tokens.get(i + 4);
                if (operand.equalsIgnoreCase("and") || operand.equalsIgnoreCase("or")) {
                    higherCondition = new HigherCondition(primitiveCondition, Operand.fromString(operand), new PrimitiveCondition(tokens.get(i + 5), Command.fromString(tokens.get(i + 6)), new Value(tokens.get(i + 7))));
                }

                return higherCondition;
            }
        }
        return null;
    }




}
