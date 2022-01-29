package me.rail.ideasworldtest.models.list

data class UserCollection(
    val id: String,
    val title: String,
    val published_at: String,
    val last_collected_at: String,
    val updated_at: String,
    val cover_photo: String,
    val user: String
)
