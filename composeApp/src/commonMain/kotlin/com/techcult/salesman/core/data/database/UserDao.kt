package com.techcult.salesman.core.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDao {

    @Upsert
    suspend fun insertUser(user: UserEntity)


    @Delete
    suspend fun deleteUser(user: UserEntity)


    @Update
    suspend fun updateUser(user: UserEntity)

    @Query("SELECT A.id,A.userName,A.phoneNumber,A.createdAt,B.roleName FROM UserEntity as A Inner Join RoleEntity as B on A.role = B.roleId")
    fun getUser(): kotlinx.coroutines.flow.Flow<List<UserWithRole>>


    @Query("SELECT A.id,A.userName,A.phoneNumber,A.createdAt,B.roleName FROM UserEntity as A Inner Join RoleEntity as B on A.role = B.roleId " +
            " WHERE A.userName LIKE '%' || :query || '%'\n" +
            "        ORDER BY A.userName ASC" )
     fun getUserByUserName(query: String): Flow<List<UserWithRole>>


}

data class UserWithRole(
    val id: Int,
    val userName: String,
    val phoneNumber: String?,
    val createdAt: Long,
    val roleName: String
)

