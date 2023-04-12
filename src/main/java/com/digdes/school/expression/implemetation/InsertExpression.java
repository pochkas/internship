package com.digdes.school.expression.implemetation;

import com.digdes.school.expression.Expression;
import com.digdes.school.expression.ModifyingExpression;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

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
        return listWithInsert;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InsertExpression that = (InsertExpression) o;
        return Objects.equals(map, that.map);
    }
    @Override
    public int hashCode() {
        return Objects.hash(map);
    }
    @Override
    public String toString() {
        return "InsertExpression{" +
                "map=" + map +
                '}';
    }
}
