package com.yasintanriverdi.disneycharacters.domain.use_case

import com.yasintanriverdi.disneycharacters.common.Resource
import com.yasintanriverdi.disneycharacters.domain.model.Character
import com.yasintanriverdi.disneycharacters.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository
) {

    operator fun invoke(): Flow<Resource<List<Character>>> = flow {
        emit(Resource.Loading())

        emit(repository.getCharacters())
    }
}