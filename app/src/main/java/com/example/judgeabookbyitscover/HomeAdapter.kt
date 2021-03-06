package com.example.judgeabookbyitscover

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.judgeabookbyitscover.model.datamodels.Book

/**
 * Adapter creates a view holder and fills it with data for RecyclerView to display
 */
class HomeAdapter(var glide: RequestManager, var onBookListener: OnBookListener) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    private var books = emptyList<Book>()
    private var bookImgs = emptyList<String>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int ): HomeAdapter.ViewHolder {
        return ViewHolder.from(parent, onBookListener)
    }

    override fun getItemCount(): Int {
        return bookImgs.size
    }

    /**
     * Called by RecyclerView to display the data for the item at the specified list position
     */
    override fun onBindViewHolder(holder: HomeAdapter.ViewHolder, position: Int) {
        glide.load(bookImgs[position])
            .override(128, 200)
            .into(holder.img)
    }

    /**
     * Holds view info for one item in the RecyclerView
     */
    class ViewHolder private constructor(itemView: View, onBookListener: OnBookListener) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val img: ImageView = itemView.findViewById(R.id.row_img)
        val mOnBookListener: OnBookListener = onBookListener

        init {
            itemView.setOnClickListener(this)
        }
        /**
         * Inflates views and returns a ViewHolder instance
         */
        companion object {
            fun from(parent: ViewGroup, onBookListener: OnBookListener): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.cover_item, parent, false)

                return ViewHolder(view, onBookListener)
            }
        }

        override fun onClick(v: View?) {
            mOnBookListener.onBookClick(adapterPosition)
        }
    }

    interface OnBookListener {
        fun onBookClick(position: Int)
    }

    fun getBooks(): List<Book> {
        return books
    }

    fun setBooks(books: List<Book>) {
        this.books = books
        this.bookImgs = books.map{ it.book_image }
        notifyDataSetChanged()
    }

}