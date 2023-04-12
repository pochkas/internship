package com.digdes.school.expression;

import java.util.List;
import java.util.Map;

public interface TokensTransform {
    Map<String, Object> tokensTransform(List<String> tokens);
}
