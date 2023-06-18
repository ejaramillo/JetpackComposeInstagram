package cl.scotiabank.libraries.jetpackcomposeinstagram.components

import androidx.compose.ui.test.junit4.createComposeRule
import cl.scotiabank.libraries.jetpackcomposeinstagram.LalinComponent
import org.junit.Rule
import org.junit.Test

class LalinComponentTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun myFirstTest(){
        composeTestRule.setContent {
            LalinComponent()
        }
    }
}
