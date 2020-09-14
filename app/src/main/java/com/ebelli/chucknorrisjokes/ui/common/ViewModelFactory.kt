package com.ebelli.chucknorrisjokes.ui.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ebelli.chucknorrisjokes.data.remote.ApiDataStoreImpl
import com.ebelli.chucknorrisjokes.data.remote.RetrofitBuilder
import com.ebelli.chucknorrisjokes.data.repositories.JokesRepositoryImpl
import com.ebelli.chucknorrisjokes.ui.main.MainViewModel
import kotlinx.coroutines.Dispatchers

class ViewModelFactory: ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(JokesRepositoryImpl(ApiDataStoreImpl(RetrofitBuilder.apiService)), Dispatchers.IO) as T }
        throw IllegalArgumentException("Unknown class name")
    }
}