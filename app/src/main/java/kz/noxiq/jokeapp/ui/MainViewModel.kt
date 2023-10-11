package kz.noxiq.jokeapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kz.noxiq.jokeapp.di.IoDispatcher
import kz.noxiq.jokeapp.domain.joke.repository.JokeRepository
import kz.noxiq.jokeapp.ui.jokes.JokesAction
import kz.noxiq.jokeapp.ui.jokes.JokesState
import javax.inject.Inject

private const val DEFAULT_JOKES_AMOUNT = 10

@HiltViewModel
class MainViewModel @Inject constructor(
    private val jokeRepository: JokeRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _state = MutableStateFlow(JokesState())
    val state: StateFlow<JokesState> = _state

    init {
        loadJokes()
    }

    fun onAction(jokesAction: JokesAction) {
        when (jokesAction) {
            JokesAction.OnRetryButtonClicked -> loadJokes()
        }
    }

    private fun loadJokes() {
        viewModelScope.launch {
            _state.value = _state.value.copy(
                isLoading = true,
                isFailed = false,
            )

            val result = withContext(ioDispatcher) {
                jokeRepository.getJokes(DEFAULT_JOKES_AMOUNT)
            }

            result.apply {
                onSuccess { jokes ->
                    _state.value = _state.value.copy(
                        isLoading = false,
                        jokes = jokes,
                    )
                }
                onFailure {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        isFailed = true,
                    )
                }
            }
        }
    }
}