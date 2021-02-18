package com.example.codeforcesandroidapp.ui.profile.userProfile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.codeforcesandroidapp.repository.profile.UserProfileRepository

class UserProfileViewModelFactory(private val userProfileRepo : UserProfileRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(UserProfileRepository::class.java).newInstance(userProfileRepo)
    }

}