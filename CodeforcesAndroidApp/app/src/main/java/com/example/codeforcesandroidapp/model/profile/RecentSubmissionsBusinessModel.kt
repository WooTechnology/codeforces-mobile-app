package com.example.codeforcesandroidapp.model.profile

import com.example.codeforcesandroidapp.network.models.problems.CodeforcesProblemNetworkModel

data class RecentSubmissionsBusinessModel(
    val problem: CodeforcesProblemNetworkModel,
    val verdict: String,
    val programmingLanguage: String,
    val passedTestCount: Int,
    val creationTimeSeconds: Long,
    val timeConsumedMillis: Long
)
