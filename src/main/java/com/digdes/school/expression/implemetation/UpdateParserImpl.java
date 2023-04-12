package com.digdes.school.expression.implemetation;

import com.digdes.school.expression.TokensTransform;
import com.digdes.school.expression.UpdateParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UpdateParserImpl implements UpdateParser, TokensTransform {
    protected HashMap<String, Object> map = new HashMap<>();

    @Override
    public String parserForUpdate(String expression) {
        Pattern pattern = Pattern.compile("(?i)^UPDATE\\s+VALUES\\s+(.*)\\s+(WHERE\\s+.+)$");
        Matcher matcher = pattern.matcher(expression);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    @Override
    public HashMap<String, Object> tokensTransform(List<String> tokens) {
        for (int i = 0; i < tokens.size(); i += 2) {
            String str = tokens.get(i);
            String obj = tokens.get(i + 1);
            Value value = new Value(obj);
            map.put(str, value.object);
        }
        return map;
    }
}
