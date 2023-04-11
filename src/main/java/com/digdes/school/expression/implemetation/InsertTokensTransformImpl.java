package com.digdes.school.expression.implemetation;


import com.digdes.school.expression.StringUtils;
import com.digdes.school.expression.TokensTransform;

import java.util.HashMap;
import java.util.List;

public class InsertTokensTransformImpl implements TokensTransform {

    protected HashMap<String, Object> map= new HashMap<>();

    @Override
    public HashMap<String, Object> tokensTransform(List<String> tokens) {

        for(int i=0; i<tokens.size(); i+=2){

            String str = tokens.get(i);
            str = StringUtils.stripQuotes(str);
            String obj = tokens.get(i+1);
            Value value = new Value(obj);
            if(value.object!=null){
                map.put(str, value.object);
            }
        }

        return map;
    }
}
