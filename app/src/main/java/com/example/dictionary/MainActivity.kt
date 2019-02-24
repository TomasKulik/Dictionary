package com.example.dictionary

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView_main.layoutManager = LinearLayoutManager(this)
//        recyclerView_main.adapter = MainAdapter()

        fetchJson()
    }

    fun fetchJson(){
        val url = "https://jisho.org/api/v1/search/words?keyword=house"

        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body()?.string()
                println(body)

				val gson = GsonBuilder().create()

				val results = gson.fromJson(body, Results::class.java)

				runOnUiThread {
					recyclerView_main.adapter = MainAdapter(results)
				}
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }
        })
    }
}

class Results(val data: List<Data>)

class Data(val slug: String, val is_common: String, val japanese: List<Japanese>, val senses: List<Senses>)

class Japanese(val word: String, val reading: String)

class Senses(val english_definitions: List<String>, val parts_of_speech: List<String>)