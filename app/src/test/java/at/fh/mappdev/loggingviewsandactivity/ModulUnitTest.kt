package at.fh.mappdev.loggingviewsandactivity

import com.google.common.truth.Truth.assertThat
import org.junit.Assert.assertEquals
import org.junit.Test

class ModelUnitTest {
    @Test
    fun averageForEmptyRates_isCorrect() {
        // test whether the average is 0.0 when ratings are empty
        val newLesson = Lesson("1000", "Test", "22.02.2022", "test" , LessonType.LECTURE, listOf(), mutableListOf(),"" )
        val rating  = newLesson.ratingAverage()

        assertEquals(rating, 0.0 , 0.002)


    }
    @Test
    fun averageForNonEmptyRates_isCorrect(testLesson :Lesson,  ) {

        val ratingAverage = testLesson.ratingAverage()

        var lessonRating = 0.0
        if (testLesson.ratings.size > 0){
            lessonRating = testLesson.ratings.sumOf { it.ratingValue } / testLesson.ratings.size

            assertEquals(ratingAverage, lessonRating , 0.002)


        }

    }
}