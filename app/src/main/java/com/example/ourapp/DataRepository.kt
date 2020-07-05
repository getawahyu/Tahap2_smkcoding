package com.example.ourapp

import androidx.lifecycle.LiveData
import com.example.ourapp.dao.DataDao

class DataRepository (private val myFriendDao: DataDao) {
    val allMyFriend: LiveData<List<MyModel>> =
        myFriendDao.getAllMyFriend()
    suspend fun insert(myFriend: MyModel) {
        myFriendDao.insert(myFriend)
    }
    suspend fun insertAll(myFriends: List<MyModel>) {
        myFriendDao.insertAll(myFriends)
    }
    suspend fun deleteAll() {
        myFriendDao.deleteAll()
    }
    suspend fun update(myFriend: MyModel) {
        myFriendDao.update(myFriend)
    }
    suspend fun delete(myFriend: MyModel) {
        myFriendDao.delete(myFriend)
    }
}