package com.maestros.bottlehoz.activities

data class DataPopularProduct(
    val result: Boolean,
    val message: String,
    val `data`: List<Data>
) {
    data class Data(
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
        val status: String,
        val strtotime: String,
        val images: List<Any>,
        val addons: List<Addon>
    ) {
        data class Addon(
            val addonID: String,
            val addon_productID: String,
            val productID: String,
            val name: String,
            val price: String,
            val path: String
        )
    }
}