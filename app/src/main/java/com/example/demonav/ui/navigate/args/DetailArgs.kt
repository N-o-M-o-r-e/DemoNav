package com.example.demonav.ui.navigate.args

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailArgs(
    val userId: String,
    val userName: String
) : Parcelable
