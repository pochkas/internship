package com.digdes.school.expression.implementation;

import com.digdes.school.expression.implemetation.InsertExpression;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UpdateExpressionTest {

    @Test
    public void updateSuccessTest(){

        String expression = "UPDATE VALUES ‘active’=false, ‘cost’=10.1 where ‘id’=3";

        Map<String, Object> row = new HashMap<>();
        row.put("'active'",false);

        InsertExpression insertExpression=new InsertExpression(expression);

        List<Map<String, Object>> mapList = new ArrayList<>();
        mapList.add(new HashMap<>());

        List<Map<String, Object>> result = insertExpression.execute(mapList);

        List<Map<String, Object>> expected = new ArrayList<>();
        expected.add(row);


        assertEquals(expected, result);
        assertEquals(mapList.size(), 1);




    }

}
