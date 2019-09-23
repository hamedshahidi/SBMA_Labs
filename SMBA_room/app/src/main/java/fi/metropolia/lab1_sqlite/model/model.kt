package fi.metropolia.lab1_sqlite.model

import androidx.room.*

@Entity
data class User(
    @ColumnInfo(name = "username")
    val userName: String
) {
    @PrimaryKey(autoGenerate = true)
    var uuid: Long = 0
}

@Entity(foreignKeys = [ForeignKey(
    entity = User::class,
    parentColumns = ["uuid"],
    childColumns = ["userId"])])
data class Address(
    @PrimaryKey
    @ColumnInfo(name= "address")
    val address: String,

    @ColumnInfo(name = "userId")
    val userId: Long
)

class UserContact(
    @Embedded
    val user: User? = null,

    @Relation(parentColumn = "uuid", entityColumn = "userId")
    val addressList: List<Address>? = null
)

