package com.example.judgeabookbyitscover
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.judgeabookbyitscover.model.Repository
import com.example.judgeabookbyitscover.presenter.HomeContract
import com.example.judgeabookbyitscover.presenter.HomePresenter

// API imports

class HomeActivity() : AppCompatActivity(), HomeContract.View {
    lateinit var homePresenter: HomePresenter
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: HomeAdapter
    lateinit var repository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Configure recycler view
        recyclerView = findViewById(R.id.cover_gallery)
        recyclerView.addItemDecoration(MarginItemDecoration(8))
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        adapter = HomeAdapter(Glide.with(this))
        recyclerView.adapter = adapter

        // Create repository
        repository = Repository()

        // Create presenter
        homePresenter = HomePresenter()
        homePresenter.create(this, repository)

        // Get book covers to fill gallery
        homePresenter.getBooks()
    }

    override fun onDestroy() {
        super.onDestroy()
        homePresenter.destroy()
    }

    override fun onGetDataSuccess(books: List<String>) {
        adapter.setData(books)
    }

    override fun onGetDataError(msg: String) {
        TODO("Not yet implemented")
    }
}

