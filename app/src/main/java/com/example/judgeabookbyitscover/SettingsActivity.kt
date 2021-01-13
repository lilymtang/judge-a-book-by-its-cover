package com.example.judgeabookbyitscover

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.Toolbar

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        var toolbar = findViewById<Toolbar>(R.id.settings_action_bar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var button = findViewById<Button>(R.id.settings_button)
        button.setOnClickListener {
            Toast.makeText(this, "Export clicked", Toast.LENGTH_SHORT).show()
        }
    }

}