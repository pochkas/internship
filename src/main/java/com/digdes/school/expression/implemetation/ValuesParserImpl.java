package com.digdes.school.expression.implemetation;

import com.digdes.school.expression.ValuesParser;
import com.digdes.school.expression.UpdateParser;

import java.util.ArrayList;
import java.util.List;

public class ValuesParserImpl implements ValuesParser {

    protected String valuesExpression;
    @Override
    public List<String> parseInsert(String valuesExpression) {
        //"INSERT VALUES 'lastName' = 'Федоров' , 'id'=3, 'age'=40, 'active'=true")
        // UPDATE VALUES ‘active’=false, ‘cost’=10.1 where ‘id’=3
        String strWithoutInsert = valuesExpression;
        if(valuesExpression.toLowerCase().startsWith("insert")) {
            strWithoutInsert = valuesExpression.substring(13, valuesExpression.length());
        } else if(valuesExpression.toLowerCase().startsWith("update")) {
            UpdateParser updateParser = new UpdateParserImpl();
            strWithoutInsert = updateParser.parserForUpdate(valuesExpression).trim();
        }
        ArrayList<String> tokens = new ArrayList<>();
        if(strWithoutInsert.contains(",")) {
            char[] chars = strWithoutInsert.toCharArray();
            String[] sentences = strWithoutInsert.trim().split(",");
            for (String s : sentences) {
                String[] sentencesSplit = s.split("=");
                tokens.add(sentencesSplit[0].trim());
                tokens.add(sentencesSplit[1].trim());
            }
        }
        else{
            String[] sentencesSplit = strWithoutInsert.split("=");
            tokens.add(sentencesSplit[0].trim());
            tokens.add(sentencesSplit[1].trim());
        }
        return tokens;
    }
}
