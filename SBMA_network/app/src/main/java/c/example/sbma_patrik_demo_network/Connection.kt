package c.example.sbma_patrik_demo_network

import android.os.Handler
import android.util.Log
import java.io.InputStream
import java.io.OutputStream
import java.lang.Exception
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL

class Connection(
    mHand: Handler,
    val txt: String
) : Runnable {

    val myHandler = mHand

    override fun run() {
        try {
            val myUrl = URL("https://users.metropolia.fi/~hamedy/hamed_network.php")
            val myConnection = myUrl.openConnection() as HttpURLConnection
            myConnection.requestMethod = "POST"
            myConnection.doOutput = true
            val outStream: OutputStream = myConnection.outputStream
            outStream.bufferedWriter().use { it.write(txt) }

            val inStream: InputStream = myConnection.inputStream
            val allText = inStream.bufferedReader().use { it.readText() }
            val result = StringBuilder()
            result.append(allText)
            val str = result.toString()
            Log.d("txt", str)

            val msg = myHandler.obtainMessage()
            msg.what = 0
            msg.obj = str
            myHandler.sendMessage(msg)
        } catch (e: Exception) {
            Log.e("conn_error", "Connection error")
        }
    }
}