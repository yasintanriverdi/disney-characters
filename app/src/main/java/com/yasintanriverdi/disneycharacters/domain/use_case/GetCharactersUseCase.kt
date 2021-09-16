package com.yasintanriverdi.disneycharacters.domain.use_case

import com.yasintanriverdi.disneycharacters.common.Resource
import com.yasintanriverdi.disneycharacters.domain.model.Character
import com.yasintanriverdi.disneycharacters.domain.repository.CharacterRepository
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository
) {

    suspend operator fun invoke(): Resource<List<Character>> =
        repository.getCharacters()

}