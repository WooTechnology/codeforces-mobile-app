package com.example.codeforcesandroidapp.network.models.contests

// almost all the parameters that the contest.list endpoint of Codeforces API provides us

data class ContestNetworkModel(
    val id:Int,
    val name:String,
    val type:String,
    val phase:String,
    val frozen:Boolean,
    val durationSeconds:Long,
    val startTimeSeconds:Long,
    val description:String?,
    val difficulty:Int?,
    val kind:String?,
    val season:String?
)