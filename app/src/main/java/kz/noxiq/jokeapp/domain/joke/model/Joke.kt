package kz.noxiq.jokeapp.domain.joke.model

sealed class Joke {

    abstract val id: Long

    data class Single(override val id: Long, val joke: String) : Joke()

    data class TwoPart(override val id: Long, val setup: String, val delivery: String) : Joke()
}