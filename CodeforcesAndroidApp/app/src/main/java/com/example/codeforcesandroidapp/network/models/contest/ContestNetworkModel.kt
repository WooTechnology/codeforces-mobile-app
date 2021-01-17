package com.example.codeforcesandroidapp.network.models.contest

data class ContestNetworkModel(
    val id: Int,
    val name: String,
    val type: String,
    val phase: String,
    val frozen: Boolean?,
    val durationSeconds: Int,
    val startTimeSeconds: Int,
    val description: String,
    val difficulty: Int?,
    val kind: String?,
    val season: String?
)
