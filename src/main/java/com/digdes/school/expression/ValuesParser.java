package com.digdes.school.expression;

import java.util.List;

public interface ValuesParser {
    List<String> parseInsert(String insertExpression);
}
