package com.example.codeforcesandroidapp.network.services

import com.example.codeforcesandroidapp.network.models.contests.ContestResponse
import com.example.codeforcesandroidapp.network.models.profile.RatingResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET ("contest.list")
    fun fetchContestList(@Query ("gym") gym:Boolean = false): Call<ContestResponse>

    @GET ("user.rating")
    fun fetchRatingChangesList(@Query("handle") handle:String = "khushboo"): Call<RatingResponse>
}