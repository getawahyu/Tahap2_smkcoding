package com.example.ourapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ourapp.adapter.InformAdapter
import com.example.ourapp.data.IndoService
import com.example.ourapp.data.apiRequestIndo
import com.example.ourapp.data.httpClient
import com.example.ourapp.util.dismissLoading
import com.example.ourapp.util.showLoading
import com.example.ourapp.util.tampilToast
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_inform.*
import kotlinx.android.synthetic.main.activity_indonesia.listIndonesia
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Inform : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_inform, container, false)
    }
    override fun onViewCreated(
        view: View,
        @Nullable savedInstanceState: Bundle?
    ){
        super.onViewCreated(view, savedInstanceState)
        callApiGetListIndonesia()
    }

    private fun callApiGetListIndonesia(){
        showLoading(context!!,swipeRefreshLayoutInform)

        val httpClient = httpClient()
        val apiRequest = apiRequestIndo<IndoService>(httpClient)

        val call = apiRequest.getDataIndo()
        call.enqueue(object : Callback<DataIndonesia> {

            override fun onFailure(call: Call<DataIndonesia>, t: Throwable) {
                dismissLoading(swipeRefreshLayoutInform)
            }

            override fun onResponse(
                call: Call<DataIndonesia>,
                response: Response<DataIndonesia>
            ) {
                dismissLoading(swipeRefreshLayoutInform)
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
        listIndonesia.adapter = InformAdapter(context!!, githubUsers) {

            val githubUser = it
            tampilToast(context!!, githubUser.provinsi)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }
}