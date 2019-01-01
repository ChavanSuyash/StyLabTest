package com.stylab.test.features.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stylab.test.data.login.LoginRepository
import com.stylab.test.data.login.model.LoggedInUser
import com.stylab.test.data.list.ListRepository
import com.stylab.test.data.list.model.ListModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val listRepository: ListRepository,
    loginRepository: LoginRepository
) : ViewModel() {

    private val parentJob = Job()
    private val scope = CoroutineScope(Main + parentJob)

    private val _user = MutableLiveData<LoggedInUser>()
    val user : LiveData<LoggedInUser>
        get() = _user

    private val _list = MutableLiveData<List<ListModel>>()
    val list : LiveData<List<ListModel>>
        get() = _list


    init {
        _user.value = loginRepository.user
        loadData()
    }

    private fun loadData(page: Int = 1) = listRepository.loadListData(
        page,
        onSuccess =  {
            _list.value = it
        },
        onError = {
            //Emmit error
        }
    )

    override fun onCleared() {
        super.onCleared()
        listRepository.cancelAllRequests()
    }

}
