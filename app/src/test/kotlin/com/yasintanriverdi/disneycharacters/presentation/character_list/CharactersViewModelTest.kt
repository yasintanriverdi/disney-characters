package com.yasintanriverdi.disneycharacters.presentation.character_list

import com.google.common.truth.Truth.assertThat
import com.yasintanriverdi.disneycharacters.common.Resource
import com.yasintanriverdi.disneycharacters.common.UIState
import com.yasintanriverdi.disneycharacters.core.MainCoroutineRule
import com.yasintanriverdi.disneycharacters.core.MockCharacterProvider
import com.yasintanriverdi.disneycharacters.domain.model.Character
import com.yasintanriverdi.disneycharacters.domain.use_case.GetCharactersUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class CharactersViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    lateinit var viewModel: CharactersViewModel

    val getCharactersUseCase: GetCharactersUseCase = mock()

    @Test
    fun `fetch data successfully and update state`() = mainCoroutineRule.runBlockingTest {
        val mockResponse = MockCharacterProvider.getMockCharacters()

        whenever(getCharactersUseCase())
            .thenReturn(flow {
                emit(Resource.Success(data = mockResponse))
            })

        viewModel = CharactersViewModel(getCharactersUseCase)

        assertThat(viewModel.state.value).isEqualTo(CharacterListState(
            uiState = UIState.CONTENT,
            characters = mockResponse
        ))
    }

    @Test
    fun `fetch data with exception and update state`() = mainCoroutineRule.runBlockingTest {
        whenever(getCharactersUseCase())
            .thenReturn(flow {
                emit(Resource.Error<List<Character>>(message = "Error occurred"))
            })

        viewModel = CharactersViewModel(getCharactersUseCase)

        assertThat(viewModel.state.value).isEqualTo(CharacterListState(
            uiState = UIState.ERROR,
            characters = null
        ))
    }

    @Test
    fun `fetch loading result and update state`() = mainCoroutineRule.runBlockingTest {
        whenever(getCharactersUseCase())
            .thenReturn(flow {
                emit(Resource.Loading<List<Character>>())
            })

        viewModel = CharactersViewModel(getCharactersUseCase)

        assertThat(viewModel.state.value).isEqualTo(CharacterListState(
            uiState = UIState.LOADING,
            characters = null
        ))
    }

}