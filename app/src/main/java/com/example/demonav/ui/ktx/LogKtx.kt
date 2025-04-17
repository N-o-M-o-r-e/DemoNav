package com.example.demonav.ui.ktx

import android.util.Log

fun Any.logMessage(message: String) {
    Log.d("DEBUG_LOG [${this::class.java.simpleName}]", message)
}
