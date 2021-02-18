package com.example.codeforcesandroidapp.repository.profile

import android.util.Log
import com.example.codeforcesandroidapp.model.profile.UserProfileBusinessModel
import com.example.codeforcesandroidapp.network.models.profile.recentSubmissions.SubmissionsResponse
import com.example.codeforcesandroidapp.network.models.profile.userProfile.UserProfileMapper
import com.example.codeforcesandroidapp.network.models.profile.userProfile.UserProfileResponse
import com.example.codeforcesandroidapp.network.services.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserProfileRepository_Impl(private val apiService: ApiService, private val userProfileMapper : UserProfileMapper) : UserProfileRepository {

    override suspend fun fetchuserdetails( handles: String, callback: (List<UserProfileBusinessModel>) -> Unit) {
        withContext(Dispatchers.IO){
            apiService.fetchUserDetails(handles).enqueue(object: Callback<UserProfileResponse> {
                override fun onResponse(
                    call: Call<UserProfileResponse>,
                    response: Response<UserProfileResponse>
                ) {
                    if(response.isSuccessful && response.body()!=null){
                        callback(userProfileMapper.fromNetworkModelListtoBusinessModelList(response.body()!!.result!!))
                    }
                }

                override fun onFailure(call: Call<UserProfileResponse>, t: Throwable) {
                    Log.e("ProfileFetchFailed",t.toString())
                }
            })
        }
    }

}