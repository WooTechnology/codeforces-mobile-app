package com.example.codeforcesandroidapp.utils

object Constants {
    const val CODEFORCES_BASE_URL = "https://codeforces.com/api/"

    fun convertSecondsToHours(seconds:Long) : String{
        var sec = seconds
        val hrs = sec/3600
        sec %= 3600
        val min = sec/60
        return "${String.format("%02d", hrs)}:${String.format("%02d", min)} hours"
    }
}