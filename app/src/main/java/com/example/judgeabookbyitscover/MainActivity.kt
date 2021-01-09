package com.example.judgeabookbyitscover
import android.os.Bundle
import android.util.Log

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.judgeabookbyitscover.network.Book
import com.example.judgeabookbyitscover.network.BooksApi
import com.example.judgeabookbyitscover.network.ListsOverview

// API imports
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        coverGalleryAdapter = CoverGalleryAdapter(Glide.with(this))
        recyclerView.adapter = coverGalleryAdapter

        val service = BooksApi.BooksApiService
//        lateinit var books : List<Book>

        /* Calls the endpoint set on getUsers (/api) from UserService using enqueue method
         * that creates a new worker thread to make the HTTP call */
        service.getListsOverview().enqueue(object : Callback<ListsOverview> {

            /* The HTTP call failed. This method is run on the main thread */
            override fun onFailure(call: Call<ListsOverview>, t: Throwable) {
                val message: String = t.message ?: "Error happened"
                Log.d("RESPONSE_", message)
                Log.d("RESPONSE_", call.request().url().toString())
                t.printStackTrace()
            }

            /* The HTTP call was successful, we should still check status code and response body
             * on a production app. This method is run on the main thread */
            override fun onResponse(call: Call<ListsOverview>, response: Response<ListsOverview>) {
                /* This will print the response of the network call to the Logcat */
                Log.d("RESPONSE_", call.request().url().toString())

                var lists = response.body()?.results?.lists
                if (lists != null) {
//                    books = lists.flatMap { it.books }
                    var bookImgs: List<String> = lists.flatMap { it.books }.map { it.book_image }.distinct()

                    Log.d("RESPONSE_", bookImgs.toString())
                    Log.d("RESPONSE_", bookImgs.size.toString())

                    coverGalleryAdapter.setData(bookImgs)
                }
            }
        })

        // list of images
//        var data = arrayOf<Int> (
//            R.drawable.educated,
//            R.drawable.less,
//            R.drawable.pachinko,
//            R.drawable.into_thin_air,
//            R.drawable.coddling,
//            R.drawable.martian,
//            R.drawable.midnight,
//            R.drawable.bookthief,
//            R.drawable.crazyrichasians,
//            R.drawable.darkmatter,
//            R.drawable.light,
//            R.drawable.promisedland
//        )

    }
}

