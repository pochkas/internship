package com.digdes.school.expression.implementation;

import com.digdes.school.exception.CommandException;
import com.digdes.school.exception.ConditionException;
import com.digdes.school.expression.TokensTransform;
import com.digdes.school.expression.implemetation.ValuesTokensTransformImpl;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ValuesTokensTransformImplTest {
    static TokensTransform tokensTransform = new ValuesTokensTransformImpl();
    @Test
    public void successTestForTransform(){
        List<String> tokens = new ArrayList<>();
        tokens.add("lastName");
        tokens.add("'Федоров'");
        tokens.add("id");
        tokens.add("3");
        tokens.add("age");
        tokens.add("40");
        tokens.add("active");
        tokens.add("true");
        HashMap<String, Object> expected = new HashMap<>();
        expected.put("lastName", "Федоров");
        expected.put("id", 3L);
        expected.put("age", 40L);
        expected.put("active", true);
        HashMap<String, Object> result = tokensTransform.tokensTransform(tokens);
        assertEquals(expected, result);
    }
    @Test
    public void failureTestForTransform(){
        List<String> tokens = new ArrayList<>();
        tokens.add("lastName");
        tokens.add("5");
        tokens.add("id");
        tokens.add("3");
        tokens.add("age");
        tokens.add("40");
        tokens.add("active");
        tokens.add("true");
        String expected = "lastname has type class java.lang.Long, but lastname should be String.";
        Exception exception = assertThrows(CommandException.class, () -> {
            tokensTransform.tokensTransform(tokens);
        });
        String result = exception.getMessage();
        assertTrue(result.contains(expected));
    }
}
