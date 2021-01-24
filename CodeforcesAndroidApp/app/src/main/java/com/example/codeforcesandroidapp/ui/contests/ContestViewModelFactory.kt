package com.example.codeforcesandroidapp.ui.contests

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.codeforcesandroidapp.repository.contests.ContestsRepository

class ContestViewModelFactory(private val contestRepository:ContestsRepository): ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(ContestsRepository::class.java).newInstance(contestRepository)
    }
}