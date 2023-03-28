package com.digdes.school;

import com.digdes.school.expression.Expression;
import com.digdes.school.expression.ExpressionParser;
import com.digdes.school.expression.implemetation.ExpressionParserImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JavaSchoolStarter {

    List<Map<String, Object>> mapList;
    ExpressionParser expressionParser;

    public JavaSchoolStarter() {
        expressionParser = new ExpressionParserImpl();
        mapList = new ArrayList<>();
    }

    public List<Map<String, Object>> execute(String request) throws Exception {
        //Здесь начало исполнения вашего кодa
        Expression expression = expressionParser.parse(request);
        return expression.execute(mapList);
    }


}
