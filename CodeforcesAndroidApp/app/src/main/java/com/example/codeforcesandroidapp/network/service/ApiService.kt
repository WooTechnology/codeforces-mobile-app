package com.example.codeforcesandroidapp.network.service

import com.example.codeforcesandroidapp.network.models.contest.ContestResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("contest.list")
    fun fetchContestList(@Query("gym") gym: Boolean = false) : Call<ContestResponse>
}