package com.example.android.fetchproject.recyclerview

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.android.fetchproject.domain.DomainAccount

class AccountAdapter(val listOfData: List<DomainAccount>): Adapter<AccountAdapter.AccountViewHolder>(){

    class AccountViewHolder(item: View): RecyclerView.ViewHolder(item){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}