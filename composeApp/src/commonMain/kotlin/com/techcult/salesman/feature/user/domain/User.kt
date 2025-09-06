package com.techcult.salesman.feature.user.domain


data class User(
    val id: Int,
    val userName: String,
    val userEmail: String,
    val password: String,
    val role: String,
    val status: String,
    val createdAt: String,
    val updatedAt: String,
    val profilePicture: String,
    val phoneNumber: String,
    val lastLogin: String
)