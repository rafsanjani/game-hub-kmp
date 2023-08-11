package com.foreverrafs.gamehub.screens

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.foreverrafs.gamehub.data.format
import com.foreverrafs.gamehub.model.Game
import com.foreverrafs.gamehub.model.GameImage
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

class GameDetailScreen(private val game: Game) : Screen {

    @Composable
    override fun Content() {
        MobileScreen()
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    private fun MobileScreen() {
        val navigator = LocalNavigator.currentOrThrow

        var currentImage by remember {
            mutableStateOf(game.backgroundImage)
        }

        Column(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier.weight(0.40f)) {
                KamelImage(
                    asyncPainterResource(currentImage),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                    animationSpec = tween(easing = LinearEasing),
                )

                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 48.dp, start = 16.dp)
                        .background(
                            color = Color.Black.copy(alpha = 0.30f),
                            shape = CircleShape
                        )
                        .clickable {
                            navigator.popUntilRoot()
                        }
                        .padding(8.dp),
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Column(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .weight(0.35f)
            ) {
                Text(
                    text = game.name,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                )

                Spacer(modifier = Modifier.height(12.dp))

                SectionText(
                    title = "Released:",
                    showBottomDivider = false,
                    showTopDivider = true,
                    content = game.released.format("yyyy MMMM")
                )

                SectionText(
                    title = "Platforms:",
                    content = game
                        .platforms
                        .take(4)
                        .joinToString(separator = ", ") { it.name },
                    showBottomDivider = false,
                    showTopDivider = true,
                )

                SectionText(
                    title = "Genres:",
                    content = game.genres.joinToString(separator = ", ") { it.name },
                    showBottomDivider = false,
                    showTopDivider = true,
                )

                SectionText(
                    title = "Rating:",
                    content = "${game.rating}%",
                    showBottomDivider = true,
                    showTopDivider = true,
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(text = "Screenshots", style = MaterialTheme.typography.labelLarge)

                Spacer(modifier = Modifier.height(16.dp))
            }

            Screenshots(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.25f),
                onImageClicked = {
                    currentImage = it.image
                },
                screenshots = game.screenshots
            )
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun Screenshots(
        modifier: Modifier = Modifier,
        screenshots: List<GameImage>,
        onImageClicked: (image: GameImage) -> Unit,
    ) {
        val listState = rememberLazyListState()
        val flingBehaviour = rememberSnapFlingBehavior(lazyListState = listState)

        LazyRow(
            state = rememberLazyListState(),
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            flingBehavior = flingBehaviour,
        ) {
            items(items = screenshots, key = { it.id }) { screenshot ->
                KamelImage(
                    resource = asyncPainterResource(screenshot.image),
                    contentDescription = null,
                    modifier = Modifier
                        .clickable {
                            onImageClicked(screenshot)
                        }
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }

    @Composable
    fun SectionText(
        modifier: Modifier = Modifier,
        showTopDivider: Boolean = true,
        showBottomDivider: Boolean = true,
        title: String,
        content: String
    ) {
        if (showTopDivider) Divider()
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    16.dp
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier.alpha(0.5f),
                    text = title,
                    style = MaterialTheme.typography.labelLarge
                )

                Text(
                    text = content,
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
        if (showBottomDivider) Divider()
    }
}