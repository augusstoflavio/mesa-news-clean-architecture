package com.augusto.mesanews.cleanarchitecture.data.api.adapter

import android.annotation.SuppressLint
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.text.SimpleDateFormat
import java.util.*


object DateAdapter {

    @SuppressLint("SimpleDateFormat")
    private val FORMAT = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")

    @FromJson
    fun dateFromJson(s: String?): Date? {
        if (s == null) {
            return  null
        }

        return FORMAT.parse(s)
    }

    @ToJson
    fun dateToJson(date: Date?): String? {
        if (date == null) {
            return null
        }

        return FORMAT.format(date)
    }
}