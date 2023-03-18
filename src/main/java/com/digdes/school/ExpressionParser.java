package com.digdes.school;

import com.digdes.school.exception.CommandException;
import com.digdes.school.expression.Expression;
import com.digdes.school.expression.implemetation.DeleteExpression;
import com.digdes.school.expression.implemetation.InsertExpression;
import com.digdes.school.expression.implemetation.SelectExpression;
import com.digdes.school.expression.implemetation.UpdateExpression;

public class ExpressionParser {


    public Expression parser(String expression) {

        String[] array = expression.split(" ");
        String command = array[0];


        if (command.equals("DELETE")) {
            return new DeleteExpression();
        } else if (command.equals("UPDATE")) {
            return new UpdateExpression();
        } else if (command.equals("SELECT")) {
            return new SelectExpression();
        } else if (command.equals("INSERT")) {
            return new InsertExpression();
        } else {
            throw new CommandException("Command was not found!");
        }

    }




}
