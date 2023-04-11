package com.digdes.school.expression.implemetation;

import com.digdes.school.Command;
import com.digdes.school.Operand;
import com.digdes.school.expression.Condition;
import com.digdes.school.expression.ConditionParser;
import com.digdes.school.expression.ConditionParserToken;

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
        ArrayList<ConditionParserToken> list = new ArrayList<ConditionParserToken>();
        for (int i = 0; i < tokens.size(); i += 4) {
            list.add(new PrimitiveCondition(tokens.get(i + 1), Command.fromString(tokens.get(i + 2)), new Value(tokens.get(i + 3))));
            if(tokens.size()==i+4){
                break;
            }
            Operand operand = Operand.fromString(tokens.get(i + 4));
            list.add(operand);
        }

        List<ConditionParserToken> newTokens = new ArrayList<>();
        HigherCondition prev = null;
        int prevAnd = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(Operand.AND)) {
                newTokens.remove(newTokens.size() - 1);
                if (prevAnd == i - 2) {
                    prev = new HigherCondition(prev, Operand.AND, (Condition) list.get(i + 1));
                } else {
                    prev = new HigherCondition((Condition) list.get(i - 1), Operand.AND, (Condition) list.get(i + 1));
                }
                prevAnd = i;
                newTokens.add(prev);
                i++;
            } else {
                newTokens.add(list.get(i));
            }
        }
        Condition result = (Condition) newTokens.get(0);
        for (int i = 1; i < newTokens.size(); i += 2) {
            result = new HigherCondition(result, Operand.OR, (Condition) newTokens.get(i + 1));
        }
        return result;
    }
}
