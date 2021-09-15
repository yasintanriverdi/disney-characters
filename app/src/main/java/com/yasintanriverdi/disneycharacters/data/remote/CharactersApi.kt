package com.yasintanriverdi.disneycharacters.data.remote

import com.yasintanriverdi.disneycharacters.data.remote.dto.CharacterDto
import com.yasintanriverdi.disneycharacters.data.remote.model.response.CharactersResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CharactersApi {

    @GET("characters")
    suspend fun getCharacters(): CharactersResponse

    @GET("characters/{id}")
    suspend fun getCharacter(@Path("id") id: String): CharacterDto

}