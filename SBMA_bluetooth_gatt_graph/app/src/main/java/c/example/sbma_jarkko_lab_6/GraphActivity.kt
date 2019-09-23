package c.example.sbma_jarkko_lab_6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import kotlinx.android.synthetic.main.activity_graph.*

class GraphActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graph)
        val timePoints = IntArray(BluetoothDataManager.heartRateArray.size){it}
        val dataPoints = Array(BluetoothDataManager.heartRateArray.size,
            {DataPoint(timePoints[it].toDouble(),
                BluetoothDataManager.heartRateArray[it].toDouble()) })
        graph.addSeries(LineGraphSeries<DataPoint>(dataPoints))
        graph.gridLabelRenderer.horizontalAxisTitle = "TIME"
        graph.gridLabelRenderer.verticalAxisTitle = "BPM"
        graph.gridLabelRenderer.setHumanRounding(true)
        graph.viewport.isXAxisBoundsManual = true
    }
}
