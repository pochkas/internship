package com.digdes.school.expression.implemetation;

import com.digdes.school.exception.ConditionException;
import com.digdes.school.expression.Condition;
import com.digdes.school.expression.ConditionParser;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class ExpressionWithWhere {

    protected Condition condition;
    protected String conditionWithWhere;
    private String regex;

    public ExpressionWithWhere(String expression, String regex) {
        this.regex=regex;
        this.conditionWithWhere = parserForWhere(expression);
        ConditionParser parser = new ConditionParserImpl();
        List<String> tokens = parser.parseCondition(conditionWithWhere);
        Condition conditionToCreate = parser.createCondition(tokens);
        condition = conditionToCreate;
    }


    public String parserForWhere(String expression) {

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(expression);

        MatchResult result = matcher.toMatchResult();

        return result.group(2);

    }

    public void validate(String conditionWithWhere) {
        if ((!conditionWithWhere.contains("id") || conditionWithWhere.contains("lastName") || conditionWithWhere.contains("age") || conditionWithWhere.contains("cost") || conditionWithWhere.contains("active"))) {
            throw new ConditionException("PrimitiveCondition does not contains right names");
        }

    }


}
