package c.example.sbma_jarkko_lab_6

import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCharacteristic
import android.bluetooth.le.ScanResult
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.handler.BleWrapper
import com.jjoe64.graphview.series.DataPoint
import kotlinx.android.synthetic.main.activity_device.*
import kotlinx.android.synthetic.main.device_list_item.view.*

class RecyclerAdapter(val activity: MainActivity): RecyclerView.Adapter<Holder>(), BleWrapper.BleCallback{

    private lateinit var mHandelr : BleWrapper
    var heartRate: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellRow: View = layoutInflater.inflate(R.layout.device_list_item, parent, false)
        return Holder(cellRow)
    }

    override fun getItemCount(): Int {
        return BluetoothDataManager.mScanResult?.size!!.toInt()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val result = BluetoothDataManager.mScanResult?.values?.toList()?.get(position)
        holder.view.tvDeviceAddress.text = result?.scanRecord?.deviceName ?: "No name"
        holder.view.tvDeviceName.text = result?.device?.address ?: "No address"
        holder.view.tvDevicePower.text = result?.rssi.toString() + "dBm"


        holder.view.setOnClickListener{
            mHandelr = BleWrapper(holder.view.context, result!!.device.address)
            mHandelr.addListener(this)
            mHandelr.connect(false)

            val context = holder.itemView.context
            val graphIntent = Intent(context, GraphActivity::class.java)
            val rateArray = BluetoothDataManager.heartRateArray
            graphIntent.putExtra("heartRate", rateArray)
            context.startActivity(graphIntent)

            println("blablabla")
            Log.d("DBG",heartRate.toString())
        }
    }

    override fun onDeviceReady(gatt: BluetoothGatt) {
        mHandelr.getNotifications(gatt, mHandelr.HEART_RATE_SERVICE_UUID,
            mHandelr.BAROMETRIC_PRESSURE_MEASUREMENT_CHAR_UUID)
    }

    override fun onDeviceDisconnected() {}

    override fun onNotify(characteristic: BluetoothGattCharacteristic) {
        heartRate = characteristic.value[1].toInt()
        Log.d("DBG", heartRate.toString())
        activity.tv_heartRate.text = heartRate.toString() + "bpm"
        BluetoothDataManager.addToArray(heartRate)
    }
}

class Holder (val view: View): RecyclerView.ViewHolder(view)

object BluetoothDataManager{
    var mScanResult: LinkedHashMap<String, ScanResult>? = null
    var heartRateArray: Array<Int> = arrayOf()

    fun setScanResult(results: LinkedHashMap<String, ScanResult>?){
        mScanResult = results
    }
    fun addToArray(value: Int){
        heartRateArray += value
    }
}