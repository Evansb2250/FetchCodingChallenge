package com.example.android.fetchproject.network

import com.example.android.fetchproject.fetchDataTransfertObject.DataTransferObjectAccount
import retrofit2.http.GET

interface FetchWebServiceInterface {
    @GET("hiring.json")
    suspend fun getAccountFromWebServer():List<DataTransferObjectAccount>?
}