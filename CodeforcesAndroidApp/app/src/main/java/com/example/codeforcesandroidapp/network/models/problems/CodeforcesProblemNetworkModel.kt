package com.example.codeforcesandroidapp.network.models.problems

data class CodeforcesProblemNetworkModel(
    val contestId: Long,
    val index: String,
    val name:String,
    val type: String,
    val points: Float,
    val rating: Int,
    val tags: List<String>
)
