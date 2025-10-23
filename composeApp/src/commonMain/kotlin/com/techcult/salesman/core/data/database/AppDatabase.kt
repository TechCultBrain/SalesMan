package com.techcult.salesman.core.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import com.techcult.salesman.feature.Settings.store.data.database.StoreInfoDao
import com.techcult.salesman.feature.Settings.store.data.database.StoreInfoEntity
import com.techcult.salesman.feature.discount.data.DiscountConverters
import com.techcult.salesman.feature.discount.data.DiscountDao
import com.techcult.salesman.feature.discount.data.DiscountEntity
import com.techcult.salesman.feature.product.data.dao.BrandDao
import com.techcult.salesman.feature.product.data.dao.CategoryDao
import com.techcult.salesman.feature.product.data.dao.ProductDao
import com.techcult.salesman.feature.product.data.entity.BrandEntity
import com.techcult.salesman.feature.product.data.entity.CategoryEntity
import com.techcult.salesman.feature.product.data.entity.ProductEntity
import com.techcult.salesman.feature.product.data.entity.ProductVariantEntity
import com.techcult.salesman.feature.product.data.entity.UomEntity
import com.techcult.salesman.feature.purchase.data.PurchaseEntity
import com.techcult.salesman.feature.purchase.data.PurchaseItemEntity
import com.techcult.salesman.feature.supplier.data.SupplierEntity
import com.techcult.salesman.feature.product.data.dao.UomDao
import com.techcult.salesman.feature.purchase.data.PurchaseDao
import com.techcult.salesman.feature.supplier.data.SupplierDao
import com.techcult.salesman.feature.tax.data.TaxComponentEntity
import com.techcult.salesman.feature.tax.data.TaxDao
import com.techcult.salesman.feature.tax.data.TaxSlabEntity
import com.techcult.salesman.feature.tax.data.TaxSlabWithComponents

@Database(
    entities = [UserEntity::class, PermissionEntity::class, RoleEntity::class, RolePermissionEntity::class, StoreInfoEntity::class, BrandEntity::class,
        ProductEntity::class, CategoryEntity::class, UomEntity::class, ProductVariantEntity::class, SupplierEntity::class, PurchaseEntity::class, PurchaseItemEntity::class,
        TaxSlabEntity::class, TaxComponentEntity::class, DiscountEntity::class],
    version = 1,
    exportSchema = false
)
@androidx.room.TypeConverters(LocalDateTimeConvertor::class, DiscountConverters::class)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun permissionDao(): PermissionDao
    abstract fun storeInfoDao(): StoreInfoDao
    abstract fun productDao(): ProductDao

    abstract fun supplierDao(): SupplierDao
    abstract fun purchaseDao(): PurchaseDao
    abstract fun uomDao(): UomDao
    abstract fun categoryDao(): CategoryDao
    abstract fun brandDao(): BrandDao

    abstract fun taxDao(): TaxDao
    abstract fun discountDao(): DiscountDao





    companion object {
        const val DB_NAME = "posapp4.db"
    }

}