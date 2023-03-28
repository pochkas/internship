package com.digdes.school.expression.implemetation;

import com.digdes.school.Command;
import com.digdes.school.Pair;
import com.digdes.school.expression.Expression;
import com.digdes.school.expression.ModifyingExpression;

import java.util.*;
import java.util.stream.Collectors;

public class InsertExpression implements Expression, ModifyingExpression {

    Map<String, Object> map;
    public InsertExpression() {

    }

    public InsertExpression(Map<String, Object> map) {
        this.map = map;
    }


    @Override
    public  List<Map<String, Object>> execute(List<Map<String, Object>> mapList) {

       mapList.forEach(row -> row.putAll(map));
        return mapList.stream().collect(Collectors.toUnmodifiableList());


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
