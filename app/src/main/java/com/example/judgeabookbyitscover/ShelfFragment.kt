package com.example.judgeabookbyitscover

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.judgeabookbyitscover.model.datamodels.Book
import com.example.judgeabookbyitscover.model.db.BookRepository
import com.example.judgeabookbyitscover.presenter.HomeContract
import com.example.judgeabookbyitscover.presenter.HomePresenter

class ShelfFragment : Fragment(), HomeContract.View, HomeAdapter.OnBookListener {
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: HomeAdapter

    lateinit var homePresenter: HomePresenter
    lateinit var bookRepository: BookRepository

    lateinit var books: List<Book>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.home_activity, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Configure recycler view
        recyclerView = view.findViewById(R.id.cover_gallery)
        recyclerView.addItemDecoration(MarginItemDecoration(8))
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        // Configure adapter
        adapter = HomeAdapter(Glide.with(this), this)
        recyclerView.adapter = adapter

        // Configure recycler view
        recyclerView = view.findViewById(R.id.cover_gallery)
        recyclerView.addItemDecoration(MarginItemDecoration(8))
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        adapter = HomeAdapter(Glide.with(this), this)
        recyclerView.adapter = adapter

        // Create repository
        bookRepository = BookRepository()

        // Create presenter
        homePresenter = HomePresenter()
        homePresenter.create(this, bookRepository)

        // Create Room database
        // val bookDatabase: BookDatabase = BookDatabase.getInstance(homePresenter.getContext())
        // bookDatabase.bookDao().insert(book)

        // Make network request to get book covers to fill gallery
        homePresenter.getBooks()
    }

    override fun onResponse(books: List<Book>) {
        adapter.setData(books)
        this.books = books
    }

    override fun onFailure(msg: String) {
        Log.d("CLICKED", "msg")
    }

    override fun onDestroy() {
        super.onDestroy()
        homePresenter.destroy()
    }

    override fun onBookClick(position: Int) {
        Log.d("CLICKED", "clicked $position")
        openBottomSheetDialog(books[position])
    }

    private fun openBottomSheetDialog(bookClicked: Book) {
        DetailDialogFragment
                .newInstance(bookClicked)
                .show(activity!!.supportFragmentManager, DetailDialogFragment.TAG)
    }
}