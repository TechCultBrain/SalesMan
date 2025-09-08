package com.techcult.salesman.core.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class,PermissionEntity::class,RoleEntity::class,RolePermissionEntity::class], version = 1, exportSchema = false)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun permissionDao(): PermissionDao


    companion object{
        const val DB_NAME="posnew2.db"
    }

}