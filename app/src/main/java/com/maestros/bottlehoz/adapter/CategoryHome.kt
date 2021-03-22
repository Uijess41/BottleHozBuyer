package com.maestros.bottlehoz.adapter

data class CategoryHome(
    val result: Boolean,
    val message: String,
    val `data`: List<Data>
) {
    data class Data(
        val categoryID: String,
        val name: String,
        val image: String,
        val path: String
    )
}