package com.digdes.school.expression.implementation;

import com.digdes.school.Command;
import com.digdes.school.Operand;
import com.digdes.school.expression.Condition;
import com.digdes.school.expression.ConditionParser;
import com.digdes.school.expression.implemetation.ConditionParserImpl;
import com.digdes.school.expression.implemetation.HigherCondition;
import com.digdes.school.expression.implemetation.PrimitiveCondition;
import com.digdes.school.expression.implemetation.Value;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConditionParserImplTest {

    static ConditionParser conditionParser = new ConditionParserImpl();

    @Test
    public void testSuccess() {

        String example = "WHERE 'age'>=30 and 'lastName' ilike '%п%'";

        List<String> result = conditionParser.parseCondition(example);

        List<String> tokens = new ArrayList<>();
        tokens.add("WHERE");
        tokens.add("'age'");
        tokens.add(">=");
        tokens.add("30");
        tokens.add("and");
        tokens.add("'lastName'");
        tokens.add("ilike");
        tokens.add("'%п%'");

        assertEquals(tokens, result);

    }

    @Test
    public void testSuccessAnotherQuotes() {

        String example = "WHERE ‘age’>=30 and ‘lastName’ ilike ‘%п%’";

        List<String> result = conditionParser.parseCondition(example);

        List<String> tokens = new ArrayList<>();
        tokens.add("WHERE");
        tokens.add("‘age’");
        tokens.add(">=");
        tokens.add("30");
        tokens.add("and");
        tokens.add("‘lastName’");
        tokens.add("ilike");
        tokens.add("‘%п%’");

        assertEquals(tokens, result);

    }

    @Test
    public void testSuccessWithPoint() {

        String example = "WhErE !=1.90 oR ‘lastName’ ki ‘dsj45646.hk’";

        List<String> result = conditionParser.parseCondition(example);

        List<String> tokens = new ArrayList<>();
        tokens.add("WhErE");
        tokens.add("!=");
        tokens.add("1.90");
        tokens.add("oR");
        tokens.add("‘lastName’");
        tokens.add("ki");
        tokens.add("‘dsj45646.hk’");

        assertEquals(tokens, result);

    }

    @Test
    public void testBadInput() {

        String example = "WhErE !=1.90 oR ‘lastName’ ki ‘dsj45646.hk";

        List<String> result = conditionParser.parseCondition(example);

        List<String> tokens = new ArrayList<>();
        tokens.add("WhErE");
        tokens.add("!=");
        tokens.add("1.90");
        tokens.add("oR");
        tokens.add("‘lastName’");
        tokens.add("ki");
        tokens.add("‘dsj45646.hk");

        assertEquals(tokens, result);

    }

    @Test
    public void testBadInputSpecialSymbol() {

        String example = "+";

        List<String> result = conditionParser.parseCondition(example);

        List<String> tokens = new ArrayList<>();
        tokens.add("+");


        assertEquals(tokens, result);

    }

    @Test
    public void conditionCreatorTest() {

        List<String> tokens = new ArrayList<>();
        tokens.add("WHERE");
        tokens.add("'age'");
        tokens.add(">=");
        tokens.add("30");
        Condition result = conditionParser.createCondition(tokens);

        Condition expected = new PrimitiveCondition("'age'", Command.GREATER_OR_EQUALS, new Value("30"));

        assertEquals(result, expected);
    }

    @Test
    public void conditionCreatorWithAndTest() {

        List<String> tokens = new ArrayList<>();
        tokens.add("WHERE");
        tokens.add("'age'");
        tokens.add(">=");
        tokens.add("30");
        tokens.add("and");
        tokens.add("'lastName'");
        tokens.add("ilike");
        tokens.add("'%п%'");
        Condition result = conditionParser.createCondition(tokens);

        Condition expected = new HigherCondition(new PrimitiveCondition("'age'", Command.GREATER_OR_EQUALS, new Value("30")), Operand.AND, new PrimitiveCondition("'lastName'", Command.ilike, new Value("'%п%'")));

        assertEquals(expected, result);
    }

    @Test
    public void higherConditionTest() {

        PrimitiveCondition primitiveCondition1 = new PrimitiveCondition("age", Command.EQUALS, new Value("40"));
        PrimitiveCondition primitiveCondition2= new PrimitiveCondition ("'lastname'", Command.EQUALS,  new Value("'Федоров'"));
        PrimitiveCondition primitiveCondition3 = new PrimitiveCondition ("active", Command.EQUALS, new Value("true"));


        HigherCondition expected = new HigherCondition(primitiveCondition1, Operand.OR, new HigherCondition(primitiveCondition2, Operand.AND, primitiveCondition3));

        List<String> tokens = new ArrayList<>();
        tokens.add("WHERE");
        tokens.add("age");
        tokens.add("=");
        tokens.add("40");
        tokens.add("OR");
        tokens.add("'lastname'");
        tokens.add("=");
        tokens.add("'Федоров'");
        tokens.add("AND");
        tokens.add("active");
        tokens.add("=");
        tokens.add("true");

        Condition result =conditionParser.createCondition(tokens);


        assertEquals(expected, result);

    }

}
