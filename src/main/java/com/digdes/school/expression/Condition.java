package com.digdes.school.expression;

import java.util.Map;

public interface Condition extends ConditionParserToken{

    public boolean matches(Map<String, Object> row);

}
