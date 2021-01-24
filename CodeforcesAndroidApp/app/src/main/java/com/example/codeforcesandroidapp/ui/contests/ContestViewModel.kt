package com.example.codeforcesandroidapp.ui.contests

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codeforcesandroidapp.model.contests.ContestBusinessModel
import com.example.codeforcesandroidapp.repository.contests.ContestsRepository
import kotlinx.coroutines.launch

class ContestViewModel(private val contestRepository:ContestsRepository): ViewModel() {

    var contestList = MutableLiveData<List<ContestBusinessModel>>()

    fun fetchContests(){
        viewModelScope.launch{
            contestRepository.fetchcontest {
                contestList.value=it
            }

        }

    }

}