package com.techcult.salesman.feature.product.utils

fun formatForBaseuom(baseUom: String, conversionFactor: Double, unitOfMeasure: String): String {


    return "1$unitOfMeasure = $conversionFactor$baseUom"


}