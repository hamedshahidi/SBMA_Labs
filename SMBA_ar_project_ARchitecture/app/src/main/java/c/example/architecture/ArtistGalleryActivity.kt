package c.example.architecture

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_artist_gallery.*

var modelIdentifier: Int = 1

class ArtistGalleryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist_gallery)

        setImagesToSqares()
        intent = Intent(this, ScanActivity::class.java)

        // To pass any data to next activity
        //intent.putExtra("keyIdentifier", value)

        pic1.setOnClickListener{
            modelIdentifier = 1
            intent.putExtra("modelId", modelIdentifier)
            //intent.putExtra("keyIdentifier", value)
            startActivity(intent)
            Log.d("TAG", "PRESSED1")
        }
        pic2.setOnClickListener{
            modelIdentifier = 2
            intent.putExtra("modelId", modelIdentifier)
            //intent.putExtra("keyIdentifier", value)
            startActivity(intent)
            Log.d("TAG", "PRESSED2")
        }
        pic3.setOnClickListener{
            modelIdentifier = 3
            intent.putExtra("modelId", modelIdentifier)
            //intent.putExtra("keyIdentifier", value)
            startActivity(intent)
            Log.d("TAG", "PRESSED3")
        }
        pic4.setOnClickListener{
            modelIdentifier = 4
            intent.putExtra("modelId", modelIdentifier)
            //intent.putExtra("keyIdentifier", value)
            startActivity(intent)
            Log.d("TAG", "PRESSED4")
        }
        pic5.setOnClickListener{
            modelIdentifier = 5
            intent.putExtra("modelId", modelIdentifier)
            //intent.putExtra("keyIdentifier", value)
            startActivity(intent)
            Log.d("TAG", "PRESSED5")
        }
        pic6.setOnClickListener{
            modelIdentifier = 6
            intent.putExtra("modelId", modelIdentifier)
            //intent.putExtra("keyIdentifier", value)
            startActivity(intent)
            Log.d("TAG", "PRESSED6")
        }
        pic7.setOnClickListener{
            modelIdentifier = 7
            intent.putExtra("modelId", modelIdentifier)
            //intent.putExtra("keyIdentifier", value)
            startActivity(intent)
            Log.d("TAG", "PRESSED7")
        }
        pic8.setOnClickListener{
            modelIdentifier = 8
            intent.putExtra("modelId", modelIdentifier)
            //intent.putExtra("keyIdentifier", value)
            startActivity(intent)
            Log.d("TAG", "PRESSED8")
        }
    }

    private fun setImagesToSqares () {
        pic1.setImageResource(R.drawable.art1)
        pic2.setImageResource(R.drawable.art2)
        pic3.setImageResource(R.drawable.art3)
        pic4.setImageResource(R.drawable.img4)
        pic5.setImageResource(R.drawable.img5)
        pic6.setImageResource(R.drawable.img6)
        pic7.setImageResource(R.drawable.img7)
        pic8.setImageResource(R.drawable.img8)
    }
}
