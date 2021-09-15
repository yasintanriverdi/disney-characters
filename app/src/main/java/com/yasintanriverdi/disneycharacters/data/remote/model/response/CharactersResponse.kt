package com.yasintanriverdi.disneycharacters.data.remote.model.response

import com.yasintanriverdi.disneycharacters.data.remote.dto.CharacterDto

data class CharactersResponse(
    val data: List<CharacterDto>
)