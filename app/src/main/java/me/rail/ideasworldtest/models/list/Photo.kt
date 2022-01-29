package me.rail.ideasworldtest.models.list

import me.rail.ideasworldtest.models.list.user.User

data class Photo(
    val id: String,
    val created_at: String,
    val updated_at: String,
    val width: Int,
    val height: Int,
    val color: String,
    val blur_hash: String,
    val description: String,
    val user: User,
    val urls: Urls,
    val likes: Int,
    val liked_by_user: Boolean,
    val links: Links,
    val current_user_collections: List<UserCollection>
)
