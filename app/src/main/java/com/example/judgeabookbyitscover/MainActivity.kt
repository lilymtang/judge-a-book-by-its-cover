package com.example.judgeabookbyitscover

import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager


class MainActivity() : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var coverGalleryAdapter: CoverGalleryAdapter
    lateinit var manager: StaggeredGridLayoutManager


    class MarginItemDecoration(
        private val spaceSize: Int,
        private val spanCount: Int = 1,
        private val orientation: Int = StaggeredGridLayoutManager.VERTICAL
    ) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect, view: View,
            parent: RecyclerView, state: RecyclerView.State
        ) {
            with(outRect) {
                if (orientation == StaggeredGridLayoutManager.VERTICAL) {
                    if (parent.getChildAdapterPosition(view) < spanCount) {
                        top = spaceSize
                    }
                    if (parent.getChildAdapterPosition(view) % spanCount == 0) {
                        left = spaceSize
                    }
                } else {
                    if (parent.getChildAdapterPosition(view) < spanCount) {
                        left = spaceSize
                    }
                    if (parent.getChildAdapterPosition(view) % spanCount == 0) {
                        top = spaceSize
                    }
                }

                right = spaceSize
                bottom = spaceSize
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.cover_gallery)
        recyclerView.addItemDecoration(
            MarginItemDecoration(8)
        )
        manager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = manager

        // list of images
        var data = arrayOf<Int> (
            R.drawable.educated,
            R.drawable.less,
            R.drawable.pachinko,
            R.drawable.into_thin_air,
            R.drawable.coddling,
            R.drawable.martian,
            R.drawable.midnight,
            R.drawable.bookthief,
            R.drawable.crazyrichasians,
            R.drawable.darkmatter,
            R.drawable.light,
            R.drawable.promisedland
        )

        coverGalleryAdapter = CoverGalleryAdapter(this, data)
        recyclerView.adapter = coverGalleryAdapter
    }
}