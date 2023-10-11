package kz.noxiq.jokeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kz.noxiq.jokeapp.ui.MainViewModel
import kz.noxiq.jokeapp.ui.jokes.JokesScreen
import kz.noxiq.jokeapp.ui.jokes.JokesState
import kz.noxiq.jokeapp.ui.theme.JokeAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val vm: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JokeAppTheme {
                val jokesState: JokesState by vm.state.collectAsStateWithLifecycle()

                JokesScreen(
                    jokesState = jokesState,
                    onAction = vm::onAction
                )
            }
        }
    }
}