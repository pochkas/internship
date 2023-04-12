package com.digdes.school.expression.implemetation;

import com.digdes.school.expression.Expression;
import com.digdes.school.expression.ModifyingExpression;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InsertExpression implements Expression, ModifyingExpression {
    Map<String, Object> map;
    public InsertExpression(String expression) {
        map= new ConcurrentHashMap<>(ExpressionWithValues.expressionToMap(expression));
    }
    @Override
    public List<Map<String, Object>> execute(List<Map<String, Object>> mapList) {
        mapList.add(map);
        List<Map<String, Object>> listWithInsert = new ArrayList<>();
        listWithInsert.add(map);
        return listWithInsert.stream().map(row -> (Map<String, Object>) new HashMap<>(row)).collect(Collectors.toList());
    }
}
