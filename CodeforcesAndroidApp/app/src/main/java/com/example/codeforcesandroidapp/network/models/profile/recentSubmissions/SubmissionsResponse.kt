package com.example.codeforcesandroidapp.network.models.profile.recentSubmissions

data class SubmissionsResponse(
    val status:String,
    val result: List<RecentSubmissionsNetworkModel>?
)
