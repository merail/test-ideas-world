package me.rail.ideasworldtest.models.item

import me.rail.ideasworldtest.models.list.Links
import me.rail.ideasworldtest.models.list.Urls
import me.rail.ideasworldtest.models.list.UserCollection
import me.rail.ideasworldtest.models.list.user.User

data class Item(
    val id: String,
    val created_at: String,
    val updated_at: String,
    val width: Int,
    val height: Int,
    val color: String,
    val blur_hash: String,
    val downloads: Int,
    val likes: Int,
    val liked_by_user: Boolean,
    val public_domain: Boolean,
    val description: String?,
    val exif: Exif,
    val location: Location,
    val tags: List<Tag>,
    val current_user_collections: List<UserCollection>,
    val urls: Urls,
    val links: Links,
    val user: User
)
