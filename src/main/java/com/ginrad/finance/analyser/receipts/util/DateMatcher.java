package com.ginrad.finance.analyser.receipts.util;

public interface DateMatcher {
    boolean matches(String date);
    String match(String date);

}
