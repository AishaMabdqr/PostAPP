package com.example.postrequestapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DeleteUpdateActivity : AppCompatActivity() {

    lateinit var eUserId : EditText
    lateinit var eName2 : EditText
    lateinit var eLoc2 : EditText
    lateinit var bDel : Button
    lateinit var bUpdate : Button

//    var userID : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_update)

        eUserId = findViewById(R.id.eUserId)
        eName2 = findViewById(R.id.eName2)
        eLoc2 = findViewById(R.id.eLocation2)
        bDel = findViewById(R.id.bDel)
        bUpdate = findViewById(R.id.bUpdate)

//        userID = eUserId.text.toString()



        bDel.setOnClickListener {
             deleteUser()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        bUpdate.setOnClickListener {
            updateUser()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    fun deleteUser(){
        val userID = eUserId.text.toString()
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        apiInterface?.deleteUser(userID.toInt())?.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Toast.makeText(this@DeleteUpdateActivity,"User deleted", Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@DeleteUpdateActivity,"Something went wrong", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun updateUser(){
        val userID = eUserId.text.toString()
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)

        apiInterface?.updateUser(userID.toInt(),
            Details.Items(eName2.text.toString(), eLoc2.text.toString(), userID.toInt()
        ))?.enqueue(object : Callback<Details.Items> {
            override fun onResponse(call: Call<Details.Items>, response: Response<Details.Items>) {
                Toast.makeText(this@DeleteUpdateActivity,"User updated", Toast.LENGTH_LONG).show()

            }

            override fun onFailure(call: Call<Details.Items>, t: Throwable) {
                Toast.makeText(this@DeleteUpdateActivity,"Something went wrong", Toast.LENGTH_LONG).show()
            }


        })
}}