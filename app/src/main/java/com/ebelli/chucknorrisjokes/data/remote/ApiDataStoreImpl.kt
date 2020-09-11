package com.ebelli.chucknorrisjokes.data.remote

class ApiDataStoreImpl(private val apiService: ApiService): ApiDataStore {

    override suspend fun getJokes(jokesNumber: Int) = apiService.getJokes(jokesNumber)
}