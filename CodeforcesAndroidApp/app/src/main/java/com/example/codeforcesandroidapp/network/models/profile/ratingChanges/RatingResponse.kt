package com.example.codeforcesandroidapp.network.models.profile.ratingChanges

import com.example.codeforcesandroidapp.network.models.profile.ratingChanges.RatingChangeNetworkModel

data class RatingResponse(
    val status: String,
    val result: List<RatingChangeNetworkModel>?
)
