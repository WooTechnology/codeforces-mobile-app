package com.example.codeforcesandroidapp.model.profile

data class RatingChangeBusinessModel(
    val contestId: Int,
    val contestName: String,
    val rank: Int,
    val ratingUpdateTimeSeconds: Long,
    val oldRating: Int,
    val newRating: Int
)
