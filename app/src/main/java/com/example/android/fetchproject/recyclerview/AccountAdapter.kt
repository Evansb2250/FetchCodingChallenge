package com.example.android.fetchproject.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.android.fetchproject.R
import com.example.android.fetchproject.domain.DomainAccount

class AccountAdapter:  ListAdapter<DomainAccount, AccountAdapter.AccountViewHolder>(AccountDiffUtil()){

    class AccountViewHolder(item: View): RecyclerView.ViewHolder(item){
        var idListTextView : TextView
        var nameTextView : TextView


        fun bind(account: DomainAccount){
            idListTextView.text = account.listId.toString()
            nameTextView.text = account.name
        }

        init {
            idListTextView = item.findViewById<TextView>(R.id.listIdTextView)
            nameTextView = item.findViewById<TextView>(R.id.nameTextView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder, parent, false)
        return AccountViewHolder(view)
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

}



