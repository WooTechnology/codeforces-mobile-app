package com.example.codeforcesandroidapp.model.contests

// to show only these objects of JSON data on the UI

data class ContestBusinessModel(
    val id:Int,
    val name:String,
    val type:String,
    val phase:String,
    val durationSeconds:Int,
    val startTimeSeconds:Int
)