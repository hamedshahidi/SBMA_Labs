package c.example.sbma_patrik_demo_network

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.ConnectivityManager
import android.os.*
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.io.BufferedInputStream
import java.io.FileInputStream
import java.io.InputStream
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.widget.ImageView
import java.io.Flushable


class MainActivity : AppCompatActivity() {

    // short version of function
    //private fun test(tt:String) = "hello ${tt}"

    data class URLParams(val url: URL, val name: String)
    data class FinalBitmap(val bitmap: Bitmap, val title: String)

    inner class GetConnection : AsyncTask<URLParams, Unit, FinalBitmap>() {
        // urlParams --> unit
        override fun doInBackground(vararg urlp: URLParams): FinalBitmap {
            lateinit var result: FinalBitmap
            try {
                val myConnection =
                    urlp[0].url.openConnection() as HttpURLConnection
                /*myConnection.requestMethod = "POST"
                myConnection.doOutput = true
                myConnection.connect()

                val outStream = myConnection.outputStream
                outStream.bufferedWriter().use { }
                */

                val inStream: InputStream = myConnection.inputStream

                val bitmap = BitmapFactory.decodeStream(inStream)
                val title = urlp[0].name

                result = FinalBitmap(bitmap, title)
                //inStream.close()
                //bitmap = bm
                //Log.d("bitmap", bitmap.toString())

                /*val allText = inStream.bufferedReader().use { it.readText() }
                val result = StringBuilder()
                result.append(allText)
                str = result.toString()*/

            } catch (e: Exception) {
                Log.e("Background Connection", e.toString())
            }
            return result
        }

        override fun onPostExecute(result: FinalBitmap) {
            super.onPostExecute(result)
            imageView01.setImageBitmap(result.bitmap)
            tv01.text = result.title
            Log.d("onPostExecute", "running")
        }
    }

    private val mHandler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(inputMessage: Message) {
            if (inputMessage.what == 0) {
                var txt = inputMessage.obj.toString()
                    .substringAfter("{\"")
                    .substringBefore("\":")
                if (txt.substringBefore(":") == "notImageError()"){
                    txt = txt.substringAfter(":")
                        .replace("_"," ")
                        .replace("\\", "")
                }
                tv01.text = txt
                imageView01.setImageBitmap(null)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        btnText.setOnClickListener {
            if (isNetworkAvailable()) {
                val txt = et01.text.toString()
                val myRunnable = Connection(mHandler, txt)
                val myThread = Thread(myRunnable)
                myThread.start()
            }
        }

        btnImage.setOnClickListener {
            if (isNetworkAvailable()) {
                val url = et01.text.toString()

                val iType = url.substringAfterLast(".")

                if (iType != "jpg" && iType != "JPG" && iType != "gif" && iType != "GIF"
                    && iType != "bmp" && iType != "BMP" && iType != "png" && iType != "PNG"
                    && iType != "jpeg" && iType != "JPEG"){
                    notImageError()
                } else {
                    getImageFor(url)
                }
            }
        }
    }


    private fun getImageFor(url: String){
        val myURLParams = URLParams(
            URL(
                url
            ),
            "Here is the downloaded image:"
        )
        GetConnection().execute(myURLParams)
        Log.d("BtnImage", "GetConnection().execute()")
    }

    private fun notImageError(){
        val txt = "notImageError():Please paste a URL to an image. ( jpg / jpeg / gif / png )"
        val myRunnable = Connection(mHandler, txt)
        val myThread = Thread(myRunnable)
        myThread.start()
    }

    private fun isNetworkAvailable(): Boolean {
        val cm = this.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        return cm.activeNetworkInfo?.isConnected == true
    }
}
