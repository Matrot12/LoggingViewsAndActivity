package at.fh.mappdev.loggingviewsandactivity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.moshi.Moshi


class LessonListActivity : AppCompatActivity() {
    companion object {
        val EXTRA_LESSON_ID = "LESSON_ID_EXTRA"
        val ADD_OR_EDIT_RATING_REQUEST = 1
    }
    val lessonAdapter = LessonAdapter() {


        val intent = Intent(this, LessonRatingActivity::class.java)
        intent.putExtra(EXTRA_LESSON_ID, it.id)
        startActivityForResult(intent,ADD_OR_EDIT_RATING_REQUEST)
    }
    fun updateList(){
        LessonRepository.lessonsList(
            success = {
                // handle success
                lessonAdapter.updateList(it)
            },
            error = {
                // handle error
                Log.e("API ERROR","API ERROR")
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_list)


        val recyclerView = findViewById<RecyclerView>(R.id.lesson_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = lessonAdapter

        SleepyAsyncTask().execute()
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_OR_EDIT_RATING_REQUEST && resultCode == Activity.RESULT_OK){
            val resultExtra = data?.getStringExtra(LessonRatingActivity.EXTRA_ADDED_OR_EDITED_RESULT) ?: return

            Log.e("RESULT_EXTRA", "${resultExtra}")
        }
    }
    fun parseJson (){
        val json = """
            {
                "id": "1",
                "name": "Lecture 0",
                "date": "09.10.2019",
                "topic": "Introduction",
                "type": "LECTURE",
                "lecturers": [
                    {
                        "name": "Lukas Bloder"
                    },
                    {
                        "name": "Peter Salhofer"
                    }
                ],
                "ratings": []
            }
        """
        val moshi = Moshi.Builder().build()
        val jsonAdapter = moshi.adapter<Lesson>(Lesson::class.java)
        val result = jsonAdapter.fromJson(json)
        Log.e("Name:",result?.name.toString())

    }
}