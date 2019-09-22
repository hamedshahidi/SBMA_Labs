package c.example.architecture

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_user_selection.*

class UserSelectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_selection)

        btnArtistMode.setOnClickListener {
            startActivity(Intent(this, ArtistGalleryActivity::class.java))        }

        btnVoteMode.setOnClickListener {
            startActivity(Intent(this, ScanActivity::class.java))
        }
    }
}
