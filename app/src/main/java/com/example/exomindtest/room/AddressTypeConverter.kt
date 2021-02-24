package com.example.exomindtest.room

import androidx.room.TypeConverter
import com.example.exomindtest.model.Address
import org.json.JSONObject

class AddressTypeConverter {
    @TypeConverter
    fun fromAddress(address: Address): String {
        return JSONObject().apply {
            put("street", address.street)
            put("suite", address.suite)
            put("city", address.city)
            put("zipcode", address.zipcode)
            putOpt("geo", address.geo)
        }.toString()
    }

    @TypeConverter
    fun toAddress(address: String): Address {
        val json = JSONObject(address)
        return Address(json.getString("street"), json.getString("suite"), json.getString("city"), json.getString("zipcode"), json.getString("geo"))
    }
}

