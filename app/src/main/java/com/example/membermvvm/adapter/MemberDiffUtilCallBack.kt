package com.example.membermvvm.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.membermvvm.data.Data


class MemberDiffUtilCallBack: DiffUtil.ItemCallback<Data>() {
    override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem.name == newItem.name
                && oldItem.address == newItem.address

    }
}