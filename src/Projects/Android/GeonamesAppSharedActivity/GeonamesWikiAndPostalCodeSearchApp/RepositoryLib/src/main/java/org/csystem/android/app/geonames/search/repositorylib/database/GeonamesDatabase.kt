package org.csystem.android.app.geonames.search.repositorylib.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import org.csystem.android.app.geonames.search.repositorylib.database.converter.LocalDateTimeConverter
import org.csystem.android.app.geonames.search.repositorylib.database.dao.IPostalCodeSearchDao
import org.csystem.android.app.geonames.search.repositorylib.database.dao.IQuerySearchDao
import org.csystem.android.app.geonames.search.repositorylib.database.dao.IWikiInfoSearchDao
import org.csystem.android.app.geonames.search.repositorylib.database.entity.PostalCodeDB
import org.csystem.android.app.geonames.search.repositorylib.database.entity.PostalCodeSearchDB
import org.csystem.android.app.geonames.search.repositorylib.database.entity.QuerySearch
import org.csystem.android.app.geonames.search.repositorylib.database.entity.WikiInfoDB
import org.csystem.android.app.geonames.search.repositorylib.database.entity.WikiSearchDB

@Database(
    entities = [WikiInfoDB::class, WikiSearchDB::class, PostalCodeDB::class, PostalCodeSearchDB::class, QuerySearch::class],
    exportSchema = false,
    version = 1
)
@TypeConverters(LocalDateTimeConverter::class)
abstract class GeonamesDatabase : RoomDatabase()
{
    abstract fun createWikiSearchDao(): IWikiInfoSearchDao
    abstract fun createPostalCodeSearchDao(): IPostalCodeSearchDao
    abstract fun createQuerySearchDao():IQuerySearchDao
}