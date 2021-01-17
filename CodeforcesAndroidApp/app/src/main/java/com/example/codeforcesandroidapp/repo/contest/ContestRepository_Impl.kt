package com.example.codeforcesandroidapp.repo.contest

import android.util.Log
import com.example.codeforcesandroidapp.models.ContestModel
import com.example.codeforcesandroidapp.network.models.contest.ContestMapper
import com.example.codeforcesandroidapp.network.models.contest.ContestResponse
import com.example.codeforcesandroidapp.network.service.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ContestRepository_Impl(private val apiService: ApiService, private val contestMapper: ContestMapper): ContestRepository {

    override suspend fun fetchContests(callback: (List<ContestModel>) -> Unit) {
        withContext(Dispatchers.IO){
            apiService.fetchContestList().enqueue(object: Callback<ContestResponse>{
                override fun onResponse(
                    call: Call<ContestResponse>,
                    response: Response<ContestResponse>
                ) {
                    if(response.isSuccessful && response.body()!=null){
                        callback(contestMapper.fromNetworkModelListToBusinessModelList(response.body()!!.result!!))
                    }
                }

                override fun onFailure(call: Call<ContestResponse>, t: Throwable) {
                    Log.e("ContestFetchFailed", t.toString())
                }

            })
        }
    }

}