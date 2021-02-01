package com.example.codeforcesandroidapp.network.models.profile.recentSubmissions

import com.example.codeforcesandroidapp.model.profile.RecentSubmissionsBusinessModel
import com.example.codeforcesandroidapp.network.EntityMapper

class RecentSubmissionsMapper: EntityMapper<RecentSubmissionsNetworkModel, RecentSubmissionsBusinessModel> {
    override fun fromNetworkModeltoBusinessModel(networkModel: RecentSubmissionsNetworkModel): RecentSubmissionsBusinessModel {
        return RecentSubmissionsBusinessModel(
            networkModel.problem,
            networkModel.verdict,
            networkModel.programmingLanguage,
            networkModel.passedTestCount,
            networkModel.creationTimeSeconds,
            networkModel.timeConsumedMillis
        )
    }

    override fun fromNetworkModelListtoBusinessModelList(networkModelList: List<RecentSubmissionsNetworkModel>): List<RecentSubmissionsBusinessModel> {

        val recentSubmissionsList = ArrayList<RecentSubmissionsBusinessModel>()

        networkModelList.forEach {
            recentSubmissionsList.add(fromNetworkModeltoBusinessModel(it))
        }

        return recentSubmissionsList
    }
}