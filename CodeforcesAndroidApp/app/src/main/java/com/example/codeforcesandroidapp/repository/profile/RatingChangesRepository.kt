package com.example.codeforcesandroidapp.repository.profile

import com.example.codeforcesandroidapp.model.profile.RatingChangeBusinessModel

interface RatingChangesRepository {

    suspend fun fetchratingchanges(callback: (List<RatingChangeBusinessModel>) -> Unit)
}