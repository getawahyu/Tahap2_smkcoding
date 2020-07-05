package com.example.ourapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ourapp.adapter.IndoAdapter
import com.example.ourapp.data.IndoService
import com.example.ourapp.data.apiRequestIndo
import com.example.ourapp.data.httpClient
import com.example.ourapp.util.dismissLoading
import com.example.ourapp.util.showLoading
import com.example.ourapp.util.tampilToast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_indonesia.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Indonesia : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_indonesia, container, false)
    }

    override fun onViewCreated(
        view: View,
        @Nullable savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        callApiGetListIndonesia()
        logout()
    }

    private fun logout() {
        logout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(context, login::class.java))
            activity?.finish()
        }
    }

    private fun callApiGetListIndonesia() {
        showLoading(context!!, swipeRefreshLayoutIndonesia)

        val httpClient = httpClient()
        val apiRequest = apiRequestIndo<IndoService>(httpClient)

        val call = apiRequest.getDataIndo()
        call.enqueue(object : Callback<DataIndonesia> {

            override fun onFailure(call: Call<DataIndonesia>, t: Throwable) {
                dismissLoading(swipeRefreshLayoutIndonesia)
            }

            override fun onResponse(
                call: Call<DataIndonesia>,
                response: Response<DataIndonesia>
            ) {
                dismissLoading(swipeRefreshLayoutIndonesia)
                when {
                    response.isSuccessful ->
                        when {
                            response.body()?.size != 0 -> tampilListIndonesia(response.body()!!)
                            else -> {
                                tampilToast(context!!, "Berhasil")
                            }
                        }
                    else -> {
                        tampilToast(context!!, "Gagal")
                    }
                }
            }
        })
    }

    private fun tampilListIndonesia(githubUsers: DataIndonesia) {
        listIndonesia.layoutManager = LinearLayoutManager(context)
        listIndonesia.adapter = IndoAdapter(context!!, githubUsers) {

            val githubUser = it
            tampilToast(context!!, githubUser.provinsi)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }

}
