package com.example.demonav.ui.navigate.args

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UpdateArgs(
    val userId: String,
    val userName: String,
    val email: String
) : Parcelable
