package com.example.codeforcesandroidapp.network.models.contest

data class ContestResponse(
    val status: String,
    val result: List<ContestNetworkModel>?
)
