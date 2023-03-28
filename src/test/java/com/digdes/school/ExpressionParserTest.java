package com.digdes.school;

import com.digdes.school.exception.CommandException;
import com.digdes.school.expression.Expression;
import com.digdes.school.expression.implemetation.DeleteExpression;
import com.digdes.school.expression.implemetation.InsertExpression;
import com.digdes.school.expression.implemetation.SelectExpression;
import com.digdes.school.expression.implemetation.UpdateExpression;

public class ExpressionParserTest {


    public Expression parser(String expression) {

        String[] array = expression.split(" ");
        String command = array[0];


        if (command.equals("DELETE")) {
            return new DeleteExpression(expression);
        } else if (command.equals("UPDATE")) {
            return new UpdateExpression(expression);
        } else if (command.equals("SELECT")) {
            return new SelectExpression(expression);
        } else if (command.equals("INSERT")) {
            return new InsertExpression();
        } else {
            throw new CommandException("Command was not found!");
        }

    }




}
