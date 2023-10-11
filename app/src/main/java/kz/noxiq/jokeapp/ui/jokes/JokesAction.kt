package kz.noxiq.jokeapp.ui.jokes

sealed interface JokesAction {

    object OnRetryButtonClicked : JokesAction

}