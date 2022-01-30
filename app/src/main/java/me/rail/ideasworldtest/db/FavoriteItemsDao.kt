package me.rail.ideasworldtest.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteItemsDao {

    @Query("SELECT * FROM favorite_items")
    fun getAll(): Flow<List<FavoriteItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg favoriteItem: FavoriteItem)

    @Update
    suspend fun update(vararg favoriteItem: FavoriteItem)

    @Delete
    suspend fun delete(vararg favoriteItem: FavoriteItem)

    @Query("SELECT EXISTS (SELECT * FROM favorite_items WHERE id = :id)")
    fun exists(id: String): Boolean
}