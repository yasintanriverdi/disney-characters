package com.yasintanriverdi.disneycharacters.presentation.character_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.yasintanriverdi.disneycharacters.R
import com.yasintanriverdi.disneycharacters.common.UIState
import com.yasintanriverdi.disneycharacters.domain.model.Character
import com.yasintanriverdi.disneycharacters.presentation.character_detail.components.CharacterTags
import com.yasintanriverdi.disneycharacters.presentation.ui.layout.ErrorView

@Composable
fun CharacterDetailScreen(
    viewModel: CharacterDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Box(modifier = Modifier.fillMaxSize()) {
        when (state.uiState) {
            UIState.CONTENT -> CharacterDetail(state.character)
            UIState.ERROR ->
                ErrorView {
                    viewModel.getCharacter()
                }
            UIState.LOADING ->
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            UIState.IDLE -> {
                // do nothing
            }
        }
    }
}

@Composable
fun CharacterDetail(
    character: Character?
) {
    if (character == null) return

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .background(MaterialTheme.colors.background)
            .fillMaxHeight()
    ) {
        val painter = rememberImagePainter(
            data = character.imageUrl,
            builder = {
                crossfade(true)
            }
        )
        Image(
            painter = painter,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            contentDescription = "Character Detail Image"
        )

        Text(
            text = character.name,
            style = MaterialTheme.typography.h1,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            modifier = Modifier
                .padding(start = 16.dp, top = 12.dp)
        )

        CharacterTags(title = stringResource(R.string.section_title_films), tags = character.films)
        CharacterTags(
            title = stringResource(R.string.section_title_tv_shows),
            tags = character.tvShows
        )
        CharacterTags(
            title = stringResource(R.string.section_title_tv_video_games),
            tags = character.videoGames
        )

    }
}