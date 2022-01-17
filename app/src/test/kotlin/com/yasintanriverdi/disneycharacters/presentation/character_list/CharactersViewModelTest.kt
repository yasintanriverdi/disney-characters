package com.yasintanriverdi.disneycharacters.presentation.character_list

import com.google.common.truth.Truth.assertThat
import com.yasintanriverdi.disneycharacters.common.Resource
import com.yasintanriverdi.disneycharacters.common.UIState
import com.yasintanriverdi.disneycharacters.core.MockCharacterProvider
import com.yasintanriverdi.disneycharacters.domain.model.Character
import com.yasintanriverdi.disneycharacters.domain.use_case.GetCharactersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class CharactersViewModelTest {

    lateinit var viewModel: CharactersViewModel

    val getCharactersUseCase: GetCharactersUseCase = mock()

    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @Test
    fun `fetch data successfully and update state`() = runTest {
        val mockResponse = MockCharacterProvider.getMockCharacters()

        whenever(getCharactersUseCase())
            .thenReturn(Resource.Success(data = mockResponse))

        viewModel = CharactersViewModel(getCharactersUseCase)

        assertThat(viewModel.state.value).isEqualTo(
            CharacterListState(
                uiState = UIState.CONTENT,
                characters = mockResponse
            )
        )
    }

    @Test
    fun `fetch data with exception and update state`() = runTest {
        whenever(getCharactersUseCase())
            .thenReturn(Resource.Error(message = "Error occurred"))

        viewModel = CharactersViewModel(getCharactersUseCase)

        assertThat(viewModel.state.value).isEqualTo(
            CharacterListState(
                uiState = UIState.ERROR,
                characters = null
            )
        )
    }

}