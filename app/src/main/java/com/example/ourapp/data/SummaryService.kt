package com.example.ourapp.data


import com.example.ourapp.DataDeathsItem
import com.example.ourapp.DataSummary
import retrofit2.Call
import retrofit2.http.GET

interface SummaryService {

    @GET("summary")
    fun getDataSummary(): Call<DataSummary>

}