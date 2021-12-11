package at.fh.mappdev.loggingviewsandactivity

import com.squareup.moshi.JsonClass

enum class LessonType(val description: String) {
    LECTURE("Lecture"),
    PRACTICAL("Practical")
}

@JsonClass(generateAdapter = true)
class LessonRating(val ratingValue:Double , val feedback:String )

@JsonClass(generateAdapter = true)
class Lesson(
    val id: String, val name: String, val date: String, val topic: String,
    val type: LessonType, val lecturers: List<Lecturer>, var ratings: List<LessonRating>,
    val imageUrl: String  = ""
) {
    fun ratingAverage(): Double {
        var ratingResult = 0.0
        if (ratings.size > 0){
            ratingResult = ratings.sumOf { it.ratingValue } / ratings.size
        }
        return ratingResult
    }
}

@JsonClass(generateAdapter = true)
data class Lecturer(val name:String)




