package com.digdes.school.expression;

import java.util.List;
import java.util.Map;

public interface ConditionParser {
    public List<String> parseCondition(String conditionWithWhere);
    public Condition createCondition(List<String> tokens);
}
