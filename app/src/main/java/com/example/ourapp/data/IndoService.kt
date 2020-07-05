package com.example.ourapp.data

import com.example.ourapp.DataIndonesia
import retrofit2.Call
import retrofit2.http.GET

interface IndoService {
    @GET("provinsi")
    fun getDataIndo(): Call<DataIndonesia>
}