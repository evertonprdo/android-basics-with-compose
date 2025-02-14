package com.example.reply

import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.test.SemanticsNodeInteractionCollection
import androidx.compose.ui.test.assertAny
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasAnyDescendant
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.StateRestorationTester
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.performClick
import com.example.reply.data.local.LocalEmailsDataProvider
import com.example.reply.ui.ReplyApp
import org.junit.Rule
import org.junit.Test

class ReplyAppStateRestorationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    @TestCompactWidth
    fun compactDevice_selectedEmailEmailRetained_afterConfigChange() {
        val stateRestorationTester = StateRestorationTester(composeTestRule)
        stateRestorationTester.setContent { ReplyApp(windowSize = WindowWidthSizeClass.Compact) }

        val onNodeThirdEmailBody = {
            composeTestRule.onNodeWithTextForStringId(LocalEmailsDataProvider.allEmails[2].body)
        }
        val onNodeNavigationBack = {
            composeTestRule.onNodeWithContentDescriptionForStringId(R.string.navigation_back)
        }

        onNodeThirdEmailBody().assertIsDisplayed()

        composeTestRule.onNodeWithTextForStringId(LocalEmailsDataProvider.allEmails[2].subject)
            .performClick()

        onNodeNavigationBack().assertExists()
        onNodeThirdEmailBody().assertExists()

        stateRestorationTester.emulateSavedInstanceStateRestore()

        onNodeNavigationBack().assertExists()
        onNodeThirdEmailBody().assertExists()
    }

    @Test
    @TestExpandedWidth
    fun expandedDevice_selectedEmailEmailRetained_afterConfigChange() {
        val stateRestorationTester = StateRestorationTester(composeTestRule)
        stateRestorationTester.setContent { ReplyApp(windowSize = WindowWidthSizeClass.Expanded) }

        val thirdEmail = LocalEmailsDataProvider.allEmails[2]
        val verifyThirdEmailIsDisplayed: () -> SemanticsNodeInteractionCollection

        composeTestRule.apply {
            onNodeWithTextForStringId(thirdEmail.body).assertIsDisplayed()
            onNodeWithTextForStringId(thirdEmail.subject).performClick()

            verifyThirdEmailIsDisplayed = {
                onNodeWithTagForStringId(R.string.details_screen)
                    .onChildren()
                    .assertAny(
                        hasAnyDescendant(
                            hasText(activity.getString(thirdEmail.body))
                        )
                    )
            }
        }

        verifyThirdEmailIsDisplayed()
        stateRestorationTester.emulateSavedInstanceStateRestore()
        verifyThirdEmailIsDisplayed()
    }
}