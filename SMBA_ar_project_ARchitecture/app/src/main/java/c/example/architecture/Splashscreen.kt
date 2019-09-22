package c.example.architecture

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class Splashscreen : AppCompatActivity() {

    private val SPLASH_TIME_OUT: Long = 3000 // 3 sec

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splashscreen)

        Handler().postDelayed({
            // Start main activity
            startActivity(Intent(this, UserSelectionActivity::class.java))
            finish()
        }, SPLASH_TIME_OUT)
    }
}
