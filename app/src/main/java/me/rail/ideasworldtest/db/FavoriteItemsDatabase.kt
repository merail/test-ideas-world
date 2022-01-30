package me.rail.ideasworldtest.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavoriteItem::class], version = 1)
abstract class FavoriteItemsDatabase : RoomDatabase() {
    abstract fun favoriteItemsDao(): FavoriteItemsDao
}