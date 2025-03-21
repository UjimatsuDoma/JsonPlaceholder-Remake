package com.marcgdiez.jsonplaceholder.business

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Item(val id: String, val title: String, val body: String) : Parcelable