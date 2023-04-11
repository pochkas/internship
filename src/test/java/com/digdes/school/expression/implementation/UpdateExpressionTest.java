package com.digdes.school.expression.implementation;

import com.digdes.school.Command;
import com.digdes.school.expression.implemetation.InsertExpression;
import com.digdes.school.expression.implemetation.PrimitiveCondition;
import com.digdes.school.expression.implemetation.UpdateExpression;
import com.digdes.school.expression.implemetation.Value;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UpdateExpressionTest {

    @Test
    public void updateSuccessTest() {


        String expression = "UPDATE VALUES 'active'=false, 'cost'=10.1 where 'id'=3";

        Map<String, Object> row = new HashMap<>();
        row.put("id",3L);
        row.put("lastName","Федоров");
        row.put("age",40L);
        row.put("active", false);
        row.put("cost", 10.1);

        UpdateExpression updateExpression = new UpdateExpression(expression);
        HashMap<String, Object> map = new HashMap<>();
        map.put("id",3L);
        map.put("lastName","Федоров");
        map.put("age",40L);
        map.put("active", true);

        List<Map<String, Object>> mapList = new ArrayList<>();
        mapList.add(map);

        List<Map<String, Object>> result = updateExpression.execute(mapList);

        List<Map<String, Object>> expected = new ArrayList<>();
        expected.add(row);

        assertEquals(expected, result);
        assertEquals(mapList.size(), 1);


    }

}
