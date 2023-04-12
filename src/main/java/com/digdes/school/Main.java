package com.digdes.school;

import java.util.List;
import java.util.Map;
public class Main {
    public static void main(String... args) {
        JavaSchoolStarter starter = new JavaSchoolStarter();
        try {
            // Insert
            List<Map<String, Object>> result1 = starter.execute("INSERT VALUES 'lastName' = 'Федоров' , 'id'=3, 'age'=40, 'active'=true");
            System.out.println(result1);
            // Update
            List<Map<String, Object>> result2 = starter.execute("UPDATE VALUES 'active'=false, 'cost'=10.1 where 'id'=3");
            System.out.println(result2);
            // Select
            List<Map<String, Object>> result3 = starter.execute("SELECT WHERE ‘age’>=30 and ‘lastName’ ilike ‘%р%’");
            System.out.println(result3);
            // Delete
            List<Map<String, Object>> result4 = starter.execute("DELETE WHERE ‘id’=3");
            System.out.println(result4);
            List<Map<String, Object>> result5 =
                    starter.execute("SELECT");
            System.out.println(result5);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
