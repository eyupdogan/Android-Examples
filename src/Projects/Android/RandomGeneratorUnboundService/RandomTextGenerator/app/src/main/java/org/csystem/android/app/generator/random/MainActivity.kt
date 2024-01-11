/*--------------------------------------------------------------------------------------------------
    Service yaratmak için service isimli bir sınıftan türetme yapmak ve android manifest dosyasında
    o service i servis olarak belirtmek gerekiyor

    RandomTextGenerator isimli bir proje oluşturduk ve gerekli dependency leri ekledik
--------------------------------------------------------------------------------------------------*/
package org.csystem.android.app.generator.random

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startService(Intent(this, RandomGeneratorService::class.java))
    }
}