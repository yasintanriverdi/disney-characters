package com.yasintanriverdi.disneycharacters.domain.model

data class Character(
    val id: String,
    val name: String,
    val sourceUrl: String? = null,
    val imageUrl: String? = null,
    val tvShows: List<String>? = null,
    val films: List<String>? = null,
    val videoGames: List<String>? = null,
)