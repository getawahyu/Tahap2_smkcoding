package com.example.ourapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserModel (
    var nama: String,
    var email: String,
    var profil: String,
    @PrimaryKey var key: String
){
    constructor() : this("", "", "", "" )
}