package com.example.ourapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "people_data")
data class MyModel(
    var nama: String,
    var telp: String,
    var alamat: String,
    var riwayat: String,
    @PrimaryKey var key: String
){
    constructor() : this("","","","",""
    )
}
