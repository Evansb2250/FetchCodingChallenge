package com.example.android.fetchproject

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.android.fetchproject.domain.DomainAccount
import com.example.android.fetchproject.fetchDataTransfertObject.DataTransferObjectAccount
import com.example.android.fetchproject.fetchDataTransfertObject.transformDataObjectToDomainObject
import com.example.android.fetchproject.repository.Repository
import java.time.Duration
import java.time.Instant
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class MainActivityViewModel : ViewModel() {


    private val repository = Repository()
    @RequiresApi(Build.VERSION_CODES.O)
    val completeList: LiveData<Map<Int, List<DomainAccount>>> = Transformations.map(repository.mapOfAccountData) { partionedMap ->
            sort(partionedMap)
        }


    private val filter = ArrayList<Int>()
    private val _listToShowUsers = MutableLiveData<ArrayList<DomainAccount>>()
    val listToShowUsers: LiveData<ArrayList<DomainAccount>> get() = _listToShowUsers








    fun updateResults() {

    }



    @RequiresApi(Build.VERSION_CODES.O)
    fun sort(listOfAccountsInMap: List<DataTransferObjectAccount>): Map<Int, List<DomainAccount>> {
        val timer = Instant.now()
        val sortedItem = listOfAccountsInMap
            .transformDataObjectToDomainObject()
            .asSequence()
            .filter { item -> !item.name.isNullOrBlank() }
            .sortedBy { it.listId }
            .sortedBy { it.name }
            .groupBy { it.listId }


        val timer2 = Instant.now()
        println("${Duration.between(timer, timer2)}")
        return sortedItem
    }


    init {
        repository.getData()
    }
}




