package com.example.ourapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ourapp.adapter.SummaryAdapter
import com.example.ourapp.data.*
import com.example.ourapp.util.dismissLoading
import com.example.ourapp.util.showLoading
import com.example.ourapp.util.tampilToast
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_news.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class News : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_news, container, false)
    }
    override fun onViewCreated(
        view: View,
        @Nullable savedInstanceState: Bundle?
    ){
        super.onViewCreated(view, savedInstanceState)
        callApiGetListSummary()
    }

    private fun callApiGetListSummary(){
        if (swipeRefreshLayoutSummary.isRefreshing()) {
            swipeRefreshLayoutSummary.setRefreshing(false);
        }
//        showLoading(context!!,swipeRefreshLayoutSummary)

        val httpClient = httpClient()
        val apiRequest = apiRequestsummary<SummaryService>(httpClient)

        val call = apiRequest.getDataSummary()
        call.enqueue(object : Callback<DataSummary> {

            override fun onFailure(call: Call<DataSummary>, t: Throwable) {
//                dismissLoading(swipeRefreshLayoutSummary)
                if (swipeRefreshLayoutSummary.isRefreshing()) {
                    swipeRefreshLayoutSummary.setRefreshing(false);
                }
            }


            override fun onResponse(
                call: Call<DataSummary>,
                response: Response<DataSummary>
            ) {
//                dismissLoading(swipeRefreshLayoutSummary)
                if (swipeRefreshLayoutSummary.isRefreshing()) {
                    swipeRefreshLayoutSummary.setRefreshing(false);
                }
                when {
                    response.isSuccessful ->
                        when {
                            response.body()?.countries?.size != 0 -> tampilListSummary(response.body()?.countries!!)
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

    private fun tampilListSummary(githubUsers: List<Country>) {
        listNews.layoutManager = LinearLayoutManager(context)
        listNews.adapter = SummaryAdapter(context!!, githubUsers) {

            val githubUser = it
            tampilToast(context!!, githubUser.country)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }
}
