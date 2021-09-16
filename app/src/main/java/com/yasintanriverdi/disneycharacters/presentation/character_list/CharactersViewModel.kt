package com.yasintanriverdi.disneycharacters.presentation.character_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yasintanriverdi.disneycharacters.common.Resource
import com.yasintanriverdi.disneycharacters.common.UIState
import com.yasintanriverdi.disneycharacters.domain.use_case.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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
        _state.value = CharacterListState(
            uiState = UIState.LOADING
        )

        viewModelScope.launch {
            val resource = getCharactersUseCase()
            _state.value = when (resource) {
                is Resource.Success ->
                    CharacterListState(
                        uiState = UIState.CONTENT,
                        characters = resource.data
                    )
                is Resource.Error ->
                    CharacterListState(
                        uiState = UIState.ERROR
                    )
            }
        }
    }
}