package com.ebelli.chucknorrisjokes.data.repositories

import com.ebelli.chucknorrisjokes.data.models.Jokes

interface JokesRepository {

    suspend fun getJokes(jokesNumber: Int) : Jokes
}