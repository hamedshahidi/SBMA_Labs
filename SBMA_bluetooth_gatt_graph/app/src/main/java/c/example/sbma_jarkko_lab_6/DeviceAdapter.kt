// adapter used for bluetooth lab1


/*
package c.example.sbma_jarkko_lab_6

import android.content.Intent
import android.graphics.Rect
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_device.view.*
import kotlinx.android.synthetic.main.device_list_item.view.*

const val TAG1 = "BTA"

class RecyclerAdapter1(private val devices: MutableSet<Device>) :
    RecyclerView.Adapter<RecyclerAdapter.DeviceHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerAdapter.DeviceHolder {

        Log.d(TAG1, "onCtearView() adapter")

        val inflatedView = parent.inflate(R.layout.device_list_item, false)
        return DeviceHolder(inflatedView)
    }

    override fun getItemCount() = devices.size

    override fun onBindViewHolder(holder: RecyclerAdapter.DeviceHolder, position: Int) {
        mDevice = devices.elementAt(position)
        //holder.setclickListener(mDevice)
        holder.populateUI(mDevice)
    }


    inner class DeviceHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

        private var view: View = v
        //private var device: Device? = null

*/
/*        init {
            v.setOnClickListener(this)
        }*//*


        override fun onClick(v: View) {
            Log.d("RecyclerView", "CLICK!")
            val context = itemView.context
            val showDevice = Intent(context, DeviceActivity::class.java)
            //Log.d("dv",mDevice.toString())
            showDevice.putExtra(OBJECT_KEY, mDevice)
            context.startActivity(showDevice)
        }

        fun populateUI(device: Device) {

            Log.d(TAG1, "populateUI()")

            view.tvDeviceAddress.text = device.address
            view.tvDeviceName.text = device.name
            view.tvDevicePower.text = device.power.toString()
        }

        fun setclickListener(device: Device){
            view.btnConnect.setOnClickListener {
                val context = itemView.context
                val showDevice = Intent(context, DeviceActivity::class.java)
                //Log.d("dv",mDevice.toString())
                showDevice.putExtra(OBJECT_KEY, mDevice)
                context.startActivity(showDevice)
            }
        }


*/
/*        val clickListener = View.OnClickListener {view ->

            when (view.getId()) {
                R.id.textview -> firstFun()
                R.id.button -> secondFun()
            }
        }*//*

    }

    class MarginItemDecoration(private val spaceHeight: Int) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect, view: View,
            parent: RecyclerView, state: RecyclerView.State
        ) {
            with(outRect) {
                if (parent.getChildAdapterPosition(view) == 0) {
                    top = spaceHeight
                }
                left = spaceHeight
                right = spaceHeight
                bottom = spaceHeight
            }
        }
    }

    companion object {
        private val OBJECT_KEY = "DEVICE"
        private lateinit var mDevice: Device
    }

}





*/
