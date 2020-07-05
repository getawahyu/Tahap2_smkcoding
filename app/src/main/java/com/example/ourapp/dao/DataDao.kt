package com.example.ourapp.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.ourapp.MyModel

@Dao
interface DataDao {
    @Query("SELECT * from people_data")
    fun getAllMyFriend(): LiveData<List<MyModel>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(myFriend: MyModel)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(myFriends: List<MyModel>)
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(myFriend: MyModel)
    @Delete()
    suspend fun delete(myFriend: MyModel)
    @Query("DELETE FROM people_data")
    suspend fun deleteAll()
}