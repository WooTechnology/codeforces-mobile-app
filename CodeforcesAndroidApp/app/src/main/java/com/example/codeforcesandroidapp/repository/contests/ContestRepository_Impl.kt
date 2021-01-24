package com.example.codeforcesandroidapp.repository.contests

import android.util.Log
import com.example.codeforcesandroidapp.model.contests.ContestBusinessModel
import com.example.codeforcesandroidapp.network.models.contests.ContestMapper
import com.example.codeforcesandroidapp.network.models.contests.ContestResponse
import com.example.codeforcesandroidapp.network.services.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ContestRepository_Impl (private val apiService:ApiService, private val contestMapper:ContestMapper):ContestsRepository {

    override suspend fun fetchcontest(callback: (List<ContestBusinessModel>) -> Unit) {

        withContext(Dispatchers.IO){
            apiService.fetchContestList().enqueue(object: Callback<ContestResponse>{
                override fun onResponse(
                    call: Call<ContestResponse>,
                    response: Response<ContestResponse>
                ) {
                    if(response.isSuccessful && response.body()!=null){

                        callback(contestMapper.fromNetworkModelListtoBusinessModelList(response.body()!!.results!!))
                    }
                }

                override fun onFailure(call: Call<ContestResponse>, t: Throwable) {
                    Log.e("ContestFetchFailed",t.toString())
                }
            })
        }

    }
}