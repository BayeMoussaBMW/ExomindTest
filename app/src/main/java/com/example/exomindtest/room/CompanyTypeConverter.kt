package com.example.exomindtest.room

import androidx.room.TypeConverter
import com.example.exomindtest.model.Company
import org.json.JSONObject

class CompanyTypeConverter {
    @TypeConverter
    fun fromCompany(company: Company): String {
        return JSONObject().apply {
            put("name", company.name)
            put("catchPhrase", company.catchPhrase)
            put("bs", company.bs)
        }.toString()
    }

    @TypeConverter
    fun toCompany(company: String): Company {
        val json = JSONObject(company)
        return Company( json.getString("name"),json.getString("catchPhrase"), json.getString("bs"))
    }
}
