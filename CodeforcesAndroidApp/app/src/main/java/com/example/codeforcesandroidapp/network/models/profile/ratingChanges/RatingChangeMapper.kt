package com.example.codeforcesandroidapp.network.models.profile.ratingChanges


import com.example.codeforcesandroidapp.model.profile.RatingChangeBusinessModel
import com.example.codeforcesandroidapp.network.EntityMapper

class RatingChangeMapper: EntityMapper<RatingChangeNetworkModel, RatingChangeBusinessModel> {

    override fun fromNetworkModeltoBusinessModel(networkModel: RatingChangeNetworkModel) : RatingChangeBusinessModel{
        return RatingChangeBusinessModel(
            networkModel.contestId,
            networkModel.contestName,
            networkModel.rank,
            networkModel.ratingUpdateTimeSeconds,
            networkModel.oldRating,
            networkModel.newRating
        )
    }

    override fun fromNetworkModelListtoBusinessModelList(networkModelList : List<RatingChangeNetworkModel>) : List<RatingChangeBusinessModel>{

        val ratingChangeList = ArrayList<RatingChangeBusinessModel>()

        networkModelList.forEach {
            ratingChangeList.add(fromNetworkModeltoBusinessModel(it))
        }

        return ratingChangeList
    }
}