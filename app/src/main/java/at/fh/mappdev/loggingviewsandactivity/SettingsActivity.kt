package at.fh.mappdev.loggingviewsandactivity


import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO

class SettingsActivity : AppCompatActivity() {
    companion object {
        val USERNAME = "USERNAME"
        val DARKMODE =" DARKMODE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

       // fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)

        val sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
        //var username = sharedPreferences.getString(USERNAME, null)
        val username = findViewById<EditText>(R.id.edit_username)
        val darkmode = findViewById<Switch>(R.id.switch_dark_mode)


        val usernameStored = sharedPreferences.getString(USERNAME, null)
        val darkModeStored =  sharedPreferences.getBoolean(DARKMODE, false)

        if (!usernameStored.isNullOrEmpty()){
            username.setText(usernameStored)
            darkmode.isChecked = darkModeStored
        }


        findViewById<Button>(R.id.save_settings).setOnClickListener {
            val username = username.text.toString()
            val darkmode = darkmode.isChecked



            sharedPreferences.edit().putString(USERNAME, username).apply()
            sharedPreferences.edit().putBoolean(DARKMODE, darkmode).apply()

            if (darkmode) AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
            else AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
            finish()
            }

    }
}

