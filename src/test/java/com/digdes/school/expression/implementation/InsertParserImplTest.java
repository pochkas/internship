package com.digdes.school.expression.implementation;


import com.digdes.school.expression.InsertParser;
import com.digdes.school.expression.implemetation.InsertParserImpl;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InsertParserImplTest {

    InsertParser insertParser = new InsertParserImpl();

    @Test
    public void successTestParseInsert() {

        String example = "INSERT VALUES 'lastName' = 'Федоров' , 'id'=3, 'age'=40, 'active'=true";

        List<String> result = insertParser.parseInsert(example);

        List<Object> expected = new ArrayList<>();
        expected.add("'lastName'");
        expected.add("'Федоров'");
        expected.add("'id'");
        expected.add("3");
        expected.add("'age'");
        expected.add("40");
        expected.add("'active'");
        expected.add("true");

        assertEquals(result, expected);

    }
}
