package com.maestros.bottlehoz.activities

data class CartData(
    val result: Boolean,
    val message: String,
    val `data`: List<Data>
) {
    data class Data(
        val sellers_info: SellersInfo,
        val grand_total: Int,
        val delivery_charge: String
    ) {
        data class SellersInfo(
            val userID: String,
            val type: String,
            val name: String,
            val address: String,
            val latitude: String,
            val longitude: String,
            val description: String,
            val tagline: String,
            val mobile: String,
            val email: String,
            val password: String,
            val age_validation: String,
            val bg_image: String,
            val last_login: String,
            val strtotime: String,
            val reg_id: String,
            val status: String,
            val profile_image: String,
            val shop_name: String,
            val shop_address: String,
            val shop_description: String,
            val shop_number: String,
            val products: List<Product>
        ) {
            data class Product(
                val cartID: String,
                val userID: String,
                val sellerID: String,
                val productID: String,
                val quantity: String,
                val product_tottal: String,
                val grand_total: String,
                val promoID: String,
                val promocode: String,
                val promo_value: String,
                val pay_status: String,
                val status: String,
                val orderID: String,
                val strtotime: String,
                val path: String,
                val total_price: Int,
                val product_info: ProductInfo
            ) {
                data class ProductInfo(
                    val productID: String,
                    val sellerID: String,
                    val name: String,
                    val description: String,
                    val categoryID: String,
                    val subcategoryID: String,
                    val brandID: String,
                    val price: String,
                    val stock: String,
                    val min_order: String,
                    val max_order: String,
                    val discount: String,
                    val product_sold: String,
                    val status: String,
                    val strtotime: String,
                    val images: List<Image>
                ) {
                    data class Image(
                        val product_imageID: String,
                        val productID: String,
                        val image: String,
                        val path: String
                    )
                }
            }
        }
    }
}