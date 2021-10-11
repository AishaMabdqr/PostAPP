package com.example.postrequestapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddActivity : AppCompatActivity() {

    lateinit var eName : EditText
    lateinit var eLocation : EditText
    lateinit var bSave : Button
    lateinit var bView : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        eName = findViewById(R.id.eName)
        eLocation = findViewById(R.id.eLocation)
        bSave = findViewById(R.id.bSave)
        bView = findViewById(R.id.bView)


        bView.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        bSave.setOnClickListener {
            addItems()
        }

    }

    fun addItems(){
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

        apiInterface?.addItems(AddDetails(eName.text.toString(), eLocation.text.toString()))?.enqueue(object: Callback<AddDetails> {
            override fun onResponse(call: Call<AddDetails>, response: Response<AddDetails>) {
                Toast.makeText(this@AddActivity,"User Added", Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<AddDetails>, t: Throwable) {
                Toast.makeText(this@AddActivity,"Something went wrong", Toast.LENGTH_LONG).show()
            }

        })

    }
}