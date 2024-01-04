package org.csystem.android.app.generator.randomtextgenerator

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast

class RandomGeneratorService : Service()
{

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int
    {
        Toast.makeText(this, "Service started", Toast.LENGTH_SHORT).show()

        stopSelf()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy()
    {
        Toast.makeText(this, "Service stopped", Toast.LENGTH_SHORT).show()
        super.onDestroy()
    }
    override fun onBind(intent: Intent): IBinder
    {
        throw UnsupportedOperationException("Unsupported")
    }
}