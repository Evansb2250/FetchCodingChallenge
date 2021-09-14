package com.example.android.fetchproject.fetchDataTransfertObject

import com.example.android.fetchproject.domain.DomainAccount
import java.util.*
import kotlin.collections.ArrayList

data class DataTransferObjectAccount(
    val id: Int,
    val listId: Int,
    val name: String?
)


fun List<DataTransferObjectAccount>.transformDataObjectToDomainObject(): List<DomainAccount> {
    return map {
        DomainAccount(
            id= it.id,
            listId = it.listId,
            name = it.name!!
        )
    }
}


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


