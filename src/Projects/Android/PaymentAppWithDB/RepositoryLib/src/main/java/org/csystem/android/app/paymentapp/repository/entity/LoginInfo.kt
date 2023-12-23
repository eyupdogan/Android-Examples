package org.csystem.android.app.paymentapp.repository.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.io.Serializable
import java.time.LocalDateTime

@Entity(tableName = "login_info", foreignKeys = [ForeignKey(User::class, parentColumns = ["username"], childColumns = ["username"],
    onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)])
data class LoginInfo(
    @PrimaryKey(autoGenerate = true) var id: Long = 0L,
    var username: String = "",
    var password: String = "",
    var success: Boolean = true,
    @ColumnInfo(name = "login_date_time") var loginDateTime: LocalDateTime = LocalDateTime.now()
) : Serializable