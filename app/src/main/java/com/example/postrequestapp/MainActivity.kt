package com.example.postrequestapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class MainActivity : AppCompatActivity() {

    lateinit var bAdd : Button
    lateinit var rvItem : RecyclerView
    lateinit var itemList : ArrayList<String>
    lateinit var rvAdapter: RVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bAdd = findViewById(R.id.bAdd)
        rvItem = findViewById(R.id.rvItems)
        itemList = ArrayList()

        rvAdapter = RVAdapter(itemList)
        rvItem.adapter = rvAdapter
        rvItem.layoutManager = LinearLayoutManager(this)

        getItems()

        bAdd.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)

        }


    }


    fun getItems(){
   val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

        apiInterface?.getItems()?.enqueue(object : Callback<List<Details.Items>> {
            override fun onResponse(
                call: Call<List<Details.Items>>,
                response: Response<List<Details.Items>>
            ) {
                var result: String? = ""
                for (i in response.body()!!) {
                    result = i.name +" \n"+ i.location
                    itemList.add(result!!)
                    rvAdapter.notifyDataSetChanged()
                }
            }
            override fun onFailure(call: Call<List<Details.Items>>, t: Throwable) {
                Log.d("Main", "Unable to get data")
            }
        })
    }
}