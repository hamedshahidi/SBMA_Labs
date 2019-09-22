package c.example.sbma_jarkko_lab_6

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.bluetooth.le.*
import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.collections.LinkedHashMap


const val TAG = "BT"

class MainActivity : AppCompatActivity() {


    private lateinit var linearLayoutManager: LinearLayoutManager
    //private lateinit var adapter: RecyclerAdapter

    private lateinit var scanResList: kotlin.collections.MutableSet<ScanResult>
    private lateinit var devices: kotlin.collections.MutableSet<Device>

    private lateinit var mBluetoothAdapter: BluetoothAdapter


    private  var mScanResults: LinkedHashMap<String, ScanResult>? = null


    private lateinit var mScanCallback: ScanCallback
    private lateinit var mBluetoothLeScanner: BluetoothLeScanner
    private lateinit var mHandler: Handler
    private var mScanning: Boolean = false

    companion object {
        const val SCAN_PERIOD: Long = 3000
    }

    private inner class BtleScanCallback : ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult) {
            addScanResult(result)
        }

        override fun onBatchScanResults(results: List<ScanResult>) {
            for (result in results) {
                addScanResult(result)
            }
        }

        override fun onScanFailed(errorCode: Int) {
            Log.d(TAG, "BLE Scan Failed with code $errorCode")
        }

        private fun addScanResult(result: ScanResult) {
            val device = result.device
            val deviceAddress = device.address
            val uuid = device.uuids
            val deviceName = device.name

            mScanResults?.set(deviceAddress, result)
            Log.d(TAG, "Device address: $deviceAddress (${result.isConnectable})")

            /*// check if list already contains the device
            if (!scanResList.map { it.device.address }.contains(result.device.address)){
                scanResList.add(result)
                val newDevice = generateDevice(result)
                devices.add(newDevice)
                //adapter.notifyDataSetChanged()
            }
*/
            Log.d(TAG, scanResList.count().toString())
        }
    }

/*    private fun generateDevice(result: ScanResult): Device{
        val name = result.device.name
        val address = result.device.address
        val power = result.rssi
        val connectable = result.isConnectable
        return Device(name,address,power,connectable)
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bluetoothManager = getSystemService(Context.BLUETOOTH_SERVICE)
                as BluetoothManager
        mBluetoothAdapter = bluetoothManager.adapter

        btnScan.setOnClickListener {
            if (hasPermissions()) {
                startScan()
                btnScan.text = getString(R.string.str_scanning)
                btnScan.background = getDrawable(R.drawable.btn_scan_disable_bg)
                btnScan.isClickable = false
            }
        }

        scanResList = mutableSetOf()
        devices = mutableSetOf()

        linearLayoutManager = LinearLayoutManager(this)
        rvBtDevices.layoutManager = linearLayoutManager

        //rvBtDevices.adapter = RecyclerAdapter(this)

        /*adapter = RecyclerAdapter(devices)
        rvBtDevices.adapter = adapter
        rvBtDevices.addItemDecoration(
            RecyclerAdapter.MarginItemDecoration(
                10
            )
        )*/
    }

    private fun startScan() {
        Log.d("scan", "Scan started...")
        mScanResults = LinkedHashMap()
        mScanCallback = BtleScanCallback()
        mBluetoothLeScanner = mBluetoothAdapter.bluetoothLeScanner

        val settings = ScanSettings.Builder()
            .setScanMode(ScanSettings.SCAN_MODE_LOW_POWER)
            .build()

        val filter: List<ScanFilter?>? = null

        // Stops scanning after a pre-defined scan period
        mHandler = Handler()
        mHandler.postDelayed({ stopScan() }, SCAN_PERIOD)

        mScanning = true
        mBluetoothLeScanner.startScan(filter, settings, mScanCallback)
    }

    private fun stopScan() {
        mBluetoothLeScanner.stopScan(mScanCallback)
        //mScanCallback = null
        //mScanning = null
        btnScan.text = getString(R.string.str_startScan)
        btnScan.background = getDrawable(R.drawable.btn_scan_bg)
        btnScan.isClickable = true
        if (mScanResults!!.count() > 0) {
            BluetoothDataManager.setScanResult(mScanResults)
            rvBtDevices.adapter = RecyclerAdapter(this)
        }
    }

    private fun hasPermissions(): Boolean {
        if (mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled) {
            Log.d("DBG", "No Bluetooth LE capability")
            return false
        } else if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            Log.d("DBG", "No fine location access")
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
            return true
        }
        return true
    }
}