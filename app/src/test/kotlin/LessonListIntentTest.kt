import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import at.fh.mappdev.loggingviewsandactivity.LessonListActivity
import at.fh.mappdev.loggingviewsandactivity.LessonViewHolder
import at.fh.mappdev.loggingviewsandactivity.R
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LessonListIntentTest {
    @get:Rule
    val rule = ActivityScenarioRule(LessonListActivity::class.java)

    @Before
    fun setup() {
        Intents.init()
    }
    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun clickingAnItem_opensDetail() {
        Thread.sleep(2000)
        onView(withId(R.id.lesson_recycler_view))
            .perform(
                RecyclerViewActions.actionOnItem<LessonViewHolder>(hasDescendant(withText("1")), click())
            )
    }
}