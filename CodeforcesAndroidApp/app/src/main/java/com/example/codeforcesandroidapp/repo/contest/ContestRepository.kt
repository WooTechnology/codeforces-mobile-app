package com.example.codeforcesandroidapp.repo.contest

import com.example.codeforcesandroidapp.models.ContestModel

interface ContestRepository {
    suspend fun fetchContests(callback: (List<ContestModel>) -> Unit)
}