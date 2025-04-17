package com.example.demonav.ui.navigate.result

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

const val RESULT = "result"

inline fun <reified R> Fragment.setFragmentResult(
    key: String,
    result: R
) {
    requireActivity().supportFragmentManager.setFragmentResult(
        key,
        bundleOf(RESULT to result)
    )
}

fun Fragment.removeFragmentResult(key: String) {
    requireActivity().supportFragmentManager.clearFragmentResult(key)
}

inline fun <reified R : Parcelable> Bundle.getFragmentResult(): R? {
    return kotlin.runCatching {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            getParcelable(RESULT, R::class.java)
        } else getParcelable<R>(RESULT)
    }.getOrNull()
}

inline fun <reified R : Parcelable> Fragment.setFragmentResultListener(
    requestKey: String,
    crossinline listener: (result: R) -> Unit
) {
    (requireActivity() as AppCompatActivity).supportFragmentManager
        .setFragmentResultListener(
            requestKey,
            requireActivity()
        ) { _, bundle ->
            bundle.getFragmentResult<R>()?.let(listener)
        }
}