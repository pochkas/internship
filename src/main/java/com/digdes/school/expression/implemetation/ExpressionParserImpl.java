package com.digdes.school.expression.implemetation;

import com.digdes.school.exception.CommandException;
import com.digdes.school.expression.Expression;
import com.digdes.school.expression.ExpressionParser;
import com.digdes.school.expression.implemetation.DeleteExpression;
import com.digdes.school.expression.implemetation.InsertExpression;
import com.digdes.school.expression.implemetation.SelectExpression;
import com.digdes.school.expression.implemetation.UpdateExpression;

public class ExpressionParserImpl implements ExpressionParser {


    public Expression parse(String expression) {

        String command = expression.substring(0,6);

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
