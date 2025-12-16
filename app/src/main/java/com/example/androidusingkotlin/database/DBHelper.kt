package com.example.androidusingkotlin.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context): SQLiteOpenHelper(context, "product_db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(
            """
        CREATE TABLE products (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            product_name TEXT,
            product_qty INTEGER,
            product_price REAL,
            total_price REAL
        )
        """.trimIndent()
        )
    }

    override fun onUpgrade(
        p0: SQLiteDatabase?,
        p1: Int,
        p2: Int
    ) {
        TODO("Not yet implemented")
    }

    fun insertProduct(
        name: String,
        qty: Int,
        price: Double,
    ): Boolean {

        val total = qty * price
        val db = writableDatabase

        val values = ContentValues().apply {
            put("product_name", name)
            put("product_qty", qty)
            put("product_price", price)
            put("total_price", total)
        }

        val result = db.insert("products", null, values)
       // db.close()

        return result != -1L
    }
}