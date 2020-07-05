package com.example.ourapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ourapp.DataDeathsItem
import com.example.ourapp.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.death_item.*

class DeathAdapter(private val context: Context, private val items:
List<DataDeathsItem>, private val listener: (DataDeathsItem)-> Unit) :
    RecyclerView.Adapter<DeathAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            context, LayoutInflater.from(context).inflate(
                R.layout.death_item,
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
        fun bindItem(item: DataDeathsItem, listener: (DataDeathsItem) -> Unit) {
            title.text = item.countryRegion
            userRepo.text = item.deaths.toString()
//            Glide.with(context).load(item.avatarUrl).into(imgUser)
//            containerView.setOnClickListener { listener(item) }
        }
    }
}