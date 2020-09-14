package com.ebelli.chucknorrisjokes.data.remote

import com.ebelli.chucknorrisjokes.data.models.Jokes
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/jokes/random/{jokesNumber}")
    suspend fun getJokes(@Path("jokesNumber") jokesNumber: Int): Jokes
}