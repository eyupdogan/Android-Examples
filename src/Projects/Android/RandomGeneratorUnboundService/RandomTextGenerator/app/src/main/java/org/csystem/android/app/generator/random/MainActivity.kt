package org.csystem.android.app.generator.random

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.csystem.android.app.generator.random.data.RandomTextGeneratorInfo
import org.csystem.android.app.generator.random.global.RANDOM_TEXT_GENERATOR_INFO

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Intent(this, RandomGeneratorService::class.java).apply {
            putExtra(RANDOM_TEXT_GENERATOR_INFO, RandomTextGeneratorInfo("test.txt", 20, 5, 12))
            startService(this)
            finish()
        }
    }
}