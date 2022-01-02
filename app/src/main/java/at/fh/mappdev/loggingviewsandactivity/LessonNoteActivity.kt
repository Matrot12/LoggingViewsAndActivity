package at.fh.mappdev.loggingviewsandactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Update
import at.fh.mappdev.loggingviewsandactivity.LessonListActivity.Companion.EXTRA_LESSON_ID
import at.fh.mappdev.loggingviewsandactivity.LessonRatingActivity.Companion.EXTRA_LESSON_NAME

class LessonNoteActivity : AppCompatActivity() {

    companion object{
        val ADD_NOTE_REQUEST = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_note)

        val lessonID = intent.getStringExtra(EXTRA_LESSON_ID).toString()
        val lessonNameNote = intent.getStringExtra(EXTRA_LESSON_NAME)
        findViewById<TextView>(R.id.lesson_name_note).text = lessonNameNote
        val newNote= findViewById<EditText>(R.id.note_text)

        val note = LessonRepository.findLessonNote(this,lessonID)
        if(!note.text.isNullOrEmpty()) {
            newNote.setText(note.text)
            Log.e("note", note.text)
        }

        findViewById<Button>(R.id.save_note).setOnClickListener {
            val newNoteEntry = LessonNote(lessonID,lessonNameNote.toString(), newNote.text.toString())

            LessonRepository.addLessonNote(this, newNoteEntry)
            val note = LessonRepository.findLessonNote(this,lessonID)
            newNote.setText(note.text)
        }
    }

}

