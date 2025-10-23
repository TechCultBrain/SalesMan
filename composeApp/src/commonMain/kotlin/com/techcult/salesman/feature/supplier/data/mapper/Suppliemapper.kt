package com.techcult.salesman.feature.supplier.data.mapper

import com.techcult.salesman.feature.supplier.data.SupplierEntity
import com.techcult.salesman.feature.supplier.domain.model.Supplier


fun Supplier.toSupplierEntity(): SupplierEntity {
    return SupplierEntity(
        supplierId = supplierId,
        supplierCode = supplierCode,
        supplierName = supplierName,
        supplierAddress = supplierAddress,
        supplierPhone = supplierPhone,
        supplierStatus = supplierStatus,
        supplierType = supplierType,
        supplierContactNo = supplierContactNo,
        supplierContactName = supplierContactName,
        supplierContactEmail = supplierContactEmail,
        supplierCity = supplierCity,
        supplierState = supplierState,
        supplierZipCode = supplierZipCode,
        supplierTin = supplierTin,
        panNumber = panNumber,
        supplierGst = supplierGst,
        creditLimit = creditLimit,
        creditDays = creditDays,
        notes = notes,

    )


}

fun SupplierEntity.toSupplier(): Supplier{
    return Supplier(
        supplierId = supplierId,
        supplierCode = supplierCode,
        supplierName = supplierName.toString(),
        supplierAddress = supplierAddress,
        supplierPhone = supplierPhone,
        supplierStatus = supplierStatus,
        supplierType = supplierType,
        supplierContactNo = supplierContactNo,
        supplierContactName = supplierContactName,
        supplierContactEmail = supplierContactEmail,
        supplierCity = supplierCity,
        supplierState = supplierState,
        supplierZipCode = supplierZipCode,
        supplierTin = supplierTin,
        panNumber = panNumber,
        supplierGst = supplierGst,
        creditLimit = creditLimit,
        creditDays = creditDays,
        notes = notes,
    )



}