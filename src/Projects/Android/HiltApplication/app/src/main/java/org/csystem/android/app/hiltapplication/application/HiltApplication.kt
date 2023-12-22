package org.csystem.android.app.hiltapplication.application

import android.app.Application
import android.widget.Toast
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HiltApplication:Application()
{
    override fun onCreate()
    {
        Toast.makeText(this, "application started", Toast.LENGTH_SHORT).show()
        super.onCreate()
    }
}