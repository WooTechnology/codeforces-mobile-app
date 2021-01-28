package com.example.codeforcesandroidapp.repository.profile

import android.util.Log
import com.example.codeforcesandroidapp.model.profile.RatingChangeBusinessModel
import com.example.codeforcesandroidapp.network.models.profile.RatingChangeMapper
import com.example.codeforcesandroidapp.network.models.profile.RatingResponse
import com.example.codeforcesandroidapp.network.services.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RatingChangesRepository_Impl(private val apiService: ApiService, private val ratingChangesMapper: RatingChangeMapper) : RatingChangesRepository {

    override suspend fun fetchratingchanges(callback: (List<RatingChangeBusinessModel>) -> Unit) {

        withContext(Dispatchers.IO){
            apiService.fetchRatingChangesList().enqueue(object: Callback<RatingResponse> {
                override fun onResponse(
                    call: Call<RatingResponse>,
                    response: Response<RatingResponse>
                ) {
                    if(response.isSuccessful && response.body()!=null){

                        callback(ratingChangesMapper.fromNetworkModelListtoBusinessModelList(response.body()!!.result!!))
                    }
                }

                override fun onFailure(call: Call<RatingResponse>, t: Throwable) {
                    Log.e("RatingFetchFailed",t.toString())
                }
            })
        }

    }
}