package com.example.android.fetchproject.domain

import java.util.*

data class DomainAccount(val id: Int ,val listId: Int, val name:String):Comparable<DomainAccount>{


    override fun compareTo(other: DomainAccount): Int {
        //Takes name of the item
        val label = name.substring(0, name.indexOf(" ")).uppercase(Locale.getDefault())
        val label2 = other.name.substring(0, other.name.indexOf(" ")).trim().uppercase(Locale.getDefault())

        //takes the number value from the item
        val labelNumber = name.substring(name.indexOf(" ")  + 1, name.length)
        val label2Number = other.name.substring(other.name.indexOf(" ") + 1, other.name.length).trim()

        //check to see if they both have the same name
        if(label.equals(label2)){
            //check to see if the number values are the same
            return labelNumber.toInt().compareTo(label2Number.toInt())
        }else
            //occurs when the label is not the same
            return label.compareTo(label2)
    }

}



