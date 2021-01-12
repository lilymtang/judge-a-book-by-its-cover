package com.example.judgeabookbyitscover

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Toast
import com.example.judgeabookbyitscover.model.datamodels.Book
import com.example.judgeabookbyitscover.model.db.BookDatabase
import com.example.judgeabookbyitscover.model.db.BookRepository
import com.example.judgeabookbyitscover.model.db.Repository
import com.example.judgeabookbyitscover.presenter.BaseContract
import com.example.judgeabookbyitscover.presenter.DetailContract
import com.example.judgeabookbyitscover.presenter.DetailPresenter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.detail.*
import kotlinx.android.synthetic.main.detail.view.*

class DetailDialogFragment : DetailContract.View, BottomSheetDialogFragment(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var detailPresenter: DetailPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val detailMenuLayout = if (arguments?.getString(TAB) == "home") R.layout.detail else R.layout.shelf_detail
        return inflater.inflate(detailMenuLayout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Navigation listener
        setNavigationViewListener()

        // Create presenter
        detailPresenter = DetailPresenter()
        detailPresenter.create(this, BookRepository(), Repository(BookDatabase.getInstance(activity!!.applicationContext)))

        setupView(view)
    }

    private fun setNavigationViewListener() {
        val navigationView = getView()?.findViewById(R.id.detail_menu) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)
    }

    private fun setupView(view: View) {
        view.book_title.text = arguments?.getString(BOOK_TITLE)
        view.author.text = arguments?.getString(AUTHOR)
        view.description.text = arguments?.getString(DESCRIPTION)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    /**
     * Navigation menu actions
     */
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val clickedBook = Book(
                title = arguments?.getString(BOOK_TITLE)!!,
                author = arguments?.getString(AUTHOR)!!,
                description = arguments?.getString(DESCRIPTION)!!,
                book_image = arguments?.getString(IMG_URL)!!,
                amazon_product_url = arguments?.getString(AMAZON_URL)!!
        )

        when(item.itemId) {
            R.id.menu_favorite -> {
                Snackbar.make(activity!!.findViewById(android.R.id.content), "Added book to shelf", Snackbar.LENGTH_SHORT).show()
                detailPresenter.addToShelf(clickedBook)
                dismiss()
            }
            R.id.shelf_menu_remove -> {
                Snackbar.make(activity!!.findViewById(android.R.id.content), "Removed book from shelf", Snackbar.LENGTH_SHORT).show()
                detailPresenter.removeFromShelf(clickedBook)
                dismiss()
            }
            R.id.menu_view_on, R.id.shelf_menu_view_on -> startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(arguments?.getString(AMAZON_URL))))
        }
        return true
    }

    companion object {
        const val TAG = "DetailDialog"
        private const val BOOK_TITLE = "KEY_TITLE"
        private const val AUTHOR = "KEY_AUTHOR"
        private const val DESCRIPTION = "KEY_DESCRIPTION"
        private const val IMG_URL = "KEY_IMG"
        private const val AMAZON_URL = "KEY_AMAZON"
        private const val TAB = "KEY_TAB" // Boolean to determine whether to show remove or add to bookshelf menu

        fun newInstance(book: Book, tab: String): BottomSheetDialogFragment {
            val args = Bundle()
            args.putString(BOOK_TITLE, book.title)
            args.putString(AUTHOR, book.author)
            args.putString(DESCRIPTION, book.description)
            args.putString(AMAZON_URL, book.amazon_product_url)
            args.putString(IMG_URL, book.book_image)
            args.putString(TAB, tab)
            val fragment = DetailDialogFragment()
            fragment.arguments = args
            return fragment
        }
    }

    // Callbacks for presenter to update view
    override fun onResponse(books: List<Book>) {
        TODO("Not yet implemented")
    }

    override fun onFailure(msg: String) {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        super.onDestroy()
        detailPresenter.destroy()
        detailPresenter.detachView()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        val callback = targetFragment as? DialogCallBack
        callback?.onDialogClick()
    }
}