package com.example.androidusingkotlin

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProductsActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_products)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val productsList = ArrayList<Product>()
        productsList.add(Product("Sugar", 50.0,  R.drawable.sugar))
        productsList.add(Product("Wheat", 15.0,  R.drawable.wheat))

        recyclerView = findViewById(R.id.rv_product_list)
        val prodListAdapter = ProductListAdapter(this, productsList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = prodListAdapter



    }
}