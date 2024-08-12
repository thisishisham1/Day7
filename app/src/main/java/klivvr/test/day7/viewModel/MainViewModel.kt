package klivvr.test.day7.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import klivvr.test.day7.data.local.dao.Dao
import klivvr.test.day7.data.local.entity.DataEntity
import kotlinx.coroutines.launch

class MainViewModel(private val dao: Dao) : ViewModel() {
    private val _data = MutableLiveData<DataEntity>()
    val data: LiveData<DataEntity> get() = _data

    fun insertData(phone: String, message: String) {
        viewModelScope.launch {
            dao.deleteAllData()
            dao.insertData(DataEntity(phone = phone, message = message))
        }
    }

    fun deleteAllData() {
        viewModelScope.launch {
            dao.deleteAllData()
        }
    }

    fun getData() {
        viewModelScope.launch {
            val dataList = dao.getData()
            if (dataList.isNotEmpty()) {
                _data.postValue(dataList[0])
            }
        }
    }
}


class MainViewModelFactory(private val dao: Dao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}