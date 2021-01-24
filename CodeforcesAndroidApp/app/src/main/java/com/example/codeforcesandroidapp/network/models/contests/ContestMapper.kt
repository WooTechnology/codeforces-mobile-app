package com.example.codeforcesandroidapp.network.models.contests

import com.example.codeforcesandroidapp.model.contests.ContestBusinessModel
import com.example.codeforcesandroidapp.network.EntityMapper

class ContestMapper: EntityMapper<ContestNetworkModel,ContestBusinessModel> {

    override fun fromNetworkModeltoBusinessModel(networkModel: ContestNetworkModel): ContestBusinessModel {
        return ContestBusinessModel(
            networkModel.id,
            networkModel.name,
            networkModel.type,
            networkModel.phase,
            networkModel.durationSeconds,
            networkModel.startTimeSeconds
        )
    }

    override fun fromNetworkModelListtoBusinessModelList(networkModelList: List<ContestNetworkModel>): List<ContestBusinessModel> {

        val contestList = ArrayList<ContestBusinessModel>()

        networkModelList.forEach {
            contestList.add(fromNetworkModeltoBusinessModel(it))
        }

        return contestList
    }

}