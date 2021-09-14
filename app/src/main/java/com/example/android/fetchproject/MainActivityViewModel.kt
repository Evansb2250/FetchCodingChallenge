package com.example.android.fetchproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.android.fetchproject.domain.DomainAccount
import com.example.android.fetchproject.fetchDataTransfertObject.DataTransferObjectAccount
import com.example.android.fetchproject.fetchDataTransfertObject.transformDataObjectToDomainObject
import com.example.android.fetchproject.repository.Repository
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class MainActivityViewModel : ViewModel() {

    var showListIDOne:Boolean
    var showListIDTwo: Boolean
    var showListIDThree :Boolean
    var showListIDFour: Boolean


    private val repository = Repository()

     val completeListOfUsers: LiveData<List<DomainAccount>> = Transformations.map(repository.mapOfAccountData) { partionedMap ->
            sort(partionedMap)
        }

    private val _listToShowUsers = MutableLiveData<List<DomainAccount>>()
    val listToShowUsers: LiveData<List<DomainAccount>> get() = _listToShowUsers


    fun filter() {
        var keysToShow = mutableListOf<Int>()
        if (showListIDOne) keysToShow.add(1)
        if (showListIDTwo) keysToShow.add(2)
        if (showListIDThree) keysToShow.add(3)
        if (showListIDFour) keysToShow.add(4)

        println(" key  ${keysToShow}")
        updateDisplay(keysToShow)
    }


    private fun updateDisplay(keysToShow: MutableList<Int>) {
        val newList = ArrayList<DomainAccount>()
        completeListOfUsers.value?.forEach {
            if (keysToShow.contains(it.listId))
                newList.add(it)
        }

        println(" new list ${newList}  ${completeListOfUsers.value.toString()} ")
        _listToShowUsers.value = newList
    }


    fun sort(listOfAccountsInMap: HashMap<Int, ArrayList<DataTransferObjectAccount>>): List<DomainAccount> {
        val sortedAccounts = ArrayList<DomainAccount>()
        //O(K * N)
        for (key in listOfAccountsInMap.keys.sorted()) {
            //checks to see if the list instance is null or not
            listOfAccountsInMap.get(key)?.let { dataTransferObjectList ->
                //uses extension function (i created) to transform data to from FetchAccountDTO to FetchDomainObject
                val domainObjectListOfAccounts =
                    dataTransferObjectList.transformDataObjectToDomainObject()
                //Sort the list
                Collections.sort(domainObjectListOfAccounts)
                //add to the dataStructure
                domainObjectListOfAccounts.forEach { it -> sortedAccounts.add(it) }
            }
        }
        //returns a list of all the FetchDomainAccounts in natural order
        return sortedAccounts
    }


    init {
        showListIDOne = false
        showListIDTwo = false
        showListIDThree = false
        showListIDFour = false
        repository.getData()
    }
}




