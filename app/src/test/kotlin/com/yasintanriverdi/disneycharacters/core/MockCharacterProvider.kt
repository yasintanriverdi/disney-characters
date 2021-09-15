package com.yasintanriverdi.disneycharacters.core

import com.yasintanriverdi.disneycharacters.domain.model.Character

object MockCharacterProvider {
    fun getMockCharacter(id: String = "58") =
        Character(
            id = id,
            name = "test"
        )

    fun getMockCharacters() =
        (1..6).map {
            getMockCharacter(id = it.toString())
        }
}