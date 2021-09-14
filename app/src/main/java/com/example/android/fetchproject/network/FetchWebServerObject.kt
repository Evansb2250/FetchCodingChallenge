package com.example.android.fetchproject.network

object FetchWebServerObject {

  private  lateinit var INSTANCE: FetchWebServiceInterface

    val retrofitService: FetchWebServiceInterface by lazy {
        getFetchWebService()
    }

   private fun getFetchWebService(): FetchWebServiceInterface{
        if(::INSTANCE.isInitialized){
            return INSTANCE
        }else
            INSTANCE = getRetrofitInterfaceInstance()

        return INSTANCE
    }
}

