package com.saulwiggin.breakingbadactormodule.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ActorModel(
    val appearance: List<String>?,
    val id: Int,
    val img: String,
    val name: String,
    val nickname: String,
    val occupation: List<String>,
    val status: String
): Parcelable