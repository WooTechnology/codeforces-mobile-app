package com.example.codeforcesandroidapp.network.models.profile.userProfile

data class UserProfileResponse(
    val status: String,
    val result: List<UserProfileNetworkModel>?
)
