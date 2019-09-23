package c.example.sbma_jarkko_lab_6

import android.bluetooth.le.ScanResult

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