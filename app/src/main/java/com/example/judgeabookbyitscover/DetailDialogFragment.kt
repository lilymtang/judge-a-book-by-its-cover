package com.example.judgeabookbyitscover

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.detail.view.*


//class DetailDialogFragment : Fragment(R.layout.detail)

class DetailDialogFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView(view)
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
//
////    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
////            BottomSheetDialogFragment.Builder(requireContext())
////                    .setMessage(getString(R.string.order_confirmation))
////                    .setPositiveButton(getString(R.string.ok)) { _,_ -> }
////                    .create()
//
    companion object {
        const val TAG = "DetailDialog"
        private const val BOOK_TITLE = "KEY_TITLE"
        private const val AUTHOR = "KEY_AUTHOR"
        private const val DESCRIPTION = "KEY_DESCRIPTION"

        fun newInstance(bookTitle: String, author: String, description: String): BottomSheetDialogFragment {
            val args = Bundle()
            args.putString(BOOK_TITLE, bookTitle)
            args.putString(AUTHOR, author)
            args.putString(DESCRIPTION, description)
            val fragment = DetailDialogFragment()
            fragment.arguments = args
            return fragment
        }
    }
//
}