package com.example.codeforcesandroidapp.network

interface EntityMapper<NetworkModel, BusinessModel> {
    fun fromNetworkModelToBusinessModel(networkModel: NetworkModel): BusinessModel
    fun fromNetworkModelListToBusinessModelList(networkModels: List<NetworkModel>): List<BusinessModel>
}