package com.example.judgeabookbyitscover

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.judgeabookbyitscover.model.datamodels.Book
import com.example.judgeabookbyitscover.model.db.BookDatabase
import com.example.judgeabookbyitscover.model.db.BookRepository
import com.example.judgeabookbyitscover.model.db.Repository
import com.example.judgeabookbyitscover.presenter.ShelfContract
import com.example.judgeabookbyitscover.presenter.ShelfPresenter

class SettingsActivity : AppCompatActivity() {
    lateinit var shelfPresenter: ShelfPresenter
    lateinit var bookRepository: BookRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        var toolbar = findViewById<Toolbar>(R.id.settings_action_bar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var exportButton = findViewById<Button>(R.id.settings_button)
        exportButton.setOnClickListener {
            startExportService(applicationContext)
        }
    }

    private fun startExportService(context: Context) {
        val serviceIntent = Intent(this, ExportService::class.java)
        context.startService(serviceIntent)
    }
}