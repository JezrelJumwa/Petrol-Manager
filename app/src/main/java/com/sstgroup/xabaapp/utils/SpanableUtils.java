package com.sstgroup.xabaapp.utils;

import android.text.Spannable;
import android.text.SpannableString;

public class SpanableUtils {

    public static Spannable boldSpan(String text, int start, int end){
        Spannable str = new SpannableString(text);
        str.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        str.setSpan(new ForegroundColorSpan(Color.BLUE), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return str;
    }
}
