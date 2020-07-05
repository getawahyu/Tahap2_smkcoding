package com.example.ourapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ourapp.Attributes
import com.example.ourapp.DataIndonesiaItem
import com.example.ourapp.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.inform_item.*

class InformAdapter(private val context: Context, private val items:
List<DataIndonesiaItem>, private val listener: (Attributes)-> Unit) :
    RecyclerView.Adapter<InformAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            context, LayoutInflater.from(context).inflate(
                R.layout.inform_item,
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
            provinsi.text = item.provinsi
            positif.text = item.kasusPosi.toString()
            sembuh.text = item.kasusSemb.toString()
            meninggal.text = item.kasusMeni.toString()
        }
    }
}