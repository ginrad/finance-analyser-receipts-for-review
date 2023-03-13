package com.ginrad.finance.analyser.receipts.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormattedDateTimeMatcher implements DateMatcher {

    private static Pattern DATE_PATTERN = Pattern.compile(
            "(19|20)\\\\d\\\\d[- /.](0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](0[0-9]|1[0-9]|2[0123]):([0-5][0-9]):([0-5][0-9])");

    public FormattedDateTimeMatcher() {
    }

    @Override
    public boolean matches(String date) {
        return DATE_PATTERN.matcher(date).matches();
    }

    public String match(String date) {
        Matcher m = DATE_PATTERN.matcher(date);
        if (m.find()) {
            return m.group();
        }
        return null;
    }
}
