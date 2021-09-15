package com.yasintanriverdi.disneycharacters.presentation.character_list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.yasintanriverdi.disneycharacters.common.UIState
import com.yasintanriverdi.disneycharacters.presentation.character_list.components.CharacterItem
import com.yasintanriverdi.disneycharacters.presentation.navigation.Screen
import com.yasintanriverdi.disneycharacters.presentation.ui.layout.ErrorView

@ExperimentalFoundationApi
@Composable
fun CharacterListScreen(
    navController: NavController,
    viewModel: CharactersViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    Box(modifier = Modifier.fillMaxSize()) {
        when (state.uiState) {
            UIState.CONTENT -> {

                val listState = rememberLazyListState()

                LazyVerticalGrid(
                    state = listState,
                    cells = GridCells.Fixed(count = 3),
                    contentPadding = PaddingValues(all = 8.dp)
                ) {
                    items(state.characters!!) { character ->
                        CharacterItem(character = character, onItemClick = {
                            navController.navigate(Screen.CharacterDetail.route + "/${character.id}")
                        })
                    }
                }
            }
            UIState.ERROR -> ErrorView {
                viewModel.getCharacters()
            }
            UIState.LOADING ->
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            UIState.IDLE -> {
                // do nothing
            }
        }
    }

}