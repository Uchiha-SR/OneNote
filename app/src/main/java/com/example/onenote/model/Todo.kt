package com.example.onenote.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Todo(
    val title: String = "",
    val isChecked: Boolean = false,
): Parcelable
