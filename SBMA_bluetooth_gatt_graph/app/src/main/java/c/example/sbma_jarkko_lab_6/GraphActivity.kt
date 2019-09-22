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

        val rateArray = intent.extras.getIntArray("heartRate")


        // under development...
        val a = rateArray.map {it ->
            Array<DataPoint>(rateArray.size) { DataPoint(it.toDouble(), 2.0*it.toDouble()) }
        }

        var dataPoints : Array<DataPoint> = arrayOf<DataPoint>()
        for (i in rateArray.indices){
            val rate = rateArray[i]
            dataPoints[i] = DataPoint(rate.toDouble(), 2.0*rate.toDouble())
        }
        //val datapoints = Array<DataPoint>(5, {DataPoint(it.toDouble(), 2.0*it.toDouble())})

        graph.addSeries(LineGraphSeries<DataPoint>(dataPoints))
    }
}
