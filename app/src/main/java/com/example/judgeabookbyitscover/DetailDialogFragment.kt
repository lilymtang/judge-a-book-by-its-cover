package com.example.judgeabookbyitscover

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Toast
import com.example.judgeabookbyitscover.model.datamodels.Book
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.detail.view.*


//class DetailDialogFragment : Fragment(R.layout.detail)

class DetailDialogFragment : BottomSheetDialogFragment(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Navigation listener
        setNavigationViewListener()

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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_favorite -> Toast.makeText(view?.context, "Add to shelf", Toast.LENGTH_LONG).show()
            R.id.menu_view_on -> startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(arguments?.getString(AMAZON_URL))));
        }
        return true
    }

    companion object {
        const val TAG = "DetailDialog"
        private const val BOOK_TITLE = "KEY_TITLE"
        private const val AUTHOR = "KEY_AUTHOR"
        private const val DESCRIPTION = "KEY_DESCRIPTION"
        private const val AMAZON_URL = "KEY_URL"

        fun newInstance(book: Book): BottomSheetDialogFragment {
            val args = Bundle()
            args.putString(BOOK_TITLE, book.title)
            args.putString(AUTHOR, book.author)
            args.putString(DESCRIPTION, book.description)
            args.putString(AMAZON_URL, book.amazon_product_url)
            val fragment = DetailDialogFragment()
            fragment.arguments = args
            return fragment
        }
    }
//
}