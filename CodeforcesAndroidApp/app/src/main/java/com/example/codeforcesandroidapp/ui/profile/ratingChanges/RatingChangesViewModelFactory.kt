package com.example.codeforcesandroidapp.ui.profile.ratingChanges

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.codeforcesandroidapp.repository.contests.ContestsRepository
import com.example.codeforcesandroidapp.repository.profile.RatingChangesRepository

class RatingChangesViewModelFactory(private val ratingChangesRepo : RatingChangesRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(RatingChangesRepository::class.java).newInstance(ratingChangesRepo)
    }

}