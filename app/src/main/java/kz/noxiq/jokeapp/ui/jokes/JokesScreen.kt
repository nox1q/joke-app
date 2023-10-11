package kz.noxiq.jokeapp.ui.jokes

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kz.noxiq.jokeapp.R
import kz.noxiq.jokeapp.domain.joke.model.Joke
import kz.noxiq.jokeapp.ui.theme.JokeAppTheme

@Composable
fun JokesScreen(
    jokesState: JokesState,
    onAction: (JokesAction) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        when {
            jokesState.isLoading -> CircularProgressIndicator(
                modifier = Modifier
                    .size(64.dp)
                    .align(Alignment.Center)
            )
            jokesState.isFailed -> ErrorWidget(onAction)
            else -> Content(jokesState.jokes, onAction)
        }
    }
}

@Composable
private fun BoxScope.ErrorWidget(onAction: (JokesAction) -> Unit) {
    Column(
        modifier = Modifier.align(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = R.string.jokes_screen_error_title),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
        )
        Button(
            modifier = Modifier.padding(top = 8.dp),
            onClick = { onAction(JokesAction.OnRetryButtonClicked) },
            content = { Text(text = stringResource(id = R.string.jokes_screen_error_button_text)) },
        )
    }
}

@Composable
private fun Content(jokes: List<Joke>, loadMore: (JokesAction) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(jokes) { joke ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp,
                        vertical = 4.dp,
                    )
                    .border(
                        width = 2.dp,
                        color = Color.Gray,
                        shape = RoundedCornerShape(12.dp),
                    )
                    .padding(16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.jokes_screen_text_id, joke.id),
                    fontWeight = FontWeight.Bold
                )

                when (joke) {
                    is Joke.Single -> SingleJokeWidget(joke)
                    is Joke.TwoPart -> TwoPartJokeWidget(joke)
                }
            }
        }
    }
}

@Composable
private fun SingleJokeWidget(joke: Joke.Single) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            text = joke.joke,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
private fun TwoPartJokeWidget(joke: Joke.TwoPart) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            text = joke.setup,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = joke.delivery,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Red,
        )
    }
}

@Preview
@Composable
fun JokesScreenPreview() {
    JokeAppTheme {
        JokesScreen(
            jokesState = JokesState(
                jokes = listOf(
                    Joke.Single(1L, "single line joke with id: ${1L}"),
                    Joke.TwoPart(2L, "setup line", "delivery line"),
                    Joke.Single(3L, "single line joke with id: ${3L}"),
                    Joke.TwoPart(4L, "setup line", "delivery line"),
                    Joke.Single(1L, "single line joke with id: ${1L}"),
                    Joke.TwoPart(2L, "setup line", "delivery line"),
                    Joke.Single(3L, "single line joke with id: ${3L}"),
                    Joke.TwoPart(4L, "setup line", "delivery line"),
                    Joke.Single(1L, "single line joke with id: ${1L}"),
                    Joke.TwoPart(2L, "setup line", "delivery line"),
                    Joke.Single(3L, "single line joke with id: ${3L}"),
                    Joke.TwoPart(4L, "setup line", "delivery line"),

                    )
            ),
            onAction = {},
        )
    }
}