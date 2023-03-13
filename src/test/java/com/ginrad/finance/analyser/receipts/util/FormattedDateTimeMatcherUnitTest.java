package com.ginrad.finance.analyser.receipts.util;

import org.junit.Assert;
import org.junit.Test;

public class FormattedDateTimeMatcherUnitTest {

    private DateMatcher matcher = new FormattedDateTimeMatcher();

    @Test
    public void whenUsingFormattedDateTimeMatcher_thenFormatConstraintsSatisfied() {
        Assert.assertTrue(matcher.matches("2017-12-31 01:22:33"));
        Assert.assertTrue(matcher.matches("2018-01-01 01:03:33"));
        Assert.assertTrue(matcher.matches("0000-00-00 10:22:59"));
        Assert.assertTrue(matcher.matches("1029-99-72 01:22:00"));

        Assert.assertFalse(matcher.matches("2018-01"));
        Assert.assertFalse(matcher.matches("2018-01-01-01"));
        Assert.assertFalse(matcher.matches("2018-01-XX"));
        Assert.assertFalse(matcher.matches(" 2018-01-01"));
        Assert.assertFalse(matcher.matches("2018-01-01 "));
        Assert.assertFalse(matcher.matches("2018/01/01"));
    }
}

