package com.example.codeforcesandroidapp.ui.profile.ratingChanges

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codeforcesandroidapp.model.profile.RatingChangeBusinessModel
import com.example.codeforcesandroidapp.repository.profile.RatingChangesRepository
import com.example.codeforcesandroidapp.utils.Constants.PAGE_SIZE
import kotlinx.coroutines.launch

class RatingChangesViewModel(private val ratingChangesRepo : RatingChangesRepository) : ViewModel() {

    var ratingList = MutableLiveData<List<RatingChangeBusinessModel>>()
    var isLoading : MutableLiveData<Boolean> = MutableLiveData(false)
    var isLastPage : MutableLiveData<Boolean> = MutableLiveData(false)
    var from : MutableLiveData<Int> = MutableLiveData(1)

    fun fetchRating(handle:String){
        isLoading.value = true

        viewModelScope.launch {
            ratingChangesRepo.fetchratingchanges(handle) {
                ratingList.value = it
                isLoading.value = false
                if(it.isEmpty()) isLastPage.value = true
                else from.value = from.value!! + PAGE_SIZE
            }
        }
    }
}