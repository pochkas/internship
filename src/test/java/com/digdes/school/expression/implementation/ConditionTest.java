package com.digdes.school.expression.implementation;

import com.digdes.school.Command;
import com.digdes.school.expression.implemetation.PrimitiveCondition;
import com.digdes.school.expression.implemetation.Value;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class ConditionTest {
    @Test
    public void successTest() {
        PrimitiveCondition condition = new PrimitiveCondition("lastName", Command.EQUALS, new Value("'Petrov'"));
        Map<String, Object> row = new HashMap<>();
        row.put("lastName", "Petrov");
        assertTrue(condition.matches(row));
    }
    @Test
    public void successLikeIlikeTest() {
        PrimitiveCondition condition = new PrimitiveCondition("lastName", Command.like, new Value("'Petrov'"));
        Map<String, Object> row = new HashMap<>();
        row.put("lastName", "Petrov");
        assertTrue(condition.matches(row));
    }
    @Test
    public void successLikeTest() {
        PrimitiveCondition condition = new PrimitiveCondition("lastName", Command.like, new Value("'%Petrov'"));
        Map<String, Object> row = new HashMap<>();
        row.put("lastName", "Petrov");
        assertTrue(condition.matches(row));
        PrimitiveCondition condition1 = new PrimitiveCondition("lastName", Command.like, new Value("'Petrov%'"));
        Map<String, Object> row1 = new HashMap<>();
        row1.put("lastName", "Petrov");
        assertTrue(condition1.matches(row1));
        PrimitiveCondition condition2 = new PrimitiveCondition("lastName", Command.like, new Value("'%Petrov%'"));
        Map<String, Object> row2 = new HashMap<>();
        row2.put("lastName", "Petrov");
        assertTrue(condition2.matches(row2));
    }
    @Test
    public void successIlikeTest() {
        PrimitiveCondition condition = new PrimitiveCondition("lastName", Command.ilike, new Value("'%PeTRov'"));
        Map<String, Object> row = new HashMap<>();
        row.put("lastName", "Petrov");
        assertTrue(condition.matches(row));
        PrimitiveCondition condition1 = new PrimitiveCondition("lastName", Command.ilike, new Value("'PeTRov%'"));
        Map<String, Object> row1 = new HashMap<>();
        row1.put("lastName", "Petrov");
        assertTrue(condition1.matches(row1));
        PrimitiveCondition condition2 = new PrimitiveCondition("lastName", Command.ilike, new Value("'%PeTRov%'"));
        Map<String, Object> row2 = new HashMap<>();
        row2.put("lastName", "Petrov");
        assertTrue(condition2.matches(row2));
    }
    @Test
    public void failureLikeTest() {
        PrimitiveCondition condition = new PrimitiveCondition("lastName", Command.like, new Value("'%Petrov'"));
        Map<String, Object> row = new HashMap<>();
        row.put("lastName", "Федоров");
        assertFalse(condition.matches(row));
    }
    @Test
    public void failureSmallerTest() {
        PrimitiveCondition condition = new PrimitiveCondition("'lastName'", Command.SMALLER, new Value("'Fedorov'"));
        Map<String, Object> row = new HashMap<>();
        row.put("'lastName'", "'Petrov'");
        assertFalse(condition.matches(row));
    }
    @Test
    public void failureSmallerOrEqualsTest() {
        PrimitiveCondition condition = new PrimitiveCondition("'lastName'", Command.SMALLER_OR_EQUALS, new Value("'Fedorov'"));
        Map<String, Object> row = new HashMap<>();
        row.put("'lastName'", "'Petrov'");
        assertFalse(condition.matches(row));
    }
    @Test
    public void nullSuccessTest() {
        PrimitiveCondition condition = new PrimitiveCondition("'lastName'", Command.SMALLER_OR_EQUALS, new Value("'Petrov'"));
        Map<String, Object> row = new HashMap<>();
        assertFalse(condition.matches(row));
    }
    @Test
    public void nullAgeSuccessTest() {
        PrimitiveCondition condition = new PrimitiveCondition("'age'", Command.NON_EQUALS, new Value("0"));
        Map<String, Object> row = new HashMap<>();
        assertTrue(condition.matches(row));
    }
    @Test
    public void nullGreaterOrEqualsSuccessTest() {
        PrimitiveCondition condition = new PrimitiveCondition("'age'", Command.GREATER_OR_EQUALS, new Value("0"));
        Map<String, Object> row = new HashMap<>();
        assertFalse(condition.matches(row));
    }
    @Test
    public void nullSmallerOrEqualsSuccessTest() {
        PrimitiveCondition condition = new PrimitiveCondition("'age'", Command.SMALLER_OR_EQUALS, new Value("0"));
        Map<String, Object> row = new HashMap<>();
        assertFalse(condition.matches(row));
    }
}
