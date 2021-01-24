package com.example.codeforcesandroidapp.network.models.contests

import com.google.gson.annotations.SerializedName

// to capture the response from the API

data class ContestResponse(
    val status:String,
    @SerializedName("result") val results: List<ContestNetworkModel>?
)