package com.yasintanriverdi.disneycharacters.presentation.character_detail

import androidx.lifecycle.SavedStateHandle
import com.google.common.truth.Truth.assertThat
import com.yasintanriverdi.disneycharacters.common.Resource
import com.yasintanriverdi.disneycharacters.common.UIState
import com.yasintanriverdi.disneycharacters.core.MockCharacterProvider
import com.yasintanriverdi.disneycharacters.domain.use_case.GetCharacterUseCase
import com.yasintanriverdi.disneycharacters.presentation.navigation.Args
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
class CharacterDetailViewModelTest {

    lateinit var viewModel: CharacterDetailViewModel

    val getCharacterUseCase: GetCharacterUseCase = mock()

    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @Test
    fun `error state in case character id not exists`() {
        val characterId = null
        val savedStateHandle = SavedStateHandle().apply {
            set(Args.ARG_CHARACTER_ID, characterId)
        }

        viewModel = CharacterDetailViewModel(getCharacterUseCase, savedStateHandle)

        assertThat(viewModel.state.value).isEqualTo(CharacterDetailState(uiState = UIState.ERROR))
    }

    @Test
    fun `fetch data successfully and update state`() = runTest {
        val characterId = "58"
        val savedStateHandle = SavedStateHandle().apply {
            set(Args.ARG_CHARACTER_ID, characterId)
        }
        val mockResponse = MockCharacterProvider.getMockCharacter(characterId)

        whenever(getCharacterUseCase(characterId))
            .thenReturn((Resource.Success(data = mockResponse)))

        viewModel = CharacterDetailViewModel(getCharacterUseCase, savedStateHandle)

        assertThat(viewModel.state.value).isEqualTo(
            CharacterDetailState(
                uiState = UIState.CONTENT,
                character = mockResponse
            )
        )
    }

    @Test
    fun `fetch data with exception and update state`() = runTest {
        val characterId = "58"
        val savedStateHandle = SavedStateHandle().apply {
            set(Args.ARG_CHARACTER_ID, characterId)
        }

        whenever(getCharacterUseCase(characterId))
            .thenReturn(Resource.Error(message = "Error"))

        viewModel = CharacterDetailViewModel(getCharacterUseCase, savedStateHandle)

        assertThat(viewModel.state.value).isEqualTo(
            CharacterDetailState(
                uiState = UIState.ERROR,
                character = null
            )
        )
    }

}