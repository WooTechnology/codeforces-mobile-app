package com.example.codeforcesandroidapp.ui.contest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.codeforcesandroidapp.repo.contest.ContestRepository
import com.example.codeforcesandroidapp.repo.contest.ContestRepository_Impl

class ContestViewModelFactory(private val contestRepository: ContestRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(ContestRepository::class.java).newInstance(contestRepository)
    }
}