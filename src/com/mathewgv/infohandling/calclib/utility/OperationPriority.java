package com.mathewgv.infohandling.calclib.utility;

import java.util.HashMap;
import java.util.Map;

public final class OperationPriority {

    private static final Map<String, Integer> operationPriorityMap = new HashMap<>();

    static {
        addOperator("*", 1);
        addOperator("/", 1);
        addOperator("+", 2);
        addOperator("-", 2);
    }

    private OperationPriority() {
    }

    public static void addOperator(String operator, Integer priority) {
        operationPriorityMap.put(operator, priority);
    }

    public static Integer getPriority(String operator) {
        return operationPriorityMap.get(operator);
    }
}
