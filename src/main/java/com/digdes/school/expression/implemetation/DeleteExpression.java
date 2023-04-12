package com.digdes.school.expression.implemetation;

import com.digdes.school.expression.Expression;
import com.digdes.school.expression.ModifyingExpression;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DeleteExpression extends ExpressionWithWhere implements Expression, ModifyingExpression {
    public DeleteExpression(String expression) {
        super(expression, "(?i)delete\\s+(.*?)\\s*(where.*)?");
    }

    @Override
    public List<Map<String, Object>> execute(List<Map<String, Object>> mapList) {
        List<Map<String, Object>> newListMap = mapList.stream().filter(row -> condition.matches(row)).toList();
        mapList.removeAll(newListMap);
        return newListMap.stream().map(row -> (Map<String, Object>) new HashMap<>(row)).collect(Collectors.toList());
    }
}
