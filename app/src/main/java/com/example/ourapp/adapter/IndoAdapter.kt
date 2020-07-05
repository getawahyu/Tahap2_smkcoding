package com.example.ourapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ourapp.Attributes
import com.example.ourapp.DataIndonesia
import com.example.ourapp.DataIndonesiaItem
import com.example.ourapp.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.user_item.*

class IndoAdapter(private val context: Context, private val items:
List<DataIndonesiaItem>, private val listener: (Attributes)-> Unit) :
    RecyclerView.Adapter<IndoAdapter.ViewHolder>() {

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
        holder.bindItem(items.get(position).attributes, listener)
    }

    class ViewHolder(val context: Context, override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(item: Attributes, listener: (Attributes) -> Unit) {
            username.text = item.provinsi
            userRepo.text = item.kasusPosi.toString()
//            Glide.with(context).load(item.avatarUrl).into(imgUser)
//            containerView.setOnClickListener { listener(item) }
        }
    }
}