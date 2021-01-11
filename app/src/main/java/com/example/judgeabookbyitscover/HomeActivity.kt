package com.example.judgeabookbyitscover

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.judgeabookbyitscover.model.Repository
import com.example.judgeabookbyitscover.model.datamodels.Book
import com.example.judgeabookbyitscover.presenter.HomeContract
import com.example.judgeabookbyitscover.presenter.HomePresenter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

// API imports

class HomeActivity() : AppCompatActivity(R.layout.detail), HomeContract.View, HomeAdapter.OnBookListener {
    lateinit var homePresenter: HomePresenter
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: HomeAdapter
    lateinit var repository: Repository
    lateinit var books: List<Book>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        if (savedInstanceState == null) {
//            supportFragmentManager.commit {
//                setReorderingAllowed(true)
//                add<DetailDialogFragment>(R.id.fragment_container_view)
////                addToBackStack("name")
//            }
//        }

        // Configure recycler view
        recyclerView = findViewById(R.id.cover_gallery)
        recyclerView.addItemDecoration(MarginItemDecoration(8))
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        adapter = HomeAdapter(Glide.with(this), this)
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

    override fun onGetDataSuccess(books: List<Book>) {
        adapter.setData(books)
        this.books = books
    }

    override fun onGetDataError(msg: String) {
        Log.d("CLICKED", "msg")
    }

    override fun onBookClick(position: Int) {
//        var intent: Intent = Intent()
//        startActivity(intent)
        Log.d("CLICKED", "clicked $position")
        openBottomSheetDialog(books[position])
    }

    private fun openBottomSheetDialog(bookClicked: Book) {
        DetailDialogFragment
                .newInstance(bookClicked.title, bookClicked.author, bookClicked.description)
                .show(supportFragmentManager, DetailDialogFragment.TAG)
    }


}

