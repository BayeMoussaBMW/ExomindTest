package com.example.exomindtest.room

import androidx.room.TypeConverter
import com.example.exomindtest.model.Geo
import org.json.JSONObject

class GeoTypeConverter {
    @TypeConverter
    fun fromGeo(geo: Geo): String {
        return JSONObject().apply {
            putOpt("lat", geo.lat)
            putOpt("lng", geo.lng)
        }.toString()
    }

    @TypeConverter
    fun toGeo(geo: String): Geo {
        val json = JSONObject(geo)
        return Geo(json.getString("lat"),json.getString("lng"))
    }
}