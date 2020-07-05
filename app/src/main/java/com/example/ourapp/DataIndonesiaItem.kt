package com.example.ourapp


import com.google.gson.annotations.SerializedName

data class DataIndonesiaItem(
    @SerializedName("attributes")
    val attributes: Attributes
)