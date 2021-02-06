package com.example.codeforcesandroidapp.repository.profile

import com.example.codeforcesandroidapp.model.profile.RecentSubmissionsBusinessModel

interface RecentSubmissionsRepository {

    suspend fun fetchrecentsubmissions(handle : String, from : Int, callback : (List<RecentSubmissionsBusinessModel>) -> Unit)
}