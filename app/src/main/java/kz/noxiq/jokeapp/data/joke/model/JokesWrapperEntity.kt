package kz.noxiq.jokeapp.data.joke.model

import com.google.gson.annotations.SerializedName

data class JokesWrapperEntity(
    @SerializedName("amount")
    val amount: Int?,
    @SerializedName("jokes")
    val jokes: List<JokeEntity>?,
)