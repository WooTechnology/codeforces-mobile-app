package com.example.codeforcesandroidapp.network.models.profile

data class RatingResponse(
    val status: String,
    val result: List<RatingChangeNetworkModel>?
)
