package com.example.codeforcesandroidapp.network.models.profile.recentSubmissions

import com.example.codeforcesandroidapp.network.models.problems.CodeforcesProblemNetworkModel

data class RecentSubmissionsNetworkModel(
    val id: Long,
    val contestId: Long,
    val creationTimeSeconds: Long,
    val problem: CodeforcesProblemNetworkModel,
    val verdict: String,
    val programmingLanguage: String,
    val relativeTimeSeconds: Long,
    val timeConsumedMillis: Long,
    val memoryConsumedBytes: Long,
    val passedTestCount: Int
)
