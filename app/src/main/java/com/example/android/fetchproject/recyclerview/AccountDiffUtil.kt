package com.example.android.fetchproject.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.example.android.fetchproject.domain.DomainAccount


class AccountDiffUtil: DiffUtil.ItemCallback<DomainAccount>(){
    override fun areItemsTheSame(oldItem: DomainAccount, newItem: DomainAccount): Boolean{
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DomainAccount, newItem: DomainAccount): Boolean {
        return oldItem == newItem
    }

}