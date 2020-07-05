package com.example.ourapp.data

import com.example.ourapp.DataDeathsItem
import retrofit2.Call
import retrofit2.http.GET

interface DeathService {
    @GET("deaths")
    fun getDataDeats(): Call<List<DataDeathsItem>>
}