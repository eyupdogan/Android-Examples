package org.csystem.android.app.geonameswikisearchretrofit.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.csystem.android.app.geonameswikisearchretrofit.repository.converter.LocalDateTimeConverter
import org.csystem.android.app.geonameswikisearchretrofit.repository.dao.IWikiInfoDao
import org.csystem.android.app.geonameswikisearchretrofit.repository.entity.WikiInfo

@Database(entities = [WikiInfo::class], exportSchema = false, version = 3)
@TypeConverters(LocalDateTimeConverter::class)
abstract class WikiSearchDatabase:RoomDatabase()
{
    abstract fun createWikiInfoDao():IWikiInfoDao
}