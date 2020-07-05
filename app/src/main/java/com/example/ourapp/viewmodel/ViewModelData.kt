package com.example.ourapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ourapp.db.DataDatabase
import com.example.ourapp.DataRepository
import com.example.ourapp.MyModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelData : ViewModel() {
    lateinit var repository: DataRepository
    fun init(context: Context) {
        val myFriendDao = DataDatabase.getDatabase(context).myFriendDao()
        repository = DataRepository(myFriendDao)
    }
    fun addData(myFriend: MyModel) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(myFriend)
        }
}