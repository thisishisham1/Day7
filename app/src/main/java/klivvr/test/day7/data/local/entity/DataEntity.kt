package klivvr.test.day7.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "data")
class DataEntity (
    val phone: String,
    val message: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}