package com.example.weatherappassessment.core.util

import android.content.res.Resources
import android.util.TypedValue

val Number.toPx
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    ).toInt()


val Long?.orZero
    get() = this ?: 0L

val Double?.orZero
    get() = this ?: 0.0

val Int?.orZero
    get() = this ?: 0

val Float?.orZero
    get() = this ?: 0F

val Boolean?.orFalse
    get() = this ?: false