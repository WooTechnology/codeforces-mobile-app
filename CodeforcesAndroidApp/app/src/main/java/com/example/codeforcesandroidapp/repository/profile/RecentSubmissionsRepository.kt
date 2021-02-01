package com.example.codeforcesandroidapp.repository.profile

import com.example.codeforcesandroidapp.model.profile.RecentSubmissionsBusinessModel

interface RecentSubmissionsRepository {

    suspend fun fetchrecentsubmissions(callback : (List<RecentSubmissionsBusinessModel>) -> Unit)
}