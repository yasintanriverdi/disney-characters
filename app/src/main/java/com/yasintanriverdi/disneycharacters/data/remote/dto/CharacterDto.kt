package com.yasintanriverdi.disneycharacters.data.remote.dto

import com.squareup.moshi.Json
import com.yasintanriverdi.disneycharacters.domain.model.Character

data class CharacterDto(
    @Json(name = "_id") val id: String,
    val name: String,
    val sourceUrl: String? = null,
    val imageUrl: String? = null,
    val tvShows: List<String>? = null,
    val films: List<String>? = null,
    val videoGames: List<String>? = null,
    val shortFilms: List<String>? = null,
    val parkAttractions: List<String>? = null,
    val allies: List<String>? = null,
    val enemies: List<String>? = null,
)

fun CharacterDto.toCharacter(): Character =
    Character(
        id = id,
        name = name,
        sourceUrl = sourceUrl,
        imageUrl = imageUrl,
        tvShows = tvShows,
        films = films,
        videoGames = videoGames
    )