package com.example.exomindtest.model

import com.google.gson.annotations.SerializedName
import javax.inject.Inject


data class Address
@Inject constructor(
    @SerializedName("street")
    val street: String,
    @SerializedName("suite")
    val suite: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("zipcode")
    val zipcode: String,
    @SerializedName("geo")
    val geo: Any
) {

    override fun toString(): String = street + suite + city + zipcode + geo

}
