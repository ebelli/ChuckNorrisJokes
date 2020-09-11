package com.ebelli.chucknorrisjokes.data.remote

import com.ebelli.chucknorrisjokes.data.models.Jokes
import retrofit2.http.GET

interface ApiService {

    @GET("/jokes/random/{jokesNumber}")
    suspend fun getJokes(jokesNumber: Int): Jokes
}