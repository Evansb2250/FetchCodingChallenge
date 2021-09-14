package com.example.android.fetchproject.fetchDataTransfertObject

import com.example.android.fetchproject.domain.DomainAccount
import java.security.Key
import java.util.*
import kotlin.collections.ArrayList

data class DataTransferObjectAccount(
    val id: Int,
    val listId: Int,
    val name: String?
)

// Converts an ArrayList of DataTransferObjects into a list of DomainAccounts
fun ArrayList<DataTransferObjectAccount>.transformDataObjectToDomainObject(): List<DomainAccount> {
    return map {
        DomainAccount(
            id = it.id,
            listId = it.listId,
            name = it.name!!
        )
    }
}

// removes blank and null values
// adds Instances in separate arraylist based on listId
// returns a map to the user
fun List<DataTransferObjectAccount>.createMapOfValidAccounts(): HashMap<Int, ArrayList<DataTransferObjectAccount>> {
    //new Map
    val map = HashMap<Int, ArrayList<DataTransferObjectAccount>>()

    this.toList().forEach {
        if (it.name != null && it.name.trim().length > 1) {

            if (!map.containsKey(it.listId))
                map.put(it.listId, ArrayList<DataTransferObjectAccount>().apply { add(it) })
            else
                map.get(it.listId)?.add(it)
        }
    }

    return map
}


