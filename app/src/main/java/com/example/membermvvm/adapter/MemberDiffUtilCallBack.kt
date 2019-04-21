package com.example.membermvvm.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.membermvvm.data.Member


class MemberDiffUtilCallBack: DiffUtil.ItemCallback<Member>() {
    override fun areItemsTheSame(oldItem: Member, newItem: Member): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Member, newItem: Member): Boolean {
        return oldItem.name == newItem.name
                && oldItem.address == newItem.address

    }
}