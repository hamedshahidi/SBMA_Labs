package c.example.sbma_patrik_recycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



const val TAG = "DBG"
const val EXTRA = "extra"

class MainActivity : AppCompatActivity() {

    private lateinit var presidents: List<President>
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DataManager.presidents = populatePresidentList()

        linearLayoutManager = LinearLayoutManager(this)
        rv_presidents.layoutManager = linearLayoutManager
        rv_presidents.adapter = PresidentAdapter()
        Log.d(TAG,"oncreate()")

        val dividerItemDecoration = DividerItemDecoration(
            rv_presidents.context,
            linearLayoutManager.orientation
        )
        rv_presidents.addItemDecoration(dividerItemDecoration)

    }



    fun populatePresidentList(): List<President> {
        return listOf(
           President("Stahlberg, Kaarlo Juho", 1919, 1925, "Eka presidentti"),
           President("Relander, Lauri Kristian", 1925, 1931, "Toka presidentti"),
           President("Svinhufvud, Pehr, Evind", 1931, 1937, "Kolmas presidentti"),
           President("Kallio, Kyosti", 1937, 1940, "Neljas presidentti"),
           President("Ryti, Risto Heikki", 1940, 1944, "Viides presidentti"),
           President("Mannerheim, Carl Gustav Emil", 1944, 1946, "Kuudes presidentti"),
           President("Paasikivi, Juho Kusti", 1946, 1956, "Äkäinen ukko"),
           President("Kekkonen, Urho Kaleva", 1956, 1982, "Pelimies"),
           President("Koivisto, Mauno Henrik", 1982, 1994, "Manu"),
           President("Ahtisaari, Martti Oiva Kalevi", 1994, 2000, "Mahtisaari"),
           President("Halonen, Tarja Kaarina", 2000, 2012, "Eka naispreseidentti"),
           President("Niinistö, Sauli Väinämö", 2012, 2024, "Owner of Lennu, the First Dog")
       )
    }
}
