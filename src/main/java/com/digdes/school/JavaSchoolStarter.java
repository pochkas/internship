package com.digdes.school;

import com.digdes.school.expression.Expression;
import com.digdes.school.expression.ExpressionParser;
import com.digdes.school.expression.implemetation.ExpressionParserImpl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class JavaSchoolStarter {
    List<Map<String, Object>> mapList;
    ExpressionParser expressionParser;
    public JavaSchoolStarter() {
        expressionParser = new ExpressionParserImpl();
        mapList = new CopyOnWriteArrayList<>();
    }
    public List<Map<String, Object>> execute(String request) throws Exception {
        //Здесь начало исполнения вашего кодa
        Expression expression = expressionParser.parse(request);
        return expression.execute(mapList);
    }
}
