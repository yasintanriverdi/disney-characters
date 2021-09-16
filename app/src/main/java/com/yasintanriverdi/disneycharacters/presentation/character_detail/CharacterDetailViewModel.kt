package com.yasintanriverdi.disneycharacters.presentation.character_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yasintanriverdi.disneycharacters.common.Resource
import com.yasintanriverdi.disneycharacters.common.UIState
import com.yasintanriverdi.disneycharacters.domain.use_case.GetCharacterUseCase
import com.yasintanriverdi.disneycharacters.presentation.navigation.Args
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getCharacterUseCase: GetCharacterUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(CharacterDetailState())
    internal val state: State<CharacterDetailState> = _state

    init {
        getCharacter()
    }

    internal fun getCharacter() {
        val characterId = savedStateHandle.get<String>(Args.ARG_CHARACTER_ID)
        if (characterId == null) {
            _state.value = CharacterDetailState(
                uiState = UIState.ERROR
            )
            return
        }

        viewModelScope.launch {
            _state.value = CharacterDetailState(
                uiState = UIState.LOADING
            )

            val resource = getCharacterUseCase(characterId)
            _state.value = when (resource) {
                is Resource.Success ->
                    CharacterDetailState(
                        uiState = UIState.CONTENT,
                        character = resource.data
                    )
                is Resource.Error ->
                    CharacterDetailState(
                        uiState = UIState.ERROR
                    )
            }
        }
    }
}