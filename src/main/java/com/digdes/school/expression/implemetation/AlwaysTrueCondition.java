package com.digdes.school.expression.implemetation;

import com.digdes.school.expression.Condition;

import java.util.Map;

public class AlwaysTrueCondition implements Condition {
    @Override
    public boolean matches(Map<String, Object> row) {
        return true;
    }
}
