package com.yasintanriverdi.disneycharacters.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yasintanriverdi.disneycharacters.presentation.character_detail.CharacterDetailScreen
import com.yasintanriverdi.disneycharacters.presentation.character_list.CharacterListScreen
import com.yasintanriverdi.disneycharacters.presentation.navigation.Args
import com.yasintanriverdi.disneycharacters.presentation.navigation.Screen
import com.yasintanriverdi.disneycharacters.presentation.ui.theme.DisneyCharactersAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DisneyCharactersAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.CharacterList.route
                    )
                    {
                        composable(
                            route = Screen.CharacterList.route
                        ) {
                            CharacterListScreen(navController = navController)
                        }

                        composable(
                            route = Screen.CharacterDetail.route + "/{${Args.ARG_CHARACTER_ID}}"
                        ) {
                            CharacterDetailScreen()
                        }
                    }
                }
            }
        }
    }
}