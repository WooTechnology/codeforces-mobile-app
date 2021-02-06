package com.example.codeforcesandroidapp.utils

import java.text.SimpleDateFormat
import java.util.*

object Constants {
    const val CODEFORCES_BASE_URL = "https://codeforces.com/api/"
    const val PAGE_SIZE = 10

    fun convertSecondsToHours(seconds:Long) : String{
        var sec = seconds
        val hrs = sec/3600
        sec %= 3600
        val min = sec/60
        return "${String.format("%02d", hrs)}:${String.format("%02d", min)} hours"
    }

    fun convertEpochToStringDate(epoch: Long): String{
        val date = Date(epoch*1000)
        val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy, hh:mm aa", Locale.ENGLISH)
        simpleDateFormat.timeZone = TimeZone.getDefault()
        return simpleDateFormat.format(date)
    }
}