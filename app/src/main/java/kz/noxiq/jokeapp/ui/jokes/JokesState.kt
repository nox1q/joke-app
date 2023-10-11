package kz.noxiq.jokeapp.ui.jokes

import androidx.compose.runtime.Immutable
import kz.noxiq.jokeapp.domain.joke.model.Joke

@Immutable
data class JokesState(
    val isLoading: Boolean = false,
    val isFailed: Boolean = false,
    val jokes: List<Joke> = emptyList()
)