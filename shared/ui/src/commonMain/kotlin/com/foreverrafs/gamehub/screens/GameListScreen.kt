package com.foreverrafs.gamehub.screens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.foreverrafs.gamehub.data.Result
import com.foreverrafs.gamehub.data.format
import com.foreverrafs.gamehub.data.repository.GameRepository
import com.foreverrafs.gamehub.model.Game
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kotlinx.coroutines.launch

internal object GameListScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val screenModel = rememberScreenModel(
            factory = {
                GameListScreenModel(repository = GameRepository())
            },
            tag = "screen-model"
        )

        val screenState by screenModel.state.collectAsState()

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            when (screenState) {
                is GameListScreenModel.ScreenState.Error -> Error(onRetry = screenModel::getGames)
                is GameListScreenModel.ScreenState.Loading -> Loading()
                is GameListScreenModel.ScreenState.Result -> GameList(
                    games = (screenState as GameListScreenModel.ScreenState.Result).games,
                    onGameClicked = { game ->
                        navigator.push(GameDetailScreen(game))
                    }
                )
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
    private fun GameList(
        games: List<Game>,
        onGameClicked: (game: Game) -> Unit
    ) {
        val gridSpacing = 10.dp

        val topPadding = with(LocalDensity.current) {
            WindowInsets.systemBars.getTop(this).toDp()
        }

        LazyVerticalGrid(
            modifier = Modifier.padding(
                top = topPadding,
            ),
            columns = if (isMobileDevice())
                GridCells.Fixed(1)
            else
                GridCells.Adaptive(minSize = 230.dp),
            verticalArrangement = Arrangement.spacedBy(gridSpacing),
            horizontalArrangement = Arrangement.spacedBy(gridSpacing),
            contentPadding = PaddingValues(
                start = gridSpacing,
                end = gridSpacing,
            )
        ) {
            items(
                items = games,
                key = { it.id }) { game ->
                GameCard(game, onGameClicked)
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun GameCard(game: Game, onGameClicked: (game: Game) -> Unit) {
        Card(
            modifier = Modifier
                .aspectRatio(if (isMobileDevice()) 1f else 0.8f),
            shape = RoundedCornerShape(8.dp),
            onClick = {
                onGameClicked(game)
            }
        ) {
            KamelImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.8f),
                resource = asyncPainterResource(game.backgroundImage),
                contentDescription = null,
                onLoading = {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                },
                contentScale = ContentScale.Crop,
                onFailure = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = Color.Green)
                    )
                },
                animationSpec = tween(easing = LinearEasing)
            )

            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .weight(0.2f)
            ) {
                Text(
                    text = game.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Released: ${game.released.format("yyyy MMMM")}",
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}

internal class GameListScreenModel(
    private val repository: GameRepository
) : StateScreenModel<GameListScreenModel.ScreenState>(ScreenState.Loading) {
    sealed interface ScreenState {
        data object Loading : ScreenState
        data class Error(val error: Throwable) : ScreenState
        data class Result(val games: List<Game>) : ScreenState
    }

    init {
        getGames()
    }

    fun getGames() {
        coroutineScope.launch {
            mutableState.value = ScreenState.Loading

            mutableState.value = when (val state = repository.getGames()) {
                is Result.Error -> ScreenState.Error(state.error)
                is Result.Success -> ScreenState.Result(state.data)
            }
        }
    }
}