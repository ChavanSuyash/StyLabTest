package com.stylab.test.features.home

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stylab.test.R
import com.stylab.test.data.login.LoginRepository
import com.stylab.test.data.login.model.LoggedInUser
import com.stylab.test.data.list.ListRepository
import com.stylab.test.data.list.model.ListModel
import com.stylab.test.features.login.LoginUiModel
import com.stylab.test.util.Event
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val listRepository: ListRepository,
    loginRepository: LoginRepository
) : ViewModel() {

    private val _user = MutableLiveData<LoggedInUser>()
    val user : LiveData<LoggedInUser>
        get() = _user

    private val _list = MutableLiveData<List<ListModel>>()
    val list : LiveData<List<ListModel>>
        get() = _list

    private val _uiState = MutableLiveData<HomeUiModel>()
    val uiState: LiveData<HomeUiModel>
        get() = _uiState

    var isRefreshing = ObservableBoolean()

    init {
        _user.value = loginRepository.user
        loadData()
    }

    private fun loadData(page: Int = 1) {
        isRefreshing.set(true)

        listRepository.loadListData(
            page,
            filter = true,
            onSuccess = {
                isRefreshing.set(false)
                _list.value = it

            },
            onError = {
                isRefreshing.set(false)
                emitUiState(
                    showError = Event(R.string.something_is_wrong)
                )
            }
        )
    }

    fun onRefresh() {
        loadData()
    }

    private fun emitUiState(
        showError: Event<Int>? = null
    ) {
        val uiModel = HomeUiModel(showError)
        _uiState.value = uiModel
    }

    override fun onCleared() {
        super.onCleared()
        listRepository.cancelAllRequests()
    }

}

class HomeUiModel(
    val showError : Event<Int>?
)
