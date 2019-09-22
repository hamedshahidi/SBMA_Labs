package com.example.kotlinadapter

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.telecom.Call
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.gson.annotations.SerializedName
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupPermissions()

        //callWebService()

        // create the adapter to convert the array to views
        val adapter = PresidentListAdapter(this, GlobalModel.presidents)

        // use a custom layout (instead of the ListActivity default layout)
        setContentView(R.layout.activity_main)

        // attach the adapter to a ListView
        mainlistview.adapter = adapter

        mainlistview.setOnItemClickListener { _, _, position, _ ->
            Log.d("USR", "Selected $position")
            selname.text = GlobalModel.presidents[position].toString()
            seldescription.text = GlobalModel.presidents[position].description

            var president = GlobalModel.presidents[position]
            callWebService(president.name)


        }

        mainlistview.setOnItemLongClickListener { _, _, position, _ ->
            val selectedPresident = GlobalModel.presidents[position]
            val detailIntent = PresidentDetailActivity.newIntent(this, selectedPresident)

            startActivity(detailIntent)
            true
        }

    }

    private fun setupPermissions() {
        val permission = ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET)
        if (permission != PackageManager.PERMISSION_GRANTED){
            Log.d("DBG", "No internet permission")
        } else {
            Log.d("DBG", "has internet permission")
        }
    }

    private inner class PresidentListAdapter(context: Context, private val presidents: MutableList<President>) : BaseAdapter() {
        private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        override fun getCount(): Int {
            return presidents.size
        }

        override fun getItem(position: Int): Any {
            return presidents[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val rowView = inflater.inflate(R.layout.item_president, parent, false)

            val thisPresident = presidents[position]
            var tv = rowView.findViewById(R.id.tvName) as TextView
            tv.text = thisPresident.name

            tv = rowView.findViewById(R.id.tvStartDuty) as TextView
            tv.text = Integer.toString(thisPresident.startDuty)

            tv = rowView.findViewById(R.id.tvEndDuty) as TextView
            tv.text = Integer.toString(thisPresident.endDuty)

            return rowView
        }
    }
    object DemoApi {
        const val URL = "https://en.wikipedia.org/w/"

        object Model {
            data class Query(@SerializedName("query") val query: Searchinfo )
            data class Searchinfo(@SerializedName("searchinfo") val searchInfo: Totalhits )
            data class Totalhits(@SerializedName("totalhits") val hits: Int )
        }

        interface Service {
            @GET("api.php")
            fun myFun(@Query("action") action: String,
                      @Query("format") format: String,
                      @Query("list") list: String,
                      @Query("srsearch") srsearch: String
            ): retrofit2.Call<Model.Query>
        }

        private val retrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(Service::class.java)

    }

    fun callWebService(searchParam: String) {
        val call = DemoApi.service.myFun(
            "query",
            "json",
            "search",
            searchParam)

        val value = object : Callback<DemoApi.Model.Query> {
            override fun onResponse(
                call: retrofit2.Call<DemoApi.Model.Query>,
                response: Response<DemoApi.Model.Query>?
            ) {
                if (response != null) {
                    var res: DemoApi.Model.Query = response.body()!!
                    //?: throw Exception("No result")

                    tv_hits.text = "Hits: ${res.query.searchInfo.hits.toString()}"

                    Log.d("DBG", res.query.toString())
                    Log.d("DBG", "Total hits: ${res.query.searchInfo.hits.toString()}")
                }
            }

            override fun onFailure(call: retrofit2.Call<DemoApi.Model.Query>, t: Throwable) {
                Log.d("err", t.message)
            }
        }

        call.enqueue(value)

    }
}


