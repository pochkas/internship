package com.digdes.school.expression.implementation;

import com.digdes.school.Command;
import com.digdes.school.expression.implemetation.PrimitiveCondition;
import com.digdes.school.expression.implemetation.Value;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConditionTest {

    PrimitiveCondition condition = new PrimitiveCondition("'lastName'", Command.EQUALS, new Value("'Petrov'"));

    @Test
    public void successTest() {
        Map<String, Object> row = new HashMap<>();
        row.put("'lastName'", "'Petrov'");

        assertTrue(condition.matches(row));
    }
}
