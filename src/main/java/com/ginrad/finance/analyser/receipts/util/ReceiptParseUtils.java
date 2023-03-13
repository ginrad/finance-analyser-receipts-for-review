package com.ginrad.finance.analyser.receipts.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ReceiptParseUtils {

    final static List<String> datePatterns = Arrays.asList("yyyy-MM-dd hh:mm:ss");
    // TODO: Replace own set of possible date patterns with a library that supports
    //  a wider variety of matching methods and patterns

    public static Date extractDateTryingMultipleFormats(String sourceText) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat(datePatterns.get(0), Locale.ENGLISH);
        formatter.setTimeZone(TimeZone.getTimeZone("Europe/Vilnius"));
        // TODO: Allow parameterisation of TimeZone according to user preference

        Date date = null;
        int i = 0;
        while (date == null && i < datePatterns.size()) {
            date = formatter.parse(sourceText);
        }

        return date;
    }

    private ReceiptParseUtils() {}

    private static class SingletonHolder {
        public static final ReceiptParseUtils instance = new ReceiptParseUtils();
    }

    public static ReceiptParseUtils getInstance() {
        return ReceiptParseUtils.SingletonHolder.instance;
    }

}
