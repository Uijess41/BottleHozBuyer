package com.maestros.bottlehoz.activities

data class DataCatViseProduct(
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
        val path: String,
        val images: List<Image>,
        val addons: List<Any>
    ) {
        data class Image(
            val product_imageID: String,
            val productID: String,
            val image: String,
            val path: String
        )
    }
}