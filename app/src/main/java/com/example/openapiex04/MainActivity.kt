package com.example.openapiex04

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.Callback
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = Adapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val client = OkHttpClient.Builder()
            .callTimeout(1000, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://openapi.airkorea.or.kr/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        btnGet.setOnClickListener {
            val apiService = retrofit.create(OpenApiInterface::class.java)
            val request = apiService.getMsrstnList(
                getString(R.string.api_key),
                "JSON",
                "",""
            )
            request.enqueue(object : retrofit2.Callback<Station> {
                override fun onResponse(call: Call<Station>, response: Response<Station>) {
                    val result = response.body() as Station

                    adapter.list.clear()
                    adapter.list.addAll(result.list)
                    adapter.notifyDataSetChanged()
                }

                override fun onFailure(call: Call<Station>, t: Throwable) {

                    Log.e("xx", t.toString())
                }
            })
        }
    }

}



interface OpenApiInterface {
    @GET("openapi/services/rest/MsrstnInfoInqireSvc/getMsrstnList")
    fun getMsrstnList(
        @Query("ServiceKey", encoded = true)
        ServiceKey : String,
        @Query("_returnType")
        returnType : String,
        @Query("tmX")
        tmX : String,
        @Query("tmY")
        tmY : String
    ) : Call<Station>
}

