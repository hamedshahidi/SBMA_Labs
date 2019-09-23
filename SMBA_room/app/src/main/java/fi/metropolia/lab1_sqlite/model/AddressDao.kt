package fi.metropolia.lab1_sqlite.model

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface AddressDao {
    @Insert
    suspend fun insertAddress(address: Address): Long
}