package com.example.codeforcesandroidapp.network.models.profile

data class RatingChangeNetworkModel(

    val contestId: Int,
    val contestName: String,
    val handle: String,
    val rank: Int,
    val ratingUpdateTimeSeconds: Long,
    val oldRating: Int,
    val newRating: Int
)
