package c.example.sbma_patrik_sensor_1

import android.annotation.SuppressLint
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

const val TAG = "SENSOR "

class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var sm: SensorManager
    private var sGravity: Sensor? = null
    private var sStepCounter: Sensor? = null
    private var sLight: Sensor? = null
    private var sOrientation: Sensor? = null
    private var preSteps: Long = 0
    private var steps: Long = 0
    private var reset: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sm = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        val allSensors = sm.getSensorList(Sensor.TYPE_ALL)
        allSensors.forEach {
            //val test = if(it.name == "test") "YES" else "NO"

            if (it.type == Sensor.TYPE_GRAVITY) sGravity = it
            //sm.getDefaultSensor(Sensor.TYPE_GRAVITY)
            if (it.type == Sensor.TYPE_LIGHT) sLight = it
            //sm.getDefaultSensor(Sensor.TYPE_LIGHT)
            if (it.type == Sensor.TYPE_STEP_COUNTER) sStepCounter = it
            //sm.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
        }

        btnResetSteps.setOnClickListener(clickListener)
    }


    private val clickListener = View.OnClickListener { view ->
        reset = true
        tvStepCounter.text = "0"
    }


override fun onResume() {
    super.onResume()
    sGravity?.also {
        sm.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
    }
    sLight?.also {
        sm.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
    }
    sStepCounter?.also {
        sm.registerListener(this, it, SensorManager.SENSOR_DELAY_FASTEST)
    }
}

override fun onPause() {
    super.onPause()
    sm.unregisterListener(this)
}

override fun onAccuracyChanged(p0: Sensor?, p1: Int) {}

override fun onSensorChanged(se: SensorEvent?) {

    if (se != null && se.sensor == sStepCounter && reset) {
        preSteps = se.values[0].toLong()
        reset = false
    }
    populateUI(se)
}

@SuppressLint("SetTextI18n")
private fun populateUI(se: SensorEvent?) {

    when (se?.sensor) {
        sGravity -> {
            tvGravity.text = (se?.values?.get(2) ?: -1f).toString() + " m/s2"
        }
        sLight -> {
            tvLight.text = (se?.values?.get(0) ?: -1f).toString() + " lx"
            //dimScrean()
        }
        sStepCounter -> {
            steps = ((se?.values?.get(0) ?: 0f).toLong()) - preSteps
            tvStepCounter.text = "$steps Steps"
            Log.d("DBG", preSteps.toString())
        }
    }


// ==================================================================================
// for development only
// ==================================================================================
    if (se?.sensor == sStepCounter) {
        Log.d(TAG, "Sensor name: " + se?.sensor?.name)
        Log.d(TAG, (se?.values?.get(0) ?: -1f)?.toString())
    }
}

/*    private fun dimScrean() {
        Settings.System.putInt(
            contentResolver,
            Settings.System.SCREEN_BRIGHTNESS, 20
        )


        val lp = window.attributes
        lp.screenBrightness = 0.2f// 100 / 100.0f;
        window.attributes = lp

        startActivity(Intent(this, RefreshScreen.javaClass))
    }*/
}
