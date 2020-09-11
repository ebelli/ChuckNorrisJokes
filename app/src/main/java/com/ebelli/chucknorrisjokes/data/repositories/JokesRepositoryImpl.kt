package com.ebelli.chucknorrisjokes.data.repositories

import com.ebelli.chucknorrisjokes.data.remote.ApiDataStore

class JokesRepositoryImpl(private val apiDataStore: ApiDataStore): JokesRepository {

    override suspend fun getJokes(jokesNumber: Int) = apiDataStore.getJokes(jokesNumber)

}