package kz.noxiq.jokeapp.data.joke.repository

import kz.noxiq.jokeapp.common.network.executeCall
import kz.noxiq.jokeapp.data.joke.api.JokeApi
import kz.noxiq.jokeapp.data.joke.mapper.JokesWrapperEntityMapper
import kz.noxiq.jokeapp.domain.joke.model.Joke
import kz.noxiq.jokeapp.domain.joke.repository.JokeRepository
import javax.inject.Inject

class BaseJokeRepository @Inject constructor(
    private val jokeApi: JokeApi,
    private val jokesWrapperEntityMapper: JokesWrapperEntityMapper,
) : JokeRepository {

    override fun getJokes(amount: Int): Result<List<Joke>> {
        val result = jokeApi.getJokes(amount).executeCall()

        return result.mapCatching(jokesWrapperEntityMapper::map)
    }
}