package com.example.android.fetchproject

import androidx.lifecycle.LiveData
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

    private val repository = Repository()

    val listToShowUsers: LiveData<List<DomainAccount>> = Transformations.map(repository.mapOfAccountData) { partionedMap ->
        sort(partionedMap)
    }


    fun sort(listOfAccountsInMap: HashMap<Int, ArrayList<DataTransferObjectAccount>>): List<DomainAccount> {
        val sortedAccounts = ArrayList<DomainAccount>()
        //O(K * N)
        for (key in listOfAccountsInMap.keys.sorted()) {
            //checks to see if the list instance is null or not
            listOfAccountsInMap.get(key)?.let { dataTransferObjectList ->
                //uses extension function (i created) to transform data to from FetchAccountDTO to FetchDomainObject
                val domainObjectListOfAccounts = dataTransferObjectList.transformDataObjectToDomainObject()
                //Sort the list
                Collections.sort(domainObjectListOfAccounts)
               //add to the dataStructure
                domainObjectListOfAccounts.forEach{it -> sortedAccounts.add(it)}
            }
        }
        //returns a list of all the FetchDomainAccounts in natural order
        return sortedAccounts
    }



    init {
        repository.getData()
    }
}




