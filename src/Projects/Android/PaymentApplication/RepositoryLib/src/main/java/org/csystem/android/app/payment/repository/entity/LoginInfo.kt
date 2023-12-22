/*--------------------------------------------------------------------------------------------------
    Örneğin biz 1 tane user silmek istiyoruz, eğer user ın ilişkili olduğu başka entity varsa silme
    işlemi gerçekleşmez. Eğer böyle olmasaydı ve biz 1 tane xxx isimli bir user silseydik, xxx diye
    bir user olmayacaktı ancak xxx ismine ait paymentler ve loginInfo lar olacaktı, tam olarak bu
    sebepten silme işlemi engelleniyor, bu durumu önlemek için aşağıdaki gibi onDelete te ne
    yapacağını söyleyebiliyoruz
--------------------------------------------------------------------------------------------------*/
    // LoginInfo
    package org.csystem.android.app.payment.repository.entity

    import androidx.room.ColumnInfo
    import androidx.room.Entity
    import androidx.room.ForeignKey
    import androidx.room.Index
    import androidx.room.PrimaryKey
    import java.io.Serializable
    import java.time.LocalDateTime

    @Entity(tableName = "login_info", indices = [Index("username")],
        foreignKeys = [ForeignKey(entity = User::class, parentColumns = ["username"], childColumns = ["username"],
            onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)])
    data class LoginInfo(
        @PrimaryKey(autoGenerate = true) var id: Long = 0,
        var username: String = "",
        var password: String = "",
        var success: Boolean = true,
        @ColumnInfo(name = "login_date_time") var loginDateTime: LocalDateTime = LocalDateTime.now()
    ):Serializable