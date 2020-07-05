package com.example.ourapp.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ourapp.db.DataDatabase
import com.example.ourapp.DataRepository
import com.example.ourapp.MyModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FragmentViewModel : ViewModel() {
    lateinit var repository: DataRepository
    lateinit var allMyFriends: LiveData<List<MyModel>>
    fun init(context: Context) {
        val myFriendDao = DataDatabase.getDatabase(context).myFriendDao()
        repository = DataRepository(myFriendDao)
        allMyFriends = repository.allMyFriend
    }
    fun delete(myFriend: MyModel) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(myFriend)
    }
    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insertAll(myFriends: List<MyModel>) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
            repository.insertAll(myFriends)
        }
}