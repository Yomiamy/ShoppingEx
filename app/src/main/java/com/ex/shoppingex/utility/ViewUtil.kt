package com.ex.shoppingex.utility

import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import kotlin.math.min


object ViewUtil {

    fun highlightText(origStr: String, keyword: String, highlightedColor: Int): Spannable {
        var start = origStr.indexOf(keyword)

        if (start < 0 || keyword.isNullOrEmpty()) {
            return SpannableString(origStr)
        } else {
            val highlightedSpannable: Spannable = SpannableString(origStr)

            while (start >= 0) {
                val spanStart = min(start, origStr.length)
                val spanEnd = min(start + keyword.length, origStr.length)

                highlightedSpannable.setSpan(ForegroundColorSpan(highlightedColor),
                    spanStart,
                    spanEnd,
                    Spanned.SPAN_INCLUSIVE_EXCLUSIVE)

                start = origStr.indexOf(keyword, spanEnd)
            }
            return highlightedSpannable
        }
    }
}