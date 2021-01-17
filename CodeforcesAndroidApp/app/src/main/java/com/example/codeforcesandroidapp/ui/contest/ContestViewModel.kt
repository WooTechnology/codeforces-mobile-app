package com.example.codeforcesandroidapp.ui.contest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codeforcesandroidapp.models.ContestModel
import com.example.codeforcesandroidapp.repo.contest.ContestRepository
import com.example.codeforcesandroidapp.repo.contest.ContestRepository_Impl
import kotlinx.coroutines.launch

class ContestViewModel(private val contestRepository: ContestRepository): ViewModel() {
    var contestList = MutableLiveData<List<ContestModel>>()

    fun fetchContest(){
        viewModelScope.launch {
            contestRepository.fetchContests {
                contestList.value = it
            }
        }
    }
}