package com.maestros.bottlehoz.activities

data class DataDiscount(
    val result: Boolean,
    val message: String,
    val `data`: List<Data>
) {
    data class Data(
        val couponID: String,
        val description: String,
        val categoryID: String,
        val user_type: String,
        val coupon_value: String,
        val coupon_code: String,
        val start_date: String,
        val end_date: String,
        val image: String,
        val strtotime: String,
        val path: String
    )
}