package com.mathewgv.infohandling.calclib;

import com.mathewgv.infohandling.calclib.exception.ValidationException;
import com.mathewgv.infohandling.calclib.utility.Validator;

import java.util.Iterator;

public class ExpressionIterator implements Iterator<String> {

    private final String mathExpression;
    private int currentIndex = 0;

    public ExpressionIterator(String mathExpression) {
        if (!Validator.isValid(mathExpression)) {
            throw new ValidationException("Неверный ввод выражения");
        }
        this.mathExpression = mathExpression.replace(" ", "");
    }

    @Override
    public boolean hasNext() {
        return currentIndex < mathExpression.length();
    }

    @Override
    public String next() {
        StringBuilder operand = new StringBuilder();
        char element = mathExpression.charAt(currentIndex);
        while (Character.isDigit(element)) {
            operand.append(element);
            currentIndex++;
            if (currentIndex == mathExpression.length()) {
                return operand.toString();
            }
            element = mathExpression.charAt(currentIndex);
        }

        if (operand.length() != 0) {
            return operand.toString();
        }
        currentIndex++;
        return Character.toString(element);
    }
}
