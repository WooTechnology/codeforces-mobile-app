package com.example.codeforcesandroidapp.repository.profile

import android.util.Log
import com.example.codeforcesandroidapp.model.profile.RecentSubmissionsBusinessModel
import com.example.codeforcesandroidapp.network.models.profile.recentSubmissions.RecentSubmissionsMapper
import com.example.codeforcesandroidapp.network.models.profile.recentSubmissions.SubmissionsResponse
import com.example.codeforcesandroidapp.network.services.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecentSubmissionsRepository_Impl(private val apiService: ApiService, private val recentSubmissionsMapper: RecentSubmissionsMapper) : RecentSubmissionsRepository {

    override suspend fun fetchrecentsubmissions(callback: (List<RecentSubmissionsBusinessModel>) -> Unit) {

        withContext(Dispatchers.IO){
            apiService.fetchRecentSubmissionsList().enqueue(object: Callback<SubmissionsResponse> {
                override fun onResponse(
                    call: Call<SubmissionsResponse>,
                    response: Response<SubmissionsResponse>
                ) {
                    if(response.isSuccessful && response.body()!=null){

                        callback(recentSubmissionsMapper.fromNetworkModelListtoBusinessModelList(response.body()!!.result!!))
                    }
                }

                override fun onFailure(call: Call<SubmissionsResponse>, t: Throwable) {
                    Log.e("SubmissionsFetchFailed",t.toString())
                }
            })
        }

    }

}