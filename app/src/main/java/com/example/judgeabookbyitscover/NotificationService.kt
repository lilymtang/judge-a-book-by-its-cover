package com.example.judgeabookbyitscover

import android.app.PendingIntent
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.judgeabookbyitscover.MyAlarmReceiver.Companion.NOTIF_CHANNEL_ID
import java.util.*

class NotificationService : Service() {
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        // Create intent to open the app when notification is tapped
        val openAppIntent = Intent(this, MainActivity::class.java)
                .apply { Intent.FLAG_ACTIVITY_CLEAR_TASK }

        var builder = NotificationCompat.Builder(this, NOTIF_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_library_books_24)
                .setContentTitle(getString(R.string.notification_title))
                .setContentText(getString(R.string.notification_text))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(PendingIntent.getActivity(this, 0, openAppIntent, 0))
                .setAutoCancel(true)

        // Show notification
        with(NotificationManagerCompat.from(this)) {
            notify(1, builder.build())
        }

        return START_STICKY
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
        val intent = Intent("com.android.ServiceStopped")
        sendBroadcast(intent)
    }

    // Default implementation returns  null - no need to implement
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}

class MyAlarmReceiver : BroadcastReceiver() {
    // Triggered by the Alarm periodically (starts the service to run task)
    override fun onReceive(context: Context, intent: Intent?) {
        Log.d("TAG", "Starting service")
        val i = Intent(context, NotificationService::class.java)
        context.startService(i)
    }

    companion object {
        const val NOTIF_CHANNEL_ID = "default"
        const val REQUEST_CODE = 100001

        // Wednesdays at 7 PM EST
        lateinit var calendar: Calendar

        init {
            val calendar = Calendar.getInstance(TimeZone.getTimeZone("EST"))
            calendar[Calendar.DAY_OF_WEEK] = 4
            // Check we aren't setting it in the past which would trigger it to fire instantly
            if (Companion.calendar.timeInMillis < System.currentTimeMillis()) {
                Companion.calendar.add(Calendar.DAY_OF_YEAR, 7)
            }
            calendar.set(Calendar.HOUR, 7)
            calendar.set(Calendar.AM_PM, Calendar.PM)
        }
    }
}