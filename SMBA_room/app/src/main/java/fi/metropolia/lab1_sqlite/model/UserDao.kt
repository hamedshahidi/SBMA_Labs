package fi.metropolia.lab1_sqlite.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User): Long

    @Query("SELECT * FROM user")
    suspend fun getAllUser(): List<User>

    @Query("DELETE FROM user")
    suspend fun deleteAllUser()

    @Query("SELECT * FROM User")
    suspend fun getUserContact(): List<UserContact>
}
