package klivvr.test.day7.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import klivvr.test.day7.data.local.dao.Dao
import klivvr.test.day7.data.local.entity.DataEntity

@Database(entities = [DataEntity::class], version = 1)
abstract class DataBase : RoomDatabase() {
    abstract fun dao(): Dao

    companion object{
        @Volatile
        private var INSTANCE: DataBase? = null

        fun getDatabase(context: Context): DataBase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context = context,
                    DataBase::class.java,
                    "data_base"
                ).build().also {
                    INSTANCE = it
                }
            }
        }

    }
}