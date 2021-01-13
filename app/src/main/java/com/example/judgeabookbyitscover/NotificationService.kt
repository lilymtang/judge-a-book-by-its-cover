package com.example.judgeabookbyitscover

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.widget.Toast

class NotificationService : Service() {
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show()
        return START_STICKY
    }

    // Default implementation returns  null - no need to implement
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}

class MyAlarmReceiver : BroadcastReceiver() {
    // Triggered by the Alarm periodically (starts the service to run task)
    override fun onReceive(context: Context, intent: Intent?) {
        val i = Intent(context, NotificationService::class.java)
        i.putExtra("foo", "bar")
        context.startService(i)
    }

    companion object {
        const val REQUEST_CODE = 12345
        const val ACTION = "com.codepath.example.servicesdemo.alarm"
    }
}