package com.imaginato.homeworkmvp.data.lrf.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
class UserEntity {

    @PrimaryKey(autoGenerate = true)
    var id: Long? = null

    var username: String? = null
    var password: String? = null

}
