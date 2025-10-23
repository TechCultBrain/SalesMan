import androidx.activity.compose.setContent
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.assertIsDisplayed
import org.junit.Rule
import org.junit.Test

class ProductScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun productScreen_displaysCorrectContent() {
        // Set the content for the test
        composeTestRule.setContent {
            ProductScreen() // Assuming no complex dependencies for this preview
        }

        // Find the text element
        val contentText = "Product Screen Content"
        composeTestRule.onNodeWithText(contentText).assertExists()
        composeTestRule.onNodeWithText(contentText).assertIsDisplayed()
    }
}