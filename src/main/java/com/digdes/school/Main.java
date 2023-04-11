package com.digdes.school;

import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String... args) {
        JavaSchoolStarter starter = new JavaSchoolStarter();
        try {

//Вставка строки в коллекцию
            List<Map<String, Object>> result1 = starter.execute("INSERT VALUES 'lastName' = 'Федоров' , 'id'=3, 'age'=40, 'active'=true");
            System.out.println(result1);
//Изменение значения которое выше записывали
            List<Map<String, Object>> result2 = starter.execute("UPDATE VALUES 'active'=false, 'cost'=10.1 where 'id'=3");
            System.out.println(result2);

            List<Map<String, Object>> result3 = starter.execute("SELECT WHERE ‘age’>=30 and ‘lastName’ ilike ‘%р%’");
            System.out.println(result3);

            List<Map<String, Object>> result4 = starter.execute("DELETE WHERE ‘id’=3");
            System.out.println(result4);



            //Получение всех данных из коллекции (т.е. в данном примере вернется 1 запись)
            List<Map<String, Object>> result5 =
                    starter.execute("SELECT");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
