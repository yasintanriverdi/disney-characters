package com.yasintanriverdi.disneycharacters.presentation.character_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yasintanriverdi.disneycharacters.common.Resource
import com.yasintanriverdi.disneycharacters.common.UIState
import com.yasintanriverdi.disneycharacters.domain.use_case.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    private val _state = mutableStateOf(CharacterListState())
    internal val state: State<CharacterListState> = _state

    init {
        getCharacters()
    }

    internal fun getCharacters() {
        getCharactersUseCase().onEach { resource ->
            when (resource) {
                is Resource.Success ->
                    _state.value = CharacterListState(
                        uiState = UIState.CONTENT,
                        characters = resource.data
                    )
                is Resource.Error ->
                    _state.value = CharacterListState(
                        uiState = UIState.ERROR
                    )
                is Resource.Loading ->
                    _state.value = CharacterListState(
                        uiState = UIState.LOADING
                    )
            }
        }.launchIn(viewModelScope)
    }
}