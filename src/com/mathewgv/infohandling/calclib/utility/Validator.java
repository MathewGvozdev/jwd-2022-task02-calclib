package com.mathewgv.infohandling.calclib.utility;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Validator {

    private static final String REGEX_INVALID_VALUES = "[a-zA-Zа-яА-Я.,]";
    private static final String REGEX_VALID_VALUES = "\\d+\\s*([+\\-*/]\\s*\\d+)+|\\(.+[+\\-*/].+\\)";

    private Validator() {
    }

    public static boolean isValid(String mathExpression) {
        Pattern invalid = Pattern.compile(REGEX_INVALID_VALUES);
        Matcher invalidMatcher = invalid.matcher(mathExpression);

        Pattern valid = Pattern.compile(REGEX_VALID_VALUES);
        Matcher validMatcher = valid.matcher(mathExpression);

        return validMatcher.find() && !invalidMatcher.find();
    }
}
