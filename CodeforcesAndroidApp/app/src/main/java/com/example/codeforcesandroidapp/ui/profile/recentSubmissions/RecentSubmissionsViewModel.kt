package com.example.codeforcesandroidapp.ui.profile.recentSubmissions

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codeforcesandroidapp.model.profile.RecentSubmissionsBusinessModel
import com.example.codeforcesandroidapp.repository.profile.RecentSubmissionsRepository
import kotlinx.coroutines.launch

class RecentSubmissionsViewModel(private val recentSubmissionsRepo : RecentSubmissionsRepository): ViewModel() {

    var submissionsList = MutableLiveData<List<RecentSubmissionsBusinessModel>>()
    var isLoading : MutableLiveData<Boolean> = MutableLiveData(false)
    var isLastPage : MutableLiveData<Boolean> = MutableLiveData(false)
    var from : MutableLiveData<Int> = MutableLiveData(1)

    fun fetchSubmissions(){

        isLoading.value = true

        viewModelScope.launch {
            recentSubmissionsRepo.fetchrecentsubmissions {
                submissionsList.value = it
                isLastPage.value = true
                isLoading.value = false
            }

        }
    }
}