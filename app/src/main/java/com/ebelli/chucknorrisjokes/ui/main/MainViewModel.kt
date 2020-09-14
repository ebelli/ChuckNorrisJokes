package com.ebelli.chucknorrisjokes.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ebelli.chucknorrisjokes.data.models.Jokes
import com.ebelli.chucknorrisjokes.data.repositories.JokesRepository
import com.ebelli.chucknorrisjokes.ui.utils.Result
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MainViewModel(private val jokesRepository: JokesRepository, private val coroutineContext: CoroutineContext): ViewModel() {

    private val _jokes = MutableLiveData<Result<Jokes>>()
    val jokes: LiveData<Result<Jokes>> = _jokes

    fun getJokes(jokesNumber: Int) {
        viewModelScope.launch(coroutineContext) {
            _jokes.postValue(Result.loading(data = null))
            try {
                val jokes = jokesRepository.getJokes(jokesNumber)
                if (jokes.value.isNullOrEmpty()) {
                    _jokes.postValue(Result.error(null, "Cannot retrieve jokes"))
                } else {
                    _jokes.postValue(Result.success(jokes))
                }
            } catch (e: Exception) {
                _jokes.postValue(Result.error(null, e.toString()))
            }
        }
    }
}