package com.example.retrofitokhttp304bug

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.InetSocketAddress
import java.net.Proxy


class MainActivity : AppCompatActivity() {

    private val cacheSize = 10 * 1024 * 1024L // 10MB

    private lateinit var okhttp: OkHttpClient
    private lateinit var retrofit: Retrofit
    private lateinit var service: PostApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        okhttp = setupOkHttp()
        retrofit = setupRetrofit()
        service = setupPostApiService()

        findViewById<Button>(R.id.button).setOnClickListener {
            Thread {
                val result = service.fetchPostDO("/posts/cf08ab872bee3291a3aaffbc0c376441?ts=1565278077").execute()

                Log.d("MainActivity", "MainActivity result:$result")
            }.start()
        }
    }

    private fun setupOkHttp(): OkHttpClient {
        val builder = OkHttpClient.Builder()

                //Uncomment the next line to setup a proxy server (for example Charles Proxy)
                //.proxy(Proxy(Proxy.Type.HTTP, InetSocketAddress("192.168.1.1", 8888)))

                .cache(Cache(cacheDir, cacheSize))

                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
        return builder.build()
    }

    private fun setupRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://api.rb-fe.nuglif.net")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okhttp)
                .build()
    }

    private fun setupPostApiService(): PostApi {
        return retrofit.create(PostApi::class.java)
    }
}
