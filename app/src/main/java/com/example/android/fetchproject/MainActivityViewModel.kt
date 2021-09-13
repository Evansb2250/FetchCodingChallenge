package com.example.android.fetchproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.android.fetchproject.domain.DomainAccount
import com.example.android.fetchproject.fetchDataTransfertObject.DataTransferObjectAccount
import com.example.android.fetchproject.fetchDataTransfertObject.transformDataObjectToDomainObject
import com.example.android.fetchproject.repository.Repository
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

val lengthThenNatural = compareBy<String> { it.length }.then(naturalOrder())


class MainActivityViewModel : ViewModel() {

    private val repository = Repository()

    val listToShowUsers: LiveData<List<DomainAccount>> = Transformations.map(repository.mapOfAccountData) { partionedMap ->
        sort(partionedMap)
    }


    fun sort(listOfAccountsInMap: HashMap<Int, ArrayList<DataTransferObjectAccount>>): List<DomainAccount> {
        val sortedAccounts = ArrayList<DomainAccount>()
        //O(K * N)
        for (key in listOfAccountsInMap.keys) {
            //checks to see if the list instance is null or not
            listOfAccountsInMap.get(key)?.let { dataTransferObjectList ->
                //uses extension function (i created) to transform data to from FetchAccountDTO to FetchDomainObject
                val domainObjectListOfAccounts = dataTransferObjectList.transformDataObjectToDomainObject()
                // creates a temp map
                val tempMap = HashMap<String, DomainAccount>()
                // adds the value of each FetchDomainAccount to a map
                domainObjectListOfAccounts.forEach { entry -> tempMap.put(entry.name, entry) }
                //gets the keys of the map and sorts it using natural order
                val sortedKeys = tempMap.keys.sortedWith(lengthThenNatural)
                //traverse through sorted keys and adds the instance of FetchDomainAccount in the natural order
                sortedKeys.forEach { key -> sortedAccounts.add(tempMap.get(key)!!) }
            }
        }
        //returns a list of all the FetchDomainAccounts in natural order
        return sortedAccounts
    }



    init {
        repository.getData()
    }
}




