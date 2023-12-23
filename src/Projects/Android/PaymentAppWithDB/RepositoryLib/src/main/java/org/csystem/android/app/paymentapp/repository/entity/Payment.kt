package org.csystem.android.app.paymentapp.repository.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "payments",
    foreignKeys = [ForeignKey(User::class, parentColumns = ["username"], childColumns = ["username"],
        onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)])
data class Payment(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    var username: String = "",
    @ColumnInfo(name = "unit_price") var unitPrice: Double = 0.0,
    var quantity: Double = 0.0,
    var description: String = ""
) : Serializable