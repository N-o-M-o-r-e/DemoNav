package com.example.demonav.ui.navigate.args

import androidx.lifecycle.SavedStateHandle

inline fun <reified T> SavedStateHandle.getArgs(): T? {
    return keys().firstNotNullOfOrNull { key ->
        runCatching { get<T>(key) }.getOrNull()
    }
}