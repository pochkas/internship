package com.digdes.school.expression.implemetation;

import com.digdes.school.expression.Condition;
import com.digdes.school.expression.InsertParser;
import com.digdes.school.expression.TokensTransform;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class ExpressionWithValues {

    public static HashMap<String,Object> ExpressionToMap(String expression) {
        InsertParser insertParser = new InsertParserImpl();
        List<String> tokens = insertParser.parseInsert(expression);

        TokensTransform tokensTransform = new InsertTokensTransformImpl();
        return tokensTransform.tokensTransform(tokens);
    }


}

