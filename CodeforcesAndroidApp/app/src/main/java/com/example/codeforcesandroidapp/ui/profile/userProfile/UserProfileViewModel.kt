package com.example.codeforcesandroidapp.ui.profile.userProfile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codeforcesandroidapp.model.profile.UserProfileBusinessModel
import com.example.codeforcesandroidapp.repository.profile.UserProfileRepository
import com.example.codeforcesandroidapp.utils.Constants
import kotlinx.coroutines.launch

class UserProfileViewModel(private val userProfileRepo : UserProfileRepository) : ViewModel() {

    var userProfile : MutableLiveData<List<UserProfileBusinessModel>> = MutableLiveData()
    var isLoading : MutableLiveData<Boolean> = MutableLiveData(false)

    fun fetchProfile(handle : String){
        isLoading.value = true

        viewModelScope.launch {
            userProfileRepo.fetchuserdetails(handle) {
                userProfile.value = it
                isLoading.value = false

            }

        }

    }


}