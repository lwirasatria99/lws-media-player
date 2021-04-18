package com.lws.lwsmediaplayer.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lws.lwsmediaplayer.data.MainRepository
import com.lws.lwsmediaplayer.data.model.ResultItunes
import com.lws.lwsmediaplayer.util.ApiException
import com.lws.lwsmediaplayer.util.Coroutines
import com.lws.lwsmediaplayer.util.NoInternetException
import com.lws.lwsmediaplayer.util.State

class MainViewModel @ViewModelInject constructor(private val repo: MainRepository) : ViewModel() {

    fun loadData(keyword: String,
                 success: (data: List<ResultItunes>) -> Unit,
                 failed: (message: String) -> Unit) {

        Coroutines.main {
            try {
                val dataResponse = repo.fetchList(keyword)
                dataResponse.results?.let {
                    success.invoke(it)
                }

                if (dataResponse.results.isNullOrEmpty()) {
                    failed.invoke(State.failed)
                }

            } catch (e: ApiException) {
                failed.invoke(State.apiFailed)
            } catch (e: NoInternetException) {
                failed.invoke(State.noInternet)
            }
        }
    }
}