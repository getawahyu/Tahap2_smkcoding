package com.example.ourapp.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ourapp.Home
import com.example.ourapp.MyModel
import com.example.ourapp.R
import com.example.ourapp.util.tampilToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.people_item.*

class DataAdapter (
    private val context: Context,
    private var list: List<MyModel>
) :
    RecyclerView.Adapter<DataAdapter.ViewHolder>() {

    var listener: dataListener? = null
    private var auth: FirebaseAuth? = null
    lateinit var ref: DatabaseReference

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(context).inflate(R.layout.people_item, parent, false)
    )

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(list.get(position))

        holder.itemView.setOnLongClickListener(View.OnLongClickListener { view ->
            val action = arrayOf("Update", "Delete")
            val alert = AlertDialog.Builder(view.context)
            alert.setItems(action) { dialog, i ->
                when (i) {
                    0 -> {
                        val bundle = Bundle()
                        bundle.putString("dataNama", list.get(position).nama)
                        bundle.putString("dataTelp", list.get(position).telp)
                        bundle.putString("dataAlamat", list.get(position).alamat)
                        bundle.putString("dataRiwayat", list.get(position).riwayat)
                        bundle.putString("getPrimaryKey", list.get(position).key)
                        val intent = Intent(view.context, Home::class.java)
                        intent.putExtras(bundle)
                        context.startActivity(intent)
                    }
                    1 -> {
                        auth = FirebaseAuth.getInstance()
                        ref = FirebaseDatabase.getInstance().getReference()
                        val getUserID: String = auth?.getCurrentUser()?.getUid().toString()
                        if (ref != null) {
                            ref.child("data")
//                                .child("Teman")
                                .child(list.get(position)?.key.toString())
                                .removeValue()
                                .addOnSuccessListener {
                                    tampilToast(view.context, "Data Berhasil Dihapus")
                                }
                        }
                    }
                }
            }
            alert.create()
            alert.show()
            true
        })
    }

    class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        fun bindItem(item: MyModel) {
            nama.text = item.nama
            telp.text = item.telp
            alamat.text = item.alamat
            riwayat.text = item.riwayat
        }
    }

    fun setData(list: List<MyModel>) {
        this.list = list
        notifyDataSetChanged()
    }

    interface dataListener {
        fun onDeleteData(data: MyModel, position: Int)
    }
}
