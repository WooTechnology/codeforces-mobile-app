package com.example.codeforcesandroidapp.ui.profile.recentSubmissions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.codeforcesandroidapp.repository.profile.RecentSubmissionsRepository

class RecentSubmissionsViewModelFactory(private val recentSubmissionsRepo : RecentSubmissionsRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(RecentSubmissionsRepository::class.java).newInstance(recentSubmissionsRepo)
    }
}