

package c.example.sbma_jarkko_lab_6

import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCharacteristic
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.handler.BleWrapper
import kotlinx.android.synthetic.main.activity_device.*
import kotlin.math.log

class DeviceActivity : AppCompatActivity(), BleWrapper.BleCallback {

    private lateinit var mBleWrapper: BleWrapper
    var heartRate: Int = 0

    override fun onDeviceReady(gatt: BluetoothGatt) {
        mBleWrapper.getNotifications(gatt, mBleWrapper.HEART_RATE_SERVICE_UUID,
            mBleWrapper.BAROMETRIC_PRESSURE_MEASUREMENT_CHAR_UUID)
    }

    override fun onDeviceDisconnected() {
    }

    override fun onNotify(characteristic: BluetoothGattCharacteristic) {
        heartRate = characteristic.value[1].toInt()
        Log.d("DBG", heartRate.toString())
        tv_heartRate.text = heartRate.toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device)

        val device = intent.extras.getSerializable("DEVICE") as Device

        tvName.text = device.name ?: "Device name is not available"
        tvAddress.text = device.address
        tvPower.text = device.power.toString()

        btnConnect.setOnClickListener {
            mBleWrapper = BleWrapper(this, device!!.address)
            //mBleWrapper!!.addListener(this)
            mBleWrapper!!.connect(false)
        }
    }




}
