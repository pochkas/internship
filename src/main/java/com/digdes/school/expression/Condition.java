package com.digdes.school.expression;

import java.util.Map;

public interface Condition {

    public boolean matches(Map<String, Object> row);

}
