package klivvr.test.day7.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import klivvr.test.day7.data.local.entity.DataEntity

@Dao
interface  Dao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(data: DataEntity)

    @Query("SELECT * FROM data")
    suspend fun getData(): List<DataEntity>

    @Query("DELETE FROM data")
    suspend fun deleteAllData()
}