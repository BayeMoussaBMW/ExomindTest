package com.example.exomindtest.model

import com.google.gson.annotations.SerializedName
import javax.inject.Inject


data class Geo
@Inject
constructor(
    @SerializedName("lat")
    val lat: String,
    @SerializedName("lng")
    val lng: String
){

    override fun toString() = lat + lng
}