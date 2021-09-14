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
  //  val completeList: LiveData<Map<Int, List<DomainAccount>>>

    private val filter = ArrayList<Int>()
    private val _listToShowUsers = MutableLiveData<ArrayList<DomainAccount>>()
    @RequiresApi(Build.VERSION_CODES.O)
    val listToShowUsers: LiveData<List<DomainAccount>> = Transformations.map(repository.mapOfAccountData) { partionedMap ->
        sort(partionedMap)
    }





    @RequiresApi(Build.VERSION_CODES.O)
    fun sort(listOfAccountsInMap: List<DataTransferObjectAccount>): List<DomainAccount> {
        val timer = Instant.now()
        val sortedItem = listOfAccountsInMap
            .asSequence()
            .filter { item -> !item.name.isNullOrBlank() }
            .sortedBy { it.listId }
            .sortedBy { it.name }
            .groupBy { it.listId }
            .flatMap { k ->
               val flatMod = mutableListOf<DataTransferObjectAccount>()
                    k.value.forEach{ it -> flatMod.add(it) }

                flatMod
            }


        val timer2 = Instant.now()
        println("${Duration.between(timer, timer2)}")
        return sortedItem.transformDataObjectToDomainObject()
    }


    init {
        repository.getData()
    }
}




