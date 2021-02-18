package com.example.codeforcesandroidapp.network.services

import com.example.codeforcesandroidapp.network.models.contests.ContestResponse
import com.example.codeforcesandroidapp.network.models.profile.ratingChanges.RatingResponse
import com.example.codeforcesandroidapp.network.models.profile.recentSubmissions.SubmissionsResponse
import com.example.codeforcesandroidapp.network.models.profile.userProfile.UserProfileResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET ("contest.list")
    fun fetchContestList(@Query ("gym") gym:Boolean = false): Call<ContestResponse>

    @GET ("user.rating")
    fun fetchRatingChangesList(@Query("handle") handle:String): Call<RatingResponse>

    @GET("user.status")
    fun fetchRecentSubmissionsList(
        @Query("handle") handle:String,
        @Query("from") from:Int = 1,
        @Query("count") count:Int = 10
    ) : Call<SubmissionsResponse>

    @GET("user.info")
    fun fetchUserDetails(
        @Query("handles") handle: String
    ) : Call<UserProfileResponse>
}