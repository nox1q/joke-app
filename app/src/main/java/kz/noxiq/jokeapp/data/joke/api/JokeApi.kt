package kz.noxiq.jokeapp.data.joke.api

import kz.noxiq.jokeapp.data.joke.model.JokesWrapperEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface JokeApi {
    @GET("joke/Any?")
    fun getJokes(
        @Query("amount") amount: Int
    ): Call<JokesWrapperEntity>
}