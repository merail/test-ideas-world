package me.rail.ideasworldtest.models.list.user

data class User(
    val id: String,
    val username: String,
    val name: String,
    val portfolio_url: String,
    val bio: String,
    val location: String,
    val total_likes: Int,
    val total_photos: Int,
    val total_collections: Int,
    val instagram_username: String,
    val twitter_username: String,
    val profile_image: ProfileImage,
    val links: Links
)