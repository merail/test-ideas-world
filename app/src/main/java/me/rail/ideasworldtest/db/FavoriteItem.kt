package me.rail.ideasworldtest.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_items")
data class FavoriteItem(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "urlSmall") val urlSmall: String
)