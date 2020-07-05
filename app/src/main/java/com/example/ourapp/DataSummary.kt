package com.example.ourapp


import com.google.gson.annotations.SerializedName

data class DataSummary(
    @SerializedName("Countries")
    val countries: List<Country>,
    @SerializedName("Date")
    val date: String,
    @SerializedName("Global")
    val global: Global
)