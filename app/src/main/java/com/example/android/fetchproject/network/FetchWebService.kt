package com.example.android.fetchproject.network


import com.example.android.fetchproject.fetchDataTransfertObject.DataTransferObjectAccount
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException

private val fetchUrl ="https://fetch-hiring.s3.amazonaws.com/"

//Moshi
private val moshi = Moshi.Builder()
    .addLast(KotlinJsonAdapterFactory())
    .build()


//Retrofit
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(fetchUrl)
    .build()


fun getRetrofitInterfaceInstance() : FetchWebServiceInterface = retrofit.create(FetchWebServiceInterface::class.java)










