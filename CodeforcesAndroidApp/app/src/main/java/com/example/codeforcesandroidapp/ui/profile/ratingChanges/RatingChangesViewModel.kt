package com.example.codeforcesandroidapp.ui.profile.ratingChanges

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codeforcesandroidapp.model.profile.RatingChangeBusinessModel
import com.example.codeforcesandroidapp.repository.profile.RatingChangesRepository
import kotlinx.coroutines.launch

class RatingChangesViewModel(private val ratingChangesRepo : RatingChangesRepository) : ViewModel() {

    var ratingList = MutableLiveData<List<RatingChangeBusinessModel>>()

    fun fetchRating(){
        viewModelScope.launch {
            ratingChangesRepo.fetchratingchanges {
                ratingList.value = it
            }
        }
    }
}