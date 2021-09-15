package com.yasintanriverdi.disneycharacters.presentation.character_detail

import com.yasintanriverdi.disneycharacters.common.UIState
import com.yasintanriverdi.disneycharacters.domain.model.Character

data class CharacterDetailState(
    val uiState: UIState = UIState.IDLE,
    val character: Character? = null
)