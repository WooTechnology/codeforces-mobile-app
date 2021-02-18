package com.example.codeforcesandroidapp.repository.profile

import com.example.codeforcesandroidapp.model.profile.UserProfileBusinessModel

interface UserProfileRepository {

    suspend fun fetchuserdetails(handles : String, callback : (List<UserProfileBusinessModel>)-> Unit )
}