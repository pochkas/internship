package com.digdes.school.expression.implemetation;

import com.digdes.school.exception.ConditionException;
import com.digdes.school.expression.Condition;
import com.digdes.school.expression.ConditionParser;

import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Only for expressions with WHERE, I use regex for parsing. All regex are special for all expressions. Insert was parsed without regex in other class
public abstract class ExpressionWithWhere {
    protected Condition condition;
    protected String conditionWithWhere;
    private final String regex;
    public ExpressionWithWhere(String expression, String regex) {
        this.regex = regex;
        this.conditionWithWhere = parserForWhere(expression);
        if (conditionWithWhere == null) {
            condition = new AlwaysTrueCondition();
        } else {
            ConditionParser parser = new ConditionParserImpl();
            List<String> tokens = parser.parseCondition(conditionWithWhere);
            condition = parser.createCondition(tokens);
        }
    }
    public String parserForWhere(String expression) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(expression);
        if (matcher.find()) {
            return matcher.group(2);
        }
        return null;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExpressionWithWhere that = (ExpressionWithWhere) o;
        return Objects.equals(condition, that.condition) && Objects.equals(conditionWithWhere, that.conditionWithWhere) && Objects.equals(regex, that.regex);
    }
    @Override
    public int hashCode() {
        return Objects.hash(condition, conditionWithWhere, regex);
    }
    @Override
    public String toString() {
        return "ExpressionWithWhere{" +
                "condition=" + condition +
                ", conditionWithWhere='" + conditionWithWhere + '\'' +
                ", regex='" + regex + '\'' +
                '}';
    }
}
