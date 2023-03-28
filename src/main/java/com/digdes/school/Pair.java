package com.digdes.school;

import java.util.List;
import java.util.Map;

public class Pair {

    public final List<Map<String, Object>> result;
    public final List<Map<String, Object>> mapList;

    public Pair(List<Map<String, Object>> mapList, List<Map<String, Object>> result){

        this.mapList=mapList;
        this.result=result;

    }
}
