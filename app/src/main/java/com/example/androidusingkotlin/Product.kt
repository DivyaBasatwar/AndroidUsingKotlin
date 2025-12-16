package com.example.androidusingkotlin

import android.graphics.drawable.Drawable
import java.io.Serializable

data class Product(
    val productName: String,
    val productPrice: Double,
    val productImage: Int,
): Serializable