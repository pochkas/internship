package com.digdes.school.expression;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public interface Expression {
    List<Map<String, Object>> execute(List<Map<String, Object>> mapList);
}
