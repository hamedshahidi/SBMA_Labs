package c.example.sbma_patrik_recycler

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*

class PresidentAdapter(): RecyclerView.Adapter<Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        Log.d(TAG, "adapter()")
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellRow = layoutInflater.inflate(R.layout.list_item, parent, false)
            as LinearLayout
        return Holder(cellRow)
    }

    override fun getItemCount(): Int {
        Log.d(TAG, (DataManager.presidents?.count() ?: 0).toString())
        return DataManager.presidents?.count() ?: 0

    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val name = DataManager.presidents?.get(position)?.name ?: "No name"
        holder.view.tv_name.text = name
        Log.d(TAG, "im onBindViewHolder()")

        holder.view.setOnClickListener{
            val intent = Intent( holder.view.context, PresidentDetailActivity::class.java )
            intent.putExtra(EXTRA,position)
            holder.view.context.startActivity(intent)
        }
    }


}

class Holder (val view: View): RecyclerView.ViewHolder(view)

object DataManager {
    var presidents: List<President>? = null
    fun getPresidentList(list: List<President>) {
        presidents = list
    }
}
