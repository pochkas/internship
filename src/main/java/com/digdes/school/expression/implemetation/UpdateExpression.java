package com.digdes.school.expression.implemetation;

import com.digdes.school.Pair;
import com.digdes.school.expression.Expression;
import com.digdes.school.expression.ModifyingExpression;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UpdateExpression extends ExpressionWithWhere implements Expression, ModifyingExpression {

    // UPDATE VALUES ‘active’=false, ‘cost’=10.1 where ‘id’=3

    String regex;

    Map<String, Object> map;

    public UpdateExpression(String expression) {
        super(expression, "(?i)update\\s+(.*?)\\s*(where.*)?");
    }

    @Override
    public List<Map<String, Object>> execute(List<Map<String, Object>> mapList) {
        List<Map<String, Object>> resultList = mapList.stream().filter(row -> condition.matches(row)).collect(Collectors.toList());
        resultList.forEach(row -> row.putAll(map));
        return resultList;
    }
}
