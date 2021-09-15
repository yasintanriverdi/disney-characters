package com.yasintanriverdi.disneycharacters.presentation.character_list

import com.yasintanriverdi.disneycharacters.common.UIState
import com.yasintanriverdi.disneycharacters.domain.model.Character

data class CharacterListState(
    val uiState: UIState = UIState.IDLE,
    val characters: List<Character>? = null
)