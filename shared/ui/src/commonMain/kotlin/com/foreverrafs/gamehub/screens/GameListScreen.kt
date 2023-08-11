package com.foreverrafs.gamehub.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.foreverrafs.gamehub.data.Result
import com.foreverrafs.gamehub.data.repository.GameRepository
import com.foreverrafs.gamehub.model.Game
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kotlinx.coroutines.launch

internal object GameListScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val screenModel = rememberScreenModel<GameListScreenModel>(
            factory = {
                GameListScreenModel(repository = GameRepository())
            },
            tag = "screen-model"
        )

        val screenState by screenModel.state.collectAsState()

        MaterialTheme {
            Surface(modifier = Modifier.fillMaxSize()) {
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                    when (screenState) {
                        is GameListScreenModel.State.Error -> Error(onRetry = screenModel::getGames)
                        is GameListScreenModel.State.Loading -> Loading()
                        is GameListScreenModel.State.Result -> GameList(
                            games = (screenState as GameListScreenModel.State.Result).games,
                            onGameClicked = {
                                navigator.push(GameDetailScreen)
                            }
                        )
                    }
                }
            }
        }
    }

    @Composable
    private fun Loading() {
        CircularProgressIndicator()
    }

    @Composable
    private fun Error(onRetry: () -> Unit) {
        Column {
            Text("Error loading games")
            Button(onClick = onRetry) {
                Text("Retry")
            }
        }
    }

    @Composable
    private fun GameList(games: List<Game>, onGameClicked: (game: Game) -> Unit) {
        val gridSpacing = 4.dp

        LazyHorizontalGrid(
            rows = GridCells.Adaptive(minSize = 200.dp),
            verticalArrangement = Arrangement.spacedBy(gridSpacing),
            horizontalArrangement = Arrangement.spacedBy(gridSpacing)
        ) {
            items(items = games, key = { it.id }) { game ->
                GameCard(game)
            }
        }
    }

    @Composable
    private fun GameCard(game: Game) {
        Card(
            modifier = Modifier.size(width = 200.dp, height = 300.dp),
            shape = RoundedCornerShape(8.dp),
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                KamelImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    resource = asyncPainterResource(game.backgroundImage),
                    contentDescription = null,
                    onLoading = {
                        CircularProgressIndicator()
                    },
                    contentScale = ContentScale.Crop,
                    onFailure = {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = Color.Green)
                        )
                    }
                )

                Text(text = game.name, modifier = Modifier.weight(1f))
            }
        }
    }
}

internal class GameListScreenModel(
    private val repository: GameRepository
) : StateScreenModel<GameListScreenModel.State>(State.Loading) {
    sealed interface State {
        data object Loading : State
        data class Error(val error: Throwable) : State
        data class Result(val games: List<Game>) : State
    }

    init {
        getGames()
    }

    fun getGames() {
        coroutineScope.launch {
            mutableState.value = State.Loading

            mutableState.value = when (val state = repository.getGames()) {
                is Result.Error -> State.Error(state.error)
                is Result.Success -> State.Result(state.data)
            }
        }
    }
}
