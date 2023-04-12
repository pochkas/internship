package com.digdes.school.expression.implemetation;

import com.digdes.school.expression.Expression;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectExpression extends ExpressionWithWhere implements Expression {
    public SelectExpression(String expression) {
        super(expression, "(?i)select\\s+(.*?)\\s*(where.*)?");
    }
    @Override
    public List<Map<String, Object>> execute(List<Map<String, Object>> mapList) {
        return mapList.stream().filter(row -> condition.matches(row)).map(row -> (Map<String, Object>) new HashMap<>(row)).toList();
    }
}
