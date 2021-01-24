package com.example.codeforcesandroidapp.network

interface EntityMapper<NetworkModel, BusinessModel> {
    fun fromNetworkModeltoBusinessModel(networkModel: NetworkModel): BusinessModel
    fun fromNetworkModelListtoBusinessModelList(networkModelList: List<NetworkModel>): List<BusinessModel>
}