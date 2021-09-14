package com.example.android.fetchproject.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.fetchproject.fetchDataTransfertObject.DataTransferObjectAccount
import com.example.android.fetchproject.fetchDataTransfertObject.createMapOfValidAccounts
import com.example.android.fetchproject.network.FetchWebServerObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Repository {

//   private val _mapOfAccountData= MutableLiveData<HashMap<Int,ArrayList<DataTransferObjectAccount>>>()
//    val mapOfAccountData:LiveData<HashMap<Int, ArrayList<DataTransferObjectAccount>>>get() = _mapOfAccountData

    private val _mapOfAccountData= MutableLiveData<List<DataTransferObjectAccount>>()
    val mapOfAccountData:LiveData<List<DataTransferObjectAccount>>get() = _mapOfAccountData







    fun getData(){
        CoroutineScope(Dispatchers.IO).launch{
           val data = FetchWebServerObject.retrofitService.getAccountFromWebServer()
              // ?.createMapOfValidAccounts()

            data?.let {
                _mapOfAccountData.postValue(it)
            }

        }
    }
}