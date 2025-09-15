package com.techcult.salesman.core.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import com.techcult.salesman.feature.Settings.store.data.database.StoreInfoDao
import com.techcult.salesman.feature.Settings.store.data.database.StoreInfoEntity
import com.techcult.salesman.feature.product.data.dao.ProductDao
import com.techcult.salesman.feature.product.data.entity.BrandEntity
import com.techcult.salesman.feature.product.data.entity.CategoryEntity
import com.techcult.salesman.feature.product.data.entity.ProductEntity
import com.techcult.salesman.feature.product.data.entity.ProductVariantEntity
import com.techcult.salesman.feature.uom.data.UomEntity
import com.techcult.salesman.feature.purchase.data.PurchaseEntity
import com.techcult.salesman.feature.purchase.data.PurchaseItemEntity
import com.techcult.salesman.feature.supplier.data.SupplierEntity
import com.techcult.salesman.feature.uom.data.UomDao

@Database(
    entities = [UserEntity::class, PermissionEntity::class, RoleEntity::class, RolePermissionEntity::class, StoreInfoEntity::class, BrandEntity::class,
        ProductEntity::class, CategoryEntity::class, UomEntity::class, ProductVariantEntity::class, SupplierEntity::class, PurchaseEntity::class, PurchaseItemEntity::class],
    version = 1,
    exportSchema = false
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun permissionDao(): PermissionDao
    abstract fun storeInfoDao(): StoreInfoDao
    abstract fun productDao(): ProductDao
//    abstract fun supplierDao(): SupplierDao
//    abstract fun purchaseDao(): PurchaseDao
    abstract fun uomDao(): UomDao
//    abstract fun categoryDao(): CategoryDao
//    abstract fun brandDao(): BrandDao


    companion object {
        const val DB_NAME = "posnew8.db"
    }

}