package com.digdes.school.expression.implemetation;

import com.digdes.school.exception.CommandException;
import com.digdes.school.expression.Expression;
import com.digdes.school.expression.ExpressionParser;

// Check the beginning of the expression
public class ExpressionParserImpl implements ExpressionParser {
    public Expression parse(String expression) {
        String command = expression.substring(0, 6);
        return switch (command) {
            case "DELETE" -> new DeleteExpression(expression);
            case "UPDATE" -> new UpdateExpression(expression);
            case "SELECT" -> new SelectExpression(expression);
            case "INSERT" -> new InsertExpression(expression);
            default -> throw new CommandException("Command was not found!");
        };
    }
}
