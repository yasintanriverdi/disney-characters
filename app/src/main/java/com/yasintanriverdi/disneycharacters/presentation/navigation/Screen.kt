package com.yasintanriverdi.disneycharacters.presentation.navigation

sealed class Screen(val route: String) {
    object CharacterList: Screen("character_list")
    object CharacterDetail: Screen("character_detail")
}