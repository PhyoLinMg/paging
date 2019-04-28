package com.example.membermvvm.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.membermvvm.R
import com.example.membermvvm.data.Data
import com.example.membermvvm.utils.GlideApp
import com.example.membermvvm.utils.inflate
import kotlinx.android.synthetic.main.member_card.view.*


class MemberAdapter(val context: Context) : PagedListAdapter<Data, MemberAdapter.MemberViewHolder>(MemberDiffUtilCallBack())   {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberAdapter.MemberViewHolder {
        return MemberViewHolder(parent.inflate(R.layout.member_card))
    }

    override fun onBindViewHolder(holder: MemberAdapter.MemberViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.member_name.text = item?.title
        GlideApp.with(context)
            .load(item?.profilePic)
            .placeholder(R.drawable.ic_broken_image)
            .fitCenter()
            .into(holder.itemView.member_profile)
    }

    inner class MemberViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}