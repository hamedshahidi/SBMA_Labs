package c.example.sbma_kari_lab_1

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){

    lateinit var frag01: Frag01
    lateinit var frag02: Frag02
    lateinit var frag03: Frag03
    lateinit var fManager: FragmentManager
    lateinit var fTransaction: FragmentTransaction
    lateinit var mp: MediaPlayer

    var flag: Int = 3
//    var btn01: Button = findViewById(R.id.btn_happy)
//    var btn02: Button = findViewById(R.id.btn_sad)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_happy.setOnClickListener { swapFrags(1) }
        btn_sad.setOnClickListener { swapFrags(2) }
        btnFab.setOnClickListener { playSound() }
        btnFab1.setOnClickListener {
            val intent = Intent(this, CardActivity::class.java)
            startActivity(intent)
        }

        frag01 = Frag01()
        frag02 = Frag02()
        frag03 = Frag03()
        fManager = supportFragmentManager
        fTransaction = fManager.beginTransaction()
        fTransaction.add(R.id.fContainer, frag03)
        fTransaction.commit()
    }

    private fun playSound() {
        mp = MediaPlayer.create(this, R.raw.stop_touching_me )
        mp.start()
    }

    private fun swapFrags(num: Int) {

        when (num) {
            1 -> {
                fTransaction = fManager.beginTransaction()
                fTransaction.replace(R.id.fContainer, frag01)
                fTransaction.addToBackStack(null)
                fTransaction.commit()
            }
            2 -> {
                fTransaction = fManager.beginTransaction()
                fTransaction.replace(R.id.fContainer, frag02)
                fTransaction.addToBackStack(null)
                fTransaction.commit()
            }
            3 -> {
                fTransaction = fManager.beginTransaction()
                fTransaction.replace(R.id.fContainer, frag03)
                fTransaction.addToBackStack(null)
                fTransaction.commit()
            }
        }
    }
}
