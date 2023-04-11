package com.digdes.school.expression.implemetation;

import com.digdes.school.expression.ValuesParser;
import com.digdes.school.expression.TokensTransform;

import java.util.HashMap;
import java.util.List;

public abstract class ExpressionWithValues {

    public static HashMap<String,Object> ExpressionToMap(String expression) {
        ValuesParser valuesParser = new ValuesParserImpl();
        List<String> tokens = valuesParser.parseInsert(expression);

        TokensTransform tokensTransform = new InsertTokensTransformImpl();
        return tokensTransform.tokensTransform(tokens);
    }


}

