package com.yasintanriverdi.disneycharacters.domain.repository

import com.yasintanriverdi.disneycharacters.common.Resource
import com.yasintanriverdi.disneycharacters.domain.model.Character

interface CharacterRepository {

    suspend fun getCharacters(): Resource<List<Character>>

    suspend fun getCharacter(id: String): Resource<Character>

}