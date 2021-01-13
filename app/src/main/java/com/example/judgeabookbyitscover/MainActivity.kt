package com.example.judgeabookbyitscover

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.judgeabookbyitscover.MyAlarmReceiver.Companion.NOTIF_CHANNEL_ID
import com.example.judgeabookbyitscover.MyAlarmReceiver.Companion.calendar
import com.example.judgeabookbyitscover.model.db.BookDatabase
import com.example.judgeabookbyitscover.model.db.Repository
import com.example.judgeabookbyitscover.ui.main.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayout
import java.util.*


class MainActivity : AppCompatActivity() {
    lateinit var db: BookDatabase
    lateinit var repository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

        // Create Room database
        db = BookDatabase.getInstance(applicationContext)
        repository = Repository(db)

        createNotificationChannel()
        scheduleAlarm()
    }

    // Set up alarm
    private fun scheduleAlarm() {
        // This intent that will execute the AlarmReceiver
        val intent = Intent(applicationContext, MyAlarmReceiver::class.java)

        // Set up periodic alarm every every half hour from this point onwards
        val firstMillis = System.currentTimeMillis() // alarm is set right away
        val alarm = this.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        // PendingIntent will be triggered when the alarm goes off
        val pIntent = PendingIntent.getBroadcast(this, MyAlarmReceiver.REQUEST_CODE,
                intent, PendingIntent.FLAG_UPDATE_CURRENT)

        alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis,AlarmManager.INTERVAL_DAY * 7, pIntent)
//        alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, firstMillis,60, pIntent)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(NOTIF_CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}