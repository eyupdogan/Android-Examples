/*--------------------------------------------------------------------------------------------------
    projenin database eklenmiş haline instrumental testler yapıldı, ileride stres testi de yapılacak
--------------------------------------------------------------------------------------------------*/
package org.csystem.android.app.payment.repository.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.time.LocalDate

@Entity(tableName = "users")
data class User(
    @PrimaryKey var username: String = "", // table nın primary key i
    @ColumnInfo(name = "password") var password: String = "", // table ın column ismi
    @ColumnInfo(name = "first_name") var firstname: String = "",
    @ColumnInfo(name = "middle_name") var middleName: String? = "",
    @ColumnInfo(name = "last_name") var lastName: String = "",
    @ColumnInfo(name = "birth_date") var birthDate: LocalDate = LocalDate.now(),
    @ColumnInfo(name = "register_date") var registerDate: LocalDate = LocalDate.now()) : Serializable
{
    constructor(
        username: String, password: String, firstname: String, lastName: String, birthDate: LocalDate,
        registerDate: LocalDate) : this(username, password, firstname, null, lastName,
        birthDate, registerDate)
}