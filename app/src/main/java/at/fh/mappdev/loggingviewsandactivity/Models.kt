package at.fh.mappdev.loggingviewsandactivity


class LessonRating(val ratingValue:Int , val feeback:String )

class Lesson(
    val id: String, val name: String, val date: String, val topic: String,
    val type: LessonType, val lecturers: List<Lecturer>, val ratings: List<LessonRating>
) {
    fun ratingAverage(): Double {
        return 0.0
    }
}

class Lecturer(val name:String)

enum class LessonType(val description: String) {
    LECTURE("Lecture"),
    PRACTICAL("Practical")
}

