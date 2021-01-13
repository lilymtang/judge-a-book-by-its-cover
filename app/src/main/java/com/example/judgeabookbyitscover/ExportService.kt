package com.example.judgeabookbyitscover

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.example.judgeabookbyitscover.model.datamodels.Book
import com.example.judgeabookbyitscover.model.db.BookDatabase
import com.example.judgeabookbyitscover.model.db.Repository
import com.example.judgeabookbyitscover.presenter.ShelfContract
import com.example.judgeabookbyitscover.presenter.ShelfPresenter
import com.github.doyaaaaaken.kotlincsv.dsl.csvWriter
import java.io.File

class ExportService : Service(), ShelfContract.View {
    var csvFile: File? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("TAG", "Service started")

        // Create presenter
        var shelfPresenter = ShelfPresenter()
        shelfPresenter.create(this, Repository(BookDatabase.getInstance(applicationContext)))

        // Make network request to get book covers to fill gallery
        shelfPresenter.getShelfBooks()

        // Generate file
        csvFile = generateFile(applicationContext, "MyShelf.csv")

        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    fun exportBooksToCSVFile(csvFile: File, books: List<Book>) {
        csvWriter().open(csvFile, append = false) {
            // Header
            writeRow(listOf("Author", "Title", "Description", "Amazon Link"))

            // Write row for each book in list
            books.forEach { writeRow(listOf(it.author, it.title, it.description, it.amazon_product_url)) }
            Log.d("TAG", csvFile.toString())
        }
    }

    override fun onDestroy() {
        Log.d("TAG", "Service destroyed")
    }

    override fun onResponse(books: List<Book>) {
        Log.d("TAG", books.toString())

        if (csvFile != null) {
            Log.d("TAG", "Generated csv")
            exportBooksToCSVFile(csvFile!!, books)
        }
    }

    override fun onFailure(msg: String) {
        TODO("Not yet implemented")
    }
}