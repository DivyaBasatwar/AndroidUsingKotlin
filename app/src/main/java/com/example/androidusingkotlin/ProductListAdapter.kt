package com.example.androidusingkotlin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView

class ProductListAdapter(val context: Context, val productsList: ArrayList<Product>): RecyclerView.Adapter<ProductListAdapter.ProductItemViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductItemViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false)
        return ProductItemViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ProductItemViewHolder,
        position: Int
    ) {
        holder.tvProductName.text = productsList[position].productName
        holder.tvProductPrice.text = "₹" + productsList[position].productPrice.toString()
        holder.imgProduct.setImageDrawable(AppCompatResources.getDrawable(context, productsList[position].productImage))

        holder.btnPlaceOrder.setOnClickListener {
            val intent = Intent(context, BuyProductActivity::class.java)
            intent.putExtra("Product", productsList[position])
            context.startActivity(intent)
        }
    }


    override fun getItemCount(): Int {
        return productsList.size
    }

    class ProductItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvProductName: TextView = view.findViewById(R.id.tv_product_name)
        val tvProductPrice: TextView = view.findViewById(R.id.tv_product_price)
        val btnPlaceOrder: Button = view.findViewById(R.id.btn_place_order)
        val imgProduct: ImageView = view.findViewById(R.id.iv_product_image)
    }
}