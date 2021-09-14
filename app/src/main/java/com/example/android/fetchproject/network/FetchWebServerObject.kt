package com.example.android.fetchproject.network

object FetchWebServerObject {

  private  lateinit var INSTANCE: FetchWebServiceInterface

  // retrofit service
    val retrofitService: FetchWebServiceInterface by lazy {
        getFetchWebService()
    }

    //Creates singleton instance of the webServiceInterface
   private fun getFetchWebService(): FetchWebServiceInterface{
        if(::INSTANCE.isInitialized){
            return INSTANCE
        }else
            INSTANCE = getRetrofitInterfaceInstance()

        return INSTANCE
    }
}

