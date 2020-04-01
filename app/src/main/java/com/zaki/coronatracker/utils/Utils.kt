package com.zaki.coronatracker.utils

import android.app.Activity
import android.view.inputmethod.InputMethodManager

/**
 * Developer : Mohammad Zaki
 * Created On : 10-03-2020
 */

object Utils {
    /**
     * Method used to hide keyboard if outside touched.
     *
     * @param activity
     */
    fun hideSoftKeyboard(activity: Activity) {
        val focusedView = activity.currentFocus ?: return
        val windowToken = focusedView.windowToken ?: return
        val inputMethodManager =
            activity.getSystemService(
                Activity.INPUT_METHOD_SERVICE
            ) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    }
}