package kz.noxiq.jokeapp.domain.joke.repository

import androidx.annotation.WorkerThread
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kz.noxiq.jokeapp.domain.joke.model.Joke

interface JokeRepository {

    @WorkerThread
    fun getJokes(amount: Int): Result<List<Joke>>

}