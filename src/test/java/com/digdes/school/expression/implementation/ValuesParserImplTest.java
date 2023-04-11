package com.digdes.school.expression.implementation;


import com.digdes.school.expression.ValuesParser;
import com.digdes.school.expression.implemetation.ValuesParserImpl;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValuesParserImplTest {

    ValuesParser valuesParser = new ValuesParserImpl();

    @Test
    public void successTestParseInsert() {

        String example = "INSERT VALUES 'lastName' = 'Федоров' , 'id'=3, 'age'=40, 'active'=true";

        List<String> result = valuesParser.parseInsert(example);

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
