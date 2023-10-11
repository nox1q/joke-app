package kz.noxiq.jokeapp.data.joke.model

import com.google.gson.annotations.SerializedName

data class JokeEntity(
    @SerializedName("id")
    val id: Long?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("joke")
    val joke: String?,
    @SerializedName("setup")
    val setup: String?,
    @SerializedName("delivery")
    val delivery: String?,
)