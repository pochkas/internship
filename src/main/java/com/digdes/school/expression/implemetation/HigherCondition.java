package com.digdes.school.expression.implemetation;

import com.digdes.school.Operand;
import com.digdes.school.expression.Condition;

import java.util.Map;
import java.util.Objects;

// Condition with AND/OR.
public class HigherCondition  implements Condition {
    protected Condition left;
    protected Operand operand;
    protected Condition right;
    public HigherCondition(Condition left, Operand operand, Condition right) {
        this.left = left;
        this.operand = operand;
        this.right = right;
    }
    @Override
    public boolean matches(Map<String, Object> row) {
        if(operand.equals(Operand.AND)){
           return left.matches(row) && right.matches(row);
        }
        else if(operand.equals(Operand.OR)){
            return left.matches(row) || right.matches(row);
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HigherCondition that = (HigherCondition) o;
        return Objects.equals(left, that.left) && operand == that.operand && Objects.equals(right, that.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, operand, right);
    }
}
