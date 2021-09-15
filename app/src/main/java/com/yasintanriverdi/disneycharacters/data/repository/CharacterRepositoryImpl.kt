package com.yasintanriverdi.disneycharacters.data.repository

import com.yasintanriverdi.disneycharacters.common.Resource
import com.yasintanriverdi.disneycharacters.data.remote.CharactersApi
import com.yasintanriverdi.disneycharacters.data.remote.dto.toCharacter
import com.yasintanriverdi.disneycharacters.di.DispatcherModule
import com.yasintanriverdi.disneycharacters.domain.model.Character
import com.yasintanriverdi.disneycharacters.domain.repository.CharacterRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val api: CharactersApi,
    @DispatcherModule.IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : CharacterRepository {

    override suspend fun getCharacters(): Resource<List<Character>> {
        return withContext(ioDispatcher) {
            try {
                val characters = api.getCharacters().data.map {
                    it.toCharacter()
                }
                Resource.Success(characters)
            } catch (exception: HttpException) {
                Resource.Error(exception.localizedMessage ?: "An error occurred")
            } catch (exception: IOException) {
                Resource.Error("Network error occurred")
            }
        }


    }

    override suspend fun getCharacter(id: String): Resource<Character> {
        return withContext(ioDispatcher) {
            try {
                val character = api.getCharacter(id).toCharacter()
                Resource.Success(character)
            } catch (exception: HttpException) {
                Resource.Error(exception.localizedMessage ?: "An error occurred")
            } catch (exception: IOException) {
                Resource.Error("Network error occurred")
            }
        }
    }
}