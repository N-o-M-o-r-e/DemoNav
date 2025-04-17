package com.example.demonav.ui.navigate.result

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UpdateResult(
    val name: String,
    val email: String
) : Parcelable{
    companion object{
        const val KEY = "UpdateResult"
    }
}
