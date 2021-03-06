package at.fh.mappdev.loggingviewsandactivity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import at.fh.mappdev.loggingviewsandactivity.LessonListActivity.Companion.EXTRA_LESSON_ID
import at.fh.mappdev.loggingviewsandactivity.LessonRepository.lessonById
import at.fh.mappdev.loggingviewsandactivity.LessonRepository.lessonsList
import at.fh.mappdev.loggingviewsandactivity.LessonRepository.rateLesson
import com.bumptech.glide.Glide

class LessonRatingActivity : AppCompatActivity() {
    companion object {
        val EXTRA_ADDED_OR_EDITED_RESULT = "EXTRA_ADDED_OR_EDITED_RESULT"
       // val EXTRA_LESSON_ID = "LESSON_ID_EXTRA"
        val EXTRA_LESSON_NAME = "EXTRA_LESSON_NAME"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_rating)

        val lessonID = intent.getStringExtra(EXTRA_LESSON_ID)
        var lessonIdAsInt = 0
        var lessonName = ""


        if (lessonID != null){
            lessonById(
                lessonID,
                success = {
                    // handle success
                    val thislesson = it
                    //val textView = findViewById<TextView>(R.id.lesson_rating_header)
                    title = thislesson.name
                    lessonName = thislesson.name
                    lessonIdAsInt = lessonID.toInt()

                    val imageView = findViewById<ImageView>(R.id.lesson_image)
                    Glide.with(this)
                        .load(thislesson.imageUrl)
                        .into(imageView)

                    findViewById<TextView>(R.id.lesson_name).text = title
                    findViewById<RatingBar>(R.id.lesson_avg_ratingBar).rating = it.ratingAverage().toFloat()
                    val rating  = it.ratingAverage().round(2)

                    findViewById<TextView>(R.id.lesson_avg_ratingText).text = rating.toString()

                    val lastFeedback = findEntry(it.ratings)
                    findViewById<TextView>(R.id.last_rating).text = lastFeedback

                },
                error = {
                    // handle error
                    Log.e("API ERROR","API ERROR")
                }
            )
        }


        findViewById<Button>(R.id.rate_lesson).setOnClickListener {
            val ratingBar = findViewById<RatingBar>(R.id.lesson_rating_bar).rating.toDouble()
            val feedback = findViewById<EditText>(R.id.lesson_feedback).text.toString()
            val ratedLesson = LessonRating(ratingBar, feedback)


            rateLesson(
                lessonIdAsInt,
                ratedLesson,
                success = {
                    // handle success
                    val thislesson = it

                },
                error = {
                    // handle error
                    Log.e("API ERROR","API ERROR")
                }
            )


            val resultIntent = Intent()
            resultIntent.putExtra(EXTRA_ADDED_OR_EDITED_RESULT, "Added")
            setResult(Activity.RESULT_OK,resultIntent)
            finish()
        }

        findViewById<Button>(R.id.open_lessonnote).setOnClickListener {
            val intent = Intent(this, LessonNoteActivity::class.java)
            intent.putExtra(EXTRA_LESSON_ID, lessonID)
            intent.putExtra(EXTRA_LESSON_NAME, lessonName)
            startActivityForResult(intent,LessonNoteActivity.ADD_NOTE_REQUEST)


        }

    }

    private fun findEntry(list:List<LessonRating>, i:Int = 0):String{
        if(list.size <= i) return ""

        if(list[i].feedback != "") return list[i].feedback

        return findEntry(list, i+1)
        }

    private fun Double.round(decimals :Int) :Double = "%.${decimals}f".format(this).toDouble()


}
