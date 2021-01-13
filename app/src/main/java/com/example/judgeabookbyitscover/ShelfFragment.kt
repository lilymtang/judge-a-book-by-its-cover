package com.example.judgeabookbyitscover

import MarginItemDecoration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.judgeabookbyitscover.model.datamodels.Book
import com.example.judgeabookbyitscover.model.db.BookDatabase
import com.example.judgeabookbyitscover.model.db.Repository
import com.example.judgeabookbyitscover.presenter.ShelfContract
import com.example.judgeabookbyitscover.presenter.ShelfPresenter

class ShelfFragment : Fragment(), ShelfContract.View, HomeAdapter.OnBookListener, DialogCallBack {
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: HomeAdapter

    lateinit var shelfPresenter: ShelfPresenter

    lateinit var books: List<Book>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.tab_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Configure recycler view
        recyclerView = view.findViewById(R.id.cover_gallery)
        recyclerView.addItemDecoration(MarginItemDecoration(2, 10, includeEdge = true))
        recyclerView.layoutManager = GridLayoutManager(activity, 2)

        // Configure adapter
        adapter = HomeAdapter(Glide.with(this), this)
        recyclerView.adapter = adapter

        adapter = HomeAdapter(Glide.with(this), this)
        recyclerView.adapter = adapter

        // Create presenter
        shelfPresenter = ShelfPresenter()
        shelfPresenter.create(this, Repository(BookDatabase.getInstance(activity!!.applicationContext)))

        // Make network request to get book covers to fill gallery
        shelfPresenter.getShelfBooks()
    }

    override fun onResume() {
        super.onResume()

        // Make network request to get book covers to fill gallery
        shelfPresenter.getShelfBooks()
    }

    override fun onResponse(books: List<Book>) {
        adapter.setBooks(books)
        this.books = books
    }

    override fun onFailure(msg: String) {
        Log.d("CLICKED", "msg")
    }

    override fun onDestroy() {
        super.onDestroy()
        shelfPresenter.destroy()
        shelfPresenter.detachView()
    }

    override fun onBookClick(position: Int) {
        Log.d("CLICKED", "clicked $position")
        openBottomSheetDialog(books[position])
    }

    override fun onDialogClick() {
        // Make network request to get book covers to fill gallery
        shelfPresenter.getShelfBooks()
    }

    private fun openBottomSheetDialog(bookClicked: Book) {
        val dialog = DetailDialogFragment.newInstance(bookClicked, "shelf")
        dialog.setTargetFragment(this, DETAIL_DIALOG)
        dialog.show(activity!!.supportFragmentManager, DetailDialogFragment.TAG)
    }

    companion object {
        const val DETAIL_DIALOG = 1
    }

}

interface DialogCallBack {
    fun onDialogClick()
}