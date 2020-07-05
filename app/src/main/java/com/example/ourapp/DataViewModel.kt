package com.example.ourapp

import androidx.lifecycle.ViewModel

class DataViewModel : ViewModel() {
//    lateinit var repository: RSRepository
//
//    lateinit var allRS: LiveData<List<MyModel>>
//
//    fun init(context: Context) {
//        val rsDao = RSDatabase.getDatabase(context).rsDao()
//        repository = RSRepository(rsDao)
//        allRS = repository.allRS
//    }
//
//    fun delete(rs: RSModel) = viewModelScope.launch(Dispatchers.IO) {
//        repository.delete(rs)
//    }
//
//    /**
//     * Launching a new coroutine to insert the data in a non-blocking way
//     */
//    fun insertAll(hospitals: List<MyModel>) = viewModelScope.launch(Dispatchers.IO) {
//        repository.deleteAll()
//        repository.insertAll(hospitals)
//    }
}