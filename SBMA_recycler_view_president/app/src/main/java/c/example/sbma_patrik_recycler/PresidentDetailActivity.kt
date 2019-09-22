package c.example.sbma_patrik_recycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_president_detail.*
import kotlinx.android.synthetic.main.list_item.*
import kotlinx.android.synthetic.main.list_item.tv_name

class PresidentDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_president_detail)

        val position = intent.getIntExtra(EXTRA , 0)

        val president = DataManager.presidents?.get(position)

        tv_name.text = president?.name ?: "No name"
        tv_start.text = president?.start.toString()
        tv_end.text = president?.end.toString()
        tv_desc.text = president?.description ?: "No description"
    }
}
