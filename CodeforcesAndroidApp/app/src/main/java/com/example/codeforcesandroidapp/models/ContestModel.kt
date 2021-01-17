package com.example.codeforcesandroidapp.models

data class ContestModel(
    val id: Int,
    val name: String,
    val type: String,
    val phase: String,
    val durationSeconds: Int,
    val startTimeSeconds: Int
)