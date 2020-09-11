package com.ebelli.chucknorrisjokes.data.remote

import com.ebelli.chucknorrisjokes.data.models.Jokes

interface ApiDataStore {

    suspend fun getJokes(jokesNumber: Int) : Jokes

}