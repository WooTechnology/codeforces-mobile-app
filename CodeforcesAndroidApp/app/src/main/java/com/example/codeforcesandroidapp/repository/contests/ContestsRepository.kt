package com.example.codeforcesandroidapp.repository.contests

import com.example.codeforcesandroidapp.model.contests.ContestBusinessModel

interface ContestsRepository {

    suspend fun fetchcontest(callback: (List<ContestBusinessModel>) -> Unit)

}