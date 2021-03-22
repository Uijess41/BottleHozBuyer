package com.maestros.bottlehoz.activities

data class DataImagePremium(
    val result: Boolean,
    val message: String,
    val `data`: List<Data>
) {
    data class Data(
        val brandID: String,
        val name: String,
        val image: String,
        val path: String
    )
}