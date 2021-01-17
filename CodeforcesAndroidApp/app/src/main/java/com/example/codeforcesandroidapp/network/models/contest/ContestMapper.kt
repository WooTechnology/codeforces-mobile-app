package com.example.codeforcesandroidapp.network.models.contest

import com.example.codeforcesandroidapp.models.ContestModel
import com.example.codeforcesandroidapp.network.EntityMapper

class ContestMapper : EntityMapper<ContestNetworkModel, ContestModel> {
    override fun fromNetworkModelToBusinessModel(networkModel: ContestNetworkModel): ContestModel {
        return ContestModel(
            networkModel.id,
            networkModel.name,
            networkModel.type,
            networkModel.phase,
            networkModel.durationSeconds,
            networkModel.startTimeSeconds
        )
    }

    override fun fromNetworkModelListToBusinessModelList(networkModels: List<ContestNetworkModel>): List<ContestModel> {
        val list = ArrayList<ContestModel>()
        networkModels.forEach {
            list.add(fromNetworkModelToBusinessModel(it))
        }
        return list
    }

}