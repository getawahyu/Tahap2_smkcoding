package com.example.ourapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ourapp.Country
import com.example.ourapp.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.user_item.*

class SummaryAdapter (private val context: Context, private val items:
List<Country>, private val listener: (Country)-> Unit) :
    RecyclerView.Adapter<SummaryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            context, LayoutInflater.from(context).inflate(
                R.layout.user_item,
                parent, false
            )
        )

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items.get(position), listener)
    }

    class ViewHolder(val context: Context, override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(item: Country, listener: (Country) -> Unit) {
            username.text = item.country
            userRepo.text = item.totalConfirmed.toString()
//            Glide.with(context).load(item.avatarUrl).into(imgUser)
//            containerView.setOnClickListener { listener(item) }
        }
    }
}