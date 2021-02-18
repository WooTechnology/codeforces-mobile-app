package com.example.codeforcesandroidapp.network.models.profile.userProfile

import com.example.codeforcesandroidapp.model.profile.UserProfileBusinessModel
import com.example.codeforcesandroidapp.network.EntityMapper

class UserProfileMapper : EntityMapper<UserProfileNetworkModel,UserProfileBusinessModel> {

    override fun fromNetworkModeltoBusinessModel(networkModel: UserProfileNetworkModel): UserProfileBusinessModel {
        return UserProfileBusinessModel(
            networkModel.handle,
            networkModel.rating,
            networkModel.rank,
            networkModel.contribution,
            networkModel.titlePhoto
        )
    }

    override fun fromNetworkModelListtoBusinessModelList(networkModelList: List<UserProfileNetworkModel>): List<UserProfileBusinessModel> {

        val profileList = ArrayList<UserProfileBusinessModel>()

        networkModelList.forEach {
            profileList.add(fromNetworkModeltoBusinessModel(it))
        }

        return profileList
    }
}