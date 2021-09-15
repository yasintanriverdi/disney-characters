package com.yasintanriverdi.disneycharacters.domain.usecase

import com.google.common.truth.Truth
import com.yasintanriverdi.disneycharacters.common.Resource
import com.yasintanriverdi.disneycharacters.core.MockCharacterProvider
import com.yasintanriverdi.disneycharacters.domain.model.Character
import com.yasintanriverdi.disneycharacters.domain.repository.CharacterRepository
import com.yasintanriverdi.disneycharacters.domain.use_case.GetCharacterUseCase
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetCharacterUseCaseTest {

    lateinit var useCase: GetCharacterUseCase

    val characterRepository: CharacterRepository = mock()

    @Before
    fun setup() {
        useCase = GetCharacterUseCase(characterRepository)
    }

    @Test
    fun `fetch character from repository`() = runBlocking {
        val characterId = "58"
        val mockCharacter = MockCharacterProvider.getMockCharacter(characterId)
        val charactersFlow = useCase(characterId)

        val expectedResult = Resource.Success(data = mockCharacter)
        whenever(characterRepository.getCharacter(characterId))
            .thenReturn(expectedResult as Resource<Character>)

        Truth.assertThat(charactersFlow.drop(0).first()).isInstanceOf(Resource.Loading::class.java)
        Truth.assertThat(charactersFlow.drop(1).first()).isEqualTo(expectedResult)
    }
}