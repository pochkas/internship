package com.digdes.school.expression;

import com.digdes.school.expression.implemetation.InsertExpression;

import java.util.List;

public interface InsertParser {

    public List<String> parseInsert(String insertExpression);

}
