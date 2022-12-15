package com.maha.nasatest.data.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Rover(
    val id: Int,
    val landing_date: String,
    val launch_date: String,
    val name: String,
    val status: String
):Parcelable