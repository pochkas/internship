package com.digdes.school.expression;

import java.util.HashMap;
import java.util.List;

public interface TokensTransform {

    public HashMap<String, Object> tokensTransform(List<String> tokens);
}
