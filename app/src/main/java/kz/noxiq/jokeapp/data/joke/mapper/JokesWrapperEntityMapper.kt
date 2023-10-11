package kz.noxiq.jokeapp.data.joke.mapper

import kz.noxiq.jokeapp.data.joke.model.JokesWrapperEntity
import kz.noxiq.jokeapp.domain.joke.model.Joke
import javax.inject.Inject

class JokesWrapperEntityMapper @Inject constructor(
    private val jokeEntityMapper: JokeEntityMapper
) {

    fun map(jokesWrapperEntity: JokesWrapperEntity?): List<Joke> {
        return jokesWrapperEntity
            ?.jokes
            ?.mapNotNull(jokeEntityMapper::map)
            .orEmpty()
    }
}