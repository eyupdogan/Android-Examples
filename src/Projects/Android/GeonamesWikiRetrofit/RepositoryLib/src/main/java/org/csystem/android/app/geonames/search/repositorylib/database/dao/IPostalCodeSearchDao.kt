package org.csystem.android.app.geonames.search.repositorylib.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import org.csystem.android.app.geonames.search.repositorylib.database.entity.PostalCodeDB
import org.csystem.android.app.geonames.search.repositorylib.database.entity.PostalCodeSearchDB

@Dao
interface IPostalCodeSearchDao
{
    @Insert
    fun save(postalCodeDB: PostalCodeDB)

    @Insert
    fun savePostalCodeList(postalCodeSearchDB: PostalCodeSearchDB)

    @Query("""SELECT EXISTS(SELECT * FROM postal_code_search pcs
        WHERE pcs.postal_code = :postalCode AND pcs.row_count = :rowCount)""")
    fun existsPostalCodeByPostalCodeNumberAndRowCount(postalCode:String, rowCount:Int):Boolean

    @Query("""SELECT * FROM postal_code pc INNER JOIN postal_code_search pcs 
        ON pc.postal_code = pcs.postal_code WHERE pc.postal_code = :postalCode""")
    fun findPostalCodeByPostalCodeNumber(postalCode: String):List<PostalCodeDB>
}