package com.digdes.school.expression;

import java.util.List;
import java.util.Map;

public interface Expression {


    public List<Map<String, Object>> execute(List<Map<String, Object>> mapList);

}
