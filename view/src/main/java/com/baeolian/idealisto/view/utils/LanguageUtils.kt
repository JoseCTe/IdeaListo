package com.baeolian.idealisto.view.utils

inline fun <T1, T2, R> allOrNull(o1: T1?, o2: T2?, block: (T1, T2) -> R): R? {
    return if (o1 != null && o2 != null) {
        block(o1, o2)
    } else {
        null
    }
}
