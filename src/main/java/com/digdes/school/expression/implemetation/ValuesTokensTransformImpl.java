package com.digdes.school.expression.implemetation;


import com.digdes.school.exception.CommandException;
import com.digdes.school.expression.StringUtils;
import com.digdes.school.expression.TokensTransform;

import java.util.*;

// Transform List<String> to HashMap<String, Object> in expressions with VALUES. Check if column name has right type
public class ValuesTokensTransformImpl implements TokensTransform {
    protected HashMap<String, Object> map = new HashMap<>();

    @Override
    public HashMap<String, Object> tokensTransform(List<String> tokens) {
        for (int i = 0; i < tokens.size(); i += 2) {
            String str = tokens.get(i);
            str = StringUtils.stripQuotes(str);
            if (!StringUtils.validColumnName(str)) {
                throw new CommandException(str + " is not a valid field name.");
            }
            String obj = tokens.get(i + 1);
            Value value = new Value(obj);
            if (str.equalsIgnoreCase("id") && value.object != null && !(value.object instanceof Long)) {
                throw new CommandException("id has type " + value.object.getClass() + ", but id should be Long.");
            } else if (str.equalsIgnoreCase("lastname") && value.object != null && !(value.object instanceof String)) {
                throw new CommandException("lastname has type " + value.object.getClass() + ", but lastname should be String.");
            } else if (str.equalsIgnoreCase("age") && value.object != null && !(value.object instanceof Long)) {
                throw new CommandException("age has type " + value.object.getClass() + ", but age should be Long.");
            } else if (str.equalsIgnoreCase("cost") && value.object != null && !(value.object instanceof Double)) {
                throw new CommandException("cost has type " + value.object.getClass() + ", but cost should be Double.");
            } else if (str.equalsIgnoreCase("active") && value.object != null && !(value.object instanceof Boolean)) {
                throw new CommandException("active has type " + value.object.getClass() + ", but active should be Boolean.");
            } else if (value.object != null) {
                map.put(str, value.object);
            }
        }
        return map;
    }
}
