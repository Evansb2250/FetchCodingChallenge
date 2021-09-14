package com.example.android.fetchproject.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.android.fetchproject.R
import com.example.android.fetchproject.domain.DomainAccount

class AccountAdapter(val listOfData: List<DomainAccount>): Adapter<AccountAdapter.AccountViewHolder>(){

    class AccountViewHolder(item: View): RecyclerView.ViewHolder(item){
        lateinit var idTextView : TextView
        lateinit var idListTextView : TextView
        lateinit var nameTextView : TextView


        init {
             idTextView = item.findViewById<TextView>(R.id.idTextView)
             idListTextView = item.findViewById<TextView>(R.id.listIdTextView)
             nameTextView = item.findViewById<TextView>(R.id.nameTextView)
        }
        fun bind(account: DomainAccount){
            idTextView.text = account.id.toString()
            idListTextView.text = account.listId.toString()
            nameTextView.text = account.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder, parent, false)
        return AccountViewHolder(view)
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        val item = listOfData.get(position)
        holder.bind(item)
    }

    override fun getItemCount():Int = listOfData.size
}