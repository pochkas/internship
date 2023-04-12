package com.digdes.school.expression;

import java.util.List;
import java.util.Map;

public interface ConditionParser {
    List<String> parseCondition(String conditionWithWhere);
    Condition createCondition(List<String> tokens);
}
