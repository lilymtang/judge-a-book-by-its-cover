package com.example.judgeabookbyitscover

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.judgeabookbyitscover.network.Book

/**
 * Adapter creates a view holder and fills it with data for RecyclerView to display
 */
class CoverGalleryAdapter(var glide: RequestManager) : RecyclerView.Adapter<CoverGalleryAdapter.ViewHolder>() {
    private var data = emptyList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int ): CoverGalleryAdapter.ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    /**
     * Called by RecyclerView to display the data for the item at the specified list position
     */
    override fun onBindViewHolder(holder: CoverGalleryAdapter.ViewHolder, position: Int) {
        glide.load(data[position])
            .override(128, 200)
            .into(holder.img)
    }

    /**
     * Holds view info for one item in the RecyclerView
     */
    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.row_img)

        /**
         * Inflates views and returns a ViewHolder instance
         */
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.cover_item, parent, false)

                return ViewHolder(view)
            }
        }
    }

    fun setData(newList: List<String>) {
        data = newList
        notifyDataSetChanged()
    }

}