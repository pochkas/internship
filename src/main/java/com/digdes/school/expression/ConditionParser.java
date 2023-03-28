package com.digdes.school.expression;

import java.util.List;

public interface ConditionParser {

    public List<String> parseCondition(String conditionWithWhere);

    public Condition createCondition(List<String> tokens);


}
