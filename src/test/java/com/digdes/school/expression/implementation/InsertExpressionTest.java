package com.digdes.school.expression.implementation;

import com.digdes.school.expression.implemetation.InsertExpression;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class InsertExpressionTest {
    @Test
    public void executeSuccessTest() {
        String expression = "INSERT VALUES 'lastName' = 'Федоров' , 'id'=3, 'age'=40, 'active'=true";
        Map<String, Object> row3 = new HashMap<>();
        row3.put("id",3L);
        row3.put("lastName","Федоров");
        row3.put("age",40L);
        row3.put("active", true);
        InsertExpression insertExpression = new InsertExpression(expression);
        List<Map<String, Object>> mapList = new ArrayList<>();
        mapList.add(new HashMap<>());
        List<Map<String, Object>> result = insertExpression.execute(mapList);
        List<Map<String, Object>> expected = new ArrayList<>();
        expected.add(row3);
        assertEquals(expected, result);
        assertEquals(mapList.size(), 2);
    }
    @Test
    public void nullSuccessTest() {
        String expression = "INSERT VALUES 'lastName' = null , 'id'=3, 'age'=40, 'active'=true";
        Map<String, Object> row = new HashMap<>();
        row.put("id",3L);
        row.put("age",40L);
        row.put("active", true);
        InsertExpression insertExpression = new InsertExpression(expression);
        List<Map<String, Object>> mapList = new ArrayList<>();
        mapList.add(new HashMap<>());
        List<Map<String, Object>> result = insertExpression.execute(mapList);
        List<Map<String, Object>> expected = new ArrayList<>();
        expected.add(row);
        assertEquals(expected, result);
        assertEquals(mapList.size(), 2);
    }
}
