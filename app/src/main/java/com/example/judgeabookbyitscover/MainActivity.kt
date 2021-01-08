package com.example.judgeabookbyitscover
import android.os.Bundle
import android.util.Log

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

// API imports
import com.example.judgeabookbyitscover.network.Volume
import com.example.judgeabookbyitscover.network.GoogleBookApi
import com.example.judgeabookbyitscover.network.VolumeApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class MainActivity() : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var coverGalleryAdapter: CoverGalleryAdapter
    lateinit var manager: StaggeredGridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.cover_gallery)
        recyclerView.addItemDecoration(
            MarginItemDecoration(8)
        )
        manager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = manager

        // list of images
        var data = arrayOf<Int> (
            R.drawable.educated,
            R.drawable.less,
            R.drawable.pachinko,
            R.drawable.into_thin_air,
            R.drawable.coddling,
            R.drawable.martian,
            R.drawable.midnight,
            R.drawable.bookthief,
            R.drawable.crazyrichasians,
            R.drawable.darkmatter,
            R.drawable.light,
            R.drawable.promisedland
        )

        coverGalleryAdapter = CoverGalleryAdapter(this, data)
        recyclerView.adapter = coverGalleryAdapter

        val service = VolumeApi.volumeApiService

        /* Calls the endpoint set on getUsers (/api) from UserService using enqueue method
         * that creates a new worker thread to make the HTTP call */
        service.getVolume( "DapMtgEACAAJ").enqueue(object : Callback<Volume> {

            /* The HTTP call failed. This method is run on the main thread */
            override fun onFailure(call: Call<Volume>, t: Throwable) {
                Log.d("TAG_", "An error happened!")
                Log.d("TAG_", call.request().url().toString())
                t.printStackTrace()
            }

            /* The HTTP call was successful, we should still check status code and response body
             * on a production app. This method is run on the main thread */
            override fun onResponse(call: Call<Volume>, response: Response<Volume>) {
                /* This will print the response of the network call to the Logcat */
                Log.d("TAG_", response.body()?.volumeInfo?.imageLinks?.thumbnail.toString())
                Log.d("TAG_", call.request().url().toString())
            }
        })
    }
}

