package com.digdes.school.expression.implemetation;

import com.digdes.school.Command;
import com.digdes.school.Operand;
import com.digdes.school.expression.Condition;
import com.digdes.school.expression.InsertParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InsertParserImpl implements InsertParser {


    @Override
    public List<String> parseInsert(String insertExpression) {

        //"INSERT VALUES 'lastName' = 'Федоров' , 'id'=3, 'age'=40, 'active'=true")

        String strWithoutInsert = insertExpression.substring(13, insertExpression.length());

        char[] chars = strWithoutInsert.toCharArray();

        String[] sentences = strWithoutInsert.trim().split(",");

        ArrayList<String> tokens = new ArrayList<>();

        for (String s : sentences) {

            String[] sentencesSplit = s.split("=");
            tokens.add(sentencesSplit[0].trim());
            tokens.add(sentencesSplit[1].trim());

        }

        return tokens;
    }

    @Override
    public InsertExpression createInsert(List<String> tokens) {

        HashMap<String, Object> map = new HashMap<>();
        for(int i=0; i< tokens.size(); i++){

            map.put(tokens.get(i),  tokens.get(i+1));
        }
        return new InsertExpression(map);
    }

}
