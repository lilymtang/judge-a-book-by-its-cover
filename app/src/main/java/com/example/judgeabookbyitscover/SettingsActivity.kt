package com.example.judgeabookbyitscover

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.google.android.material.snackbar.Snackbar

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        var toolbar = findViewById<Toolbar>(R.id.settings_action_bar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var exportButton = findViewById<Button>(R.id.settings_button)
        exportButton.setOnClickListener {
            startExportService(applicationContext)
            Snackbar.make(findViewById(android.R.id.content), "Exported successfully", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun startExportService(context: Context) {
        val serviceIntent = Intent(this, ExportService::class.java)
        context.startService(serviceIntent)
    }
}