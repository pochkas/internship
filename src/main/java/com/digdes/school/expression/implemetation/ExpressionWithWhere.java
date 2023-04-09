package com.digdes.school.expression.implemetation;

import com.digdes.school.exception.ConditionException;
import com.digdes.school.expression.Condition;
import com.digdes.school.expression.ConditionParser;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class ExpressionWithWhere {

    protected Condition condition;
    protected String conditionWithWhere;
    private String regex;


    public ExpressionWithWhere(String expression, String regex) {
        this.regex = regex;

        this.conditionWithWhere = parserForWhere(expression);
        if (conditionWithWhere == null) {
            condition = new AlwaysTrueCondition();
        } else {
            ConditionParser parser = new ConditionParserImpl();
            List<String> tokens = parser.parseCondition(conditionWithWhere);
            Condition conditionToCreate = parser.createCondition(tokens);
            condition = conditionToCreate;
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


    public void validate(String conditionWithWhere) {
        if ((!conditionWithWhere.contains("id") || conditionWithWhere.contains("lastName") || conditionWithWhere.contains("age") || conditionWithWhere.contains("cost") || conditionWithWhere.contains("active"))) {
            throw new ConditionException("PrimitiveCondition does not contains right names");
        }

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
