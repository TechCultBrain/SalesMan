package com.techcult.salesman.core.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert


@Dao
interface UserDao {

    @Upsert
    suspend fun insertUser(user: UserEntity)

    @Query("SELECT * FROM UserEntity")
    fun getUser(): kotlinx.coroutines.flow.Flow<List<UserEntity>>

}

