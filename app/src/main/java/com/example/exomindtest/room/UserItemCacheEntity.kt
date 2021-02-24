package com.example.exomindtest.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.exomindtest.model.Address
import com.example.exomindtest.model.Company
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "useritem")
class UserItemCacheEntity (
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    @Expose
    var id: String,

    @SerializedName("name")
    @Expose
    var name: String,

    @SerializedName("username")
    @Expose
    val username: String,

    @SerializedName("email")
    @Expose
    var email: String,

    @SerializedName("address")
    @Expose
    var address: Address,

    @SerializedName("phone")
    @Expose
    val phone: String,

    @SerializedName("website")
    @Expose
    val website: String,

    @SerializedName("company")
    @Expose
    var company: Company

)