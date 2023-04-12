package com.digdes.school.expression.implemetation;

import com.digdes.school.expression.ValuesParser;
import com.digdes.school.expression.TokensTransform;

import java.util.List;
import java.util.Map;

// Class for Insert and Update, in which we return HashMap<String, Object> from List<String> tokens
public abstract class ExpressionWithValues {
    public static Map<String,Object> expressionToMap(String expression) {
        ValuesParser valuesParser = new ValuesParserImpl();
        List<String> tokens = valuesParser.parseInsert(expression);
        TokensTransform tokensTransform = new ValuesTokensTransformImpl();
        return tokensTransform.tokensTransform(tokens);
    }
}

