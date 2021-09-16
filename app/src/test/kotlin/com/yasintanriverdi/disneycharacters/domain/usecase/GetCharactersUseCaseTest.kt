package com.yasintanriverdi.disneycharacters.domain.usecase

import com.google.common.truth.Truth.assertThat
import com.yasintanriverdi.disneycharacters.common.Resource
import com.yasintanriverdi.disneycharacters.core.MockCharacterProvider
import com.yasintanriverdi.disneycharacters.domain.model.Character
import com.yasintanriverdi.disneycharacters.domain.repository.CharacterRepository
import com.yasintanriverdi.disneycharacters.domain.use_case.GetCharactersUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class GetCharactersUseCaseTest {

    lateinit var useCase: GetCharactersUseCase

    val characterRepository: CharacterRepository = mock()

    @Before
    fun setup() {
        useCase = GetCharactersUseCase(characterRepository)
    }

    @Test
    fun `fetch characters from repository`() = runBlocking {
        val mockCharacters = MockCharacterProvider.getMockCharacters()
        val expectedResult = Resource.Success(data = mockCharacters)
        whenever(characterRepository.getCharacters())
            .thenReturn(expectedResult as Resource<List<Character>>)

        val resource = useCase()

        verify(characterRepository).getCharacters()
        assertThat(resource).isEqualTo(expectedResult)
    }

}