package kz.noxiq.jokeapp.data.joke.mapper

import kz.noxiq.jokeapp.data.joke.model.JokeEntity
import kz.noxiq.jokeapp.domain.joke.model.Joke
import javax.inject.Inject

const val UNKNOWN_ID = -1L

class JokeEntityMapper @Inject constructor() {

    fun map(jokeEntity: JokeEntity?): Joke? {
        val id = jokeEntity?.id ?: UNKNOWN_ID

        return when (jokeEntity?.type) {
            "single" -> Joke.Single(
                id = id,
                joke = jokeEntity.joke.orEmpty(),
            )
            "twopart" -> Joke.TwoPart(
                id = id,
                setup = jokeEntity.setup.orEmpty(),
                delivery = jokeEntity.delivery.orEmpty(),
            )
            else -> null
        }
    }
}