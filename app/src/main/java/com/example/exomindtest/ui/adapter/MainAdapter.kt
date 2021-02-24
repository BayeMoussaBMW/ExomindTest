package com.example.exomindtest.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exomindtest.R
import com.example.exomindtest.model.UserItem
import com.example.exomindtest.util.DataState
import com.example.exomindtest.util.Resource
import kotlinx.android.synthetic.main.item_layout.view.*

class MainAdapter(private val users: ArrayList<UserItem>) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(userItem: UserItem) {
            itemView.apply {
                textViewName.text = userItem.name
                textViewUsername.text = userItem.username
                textViewEmail.text = userItem.email
                textViewPhone.text = userItem.phone
                textViewWebsite.text = userItem.website
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false))

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(users[position])
    }

    fun addUsers(users: List<UserItem>) {
        this.users.apply {
            clear()
            addAll(users)
        }

    }
}