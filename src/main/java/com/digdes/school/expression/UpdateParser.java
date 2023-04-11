package com.digdes.school.expression;

import java.util.List;
import java.util.Map;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface UpdateParser {

    public String parserForUpdate(String expression);

    public List<String> createUpdateTokens(String expression);

}
