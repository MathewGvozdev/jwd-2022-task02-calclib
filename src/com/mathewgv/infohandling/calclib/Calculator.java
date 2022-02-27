package com.mathewgv.infohandling.calclib;

import com.mathewgv.infohandling.calclib.exception.ValidationException;
import com.mathewgv.infohandling.calclib.utility.Validator;

import java.util.ArrayDeque;
import java.util.Deque;

import static com.mathewgv.infohandling.calclib.utility.OperationPriority.getPriority;

public class Calculator {

    private final ExpressionIterator iterator;
    private final Deque<Double> operands = new ArrayDeque<>();
    private final Deque<String> operators = new ArrayDeque<>();

    public Calculator(ExpressionIterator iterator) {
        this.iterator = iterator;
    }

    public Calculator(String mathExpression) {
        if (!Validator.isValid(mathExpression)) {
            throw new ValidationException("Неверный ввод выражения");
        }
        this.iterator = new ExpressionIterator(mathExpression);
    }

    public Double solve() {
        while (iterator.hasNext()) {
            String element = iterator.next();
            selectOperation(element);
        }
        while (!operators.isEmpty()) {
            calculate();
        }
        return operands.pop();
    }

    private void selectOperation(String element) {
        try {
            addOperand(element);
        } catch (NumberFormatException e) {
            countTempValue(element);
        }
    }

    private void addOperand(String element) {
        double number = Double.parseDouble(element);
        operands.push(number);
    }

    private void countTempValue(String element) {
        if (isOpeningBracket(element)) {
            operators.push(element);
        } else if (isClosingBracket(element)) {
            countBrackets();
            operators.pop();
        } else {
            if (operators.isEmpty()) {
                operators.push(element);
            } else {
                countByPriority(element);
                operators.push(element);
            }
        }
    }

    private void countBrackets() {
        while (!isOpeningBracket(operators.peek())) {
            calculate();
        }
    }

    private void countByPriority(String element) {
        Integer priority = getPriority(element);
        while (!operators.isEmpty() && !isOpeningBracket(operators.peek()) && !isClosingBracket(operators.peek()) &&
                priority >= getPriority(operators.peek())) {
            calculate();
        }
    }

    private void calculate() {
        String operator = operators.pop();
        Double n2 = operands.pop();
        Double n1 = operands.pop();
        Double result = switch (operator) {
            case "+" -> n1 + n2;
            case "-" -> n1 - n2;
            case "*" -> n1 * n2;
            case "/" -> n1 / n2;
            default -> throw new RuntimeException("Проверьте закрывающие скобки");
        };
        operands.push(result);
    }

    private boolean isClosingBracket(String element) {
        return element.equals(")");
    }

    private boolean isOpeningBracket(String element) {
        return element.equals("(");
    }
}
