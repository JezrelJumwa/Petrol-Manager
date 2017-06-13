package com.sstgroup.xabaapp.utils;

import android.text.Spannable;
import android.text.SpannableStringBuilder;

/**
 * Created by rosenstoyanov on 6/12/17.
 */

public class SpanableUtils {

    public static String boldSpan(String text, int start, int end){
        SpannableStringBuilder str = new SpannableStringBuilder(text);
        str.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return str.toString();
    }
}
