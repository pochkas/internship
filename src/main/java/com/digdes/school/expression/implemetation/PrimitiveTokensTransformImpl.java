package com.digdes.school.expression.implemetation;

import com.digdes.school.expression.TokensTransform;

import java.util.HashMap;
import java.util.List;

public class PrimitiveTokensTransformImpl implements TokensTransform {

    protected HashMap<String, Object> map = new HashMap<>();

    @Override
    public HashMap<String, Object> tokensTransform(List<String> tokens) {

        for (int i = 0; i < tokens.size(); i++) {

            if (tokens.get(i).equalsIgnoreCase("WHERE") || tokens.get(i).equalsIgnoreCase("AND") || tokens.get(i).equalsIgnoreCase("OR")) {

            }

        }
        return map;
    }
}
