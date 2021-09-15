package com.yasintanriverdi.disneycharacters

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onFirst
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import com.yasintanriverdi.disneycharacters.presentation.MainActivity
import com.yasintanriverdi.disneycharacters.presentation.character_list.CharacterListScreen
import com.yasintanriverdi.disneycharacters.presentation.character_list.CharactersViewModel
import com.yasintanriverdi.disneycharacters.presentation.ui.theme.DisneyCharactersAppTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalFoundationApi
@HiltAndroidTest
class CharactersTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    lateinit var activity: MainActivity

    lateinit var navController: TestNavHostController

    @Before
    fun init() {
        navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        composeTestRule.activityRule.scenario.onActivity {
            activity = it
        }
    }

    @Test
    fun load_characters_on_app_launch() {
        composeTestRule.setContent {
            DisneyCharactersAppTheme {
                val viewModel = hiltViewModel<CharactersViewModel>()
                CharacterListScreen(navController, viewModel)
                Thread.sleep(3000)
            }
        }

        composeTestRule.onAllNodesWithContentDescription("Character Item Image").onFirst()
            .assertIsDisplayed()
    }

}