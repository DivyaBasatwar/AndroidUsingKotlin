package com.example.androidusingkotlin

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class BuyProductActivity : AppCompatActivity() {
    lateinit var tvProductName: TextView
    lateinit var tvProductPrice: TextView
    lateinit var ivProductImage: ImageView
    lateinit var edtProductQty: EditText
    lateinit var tvTotalPriceVal: TextView
    lateinit var btnBuy: Button
    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var notificationBuilder: Notification.Builder
    var channelId: String = "infy.notification"
    var description: String = "Order Notification"

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_buy_product)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tvProductName = findViewById(R.id.tv_product_name)
        tvProductPrice = findViewById(R.id.tv_product_price)
        ivProductImage = findViewById(R.id.iv_product_image)
        edtProductQty = findViewById(R.id.edt_product_qty)
        tvTotalPriceVal = findViewById(R.id.tv_total_price_val)
        btnBuy = findViewById(R.id.btn_buy)

        val product = intent.getSerializableExtra("Product") as Product
        tvProductName.text = product.productName
        tvProductPrice.text = "₹" + product.productPrice.toString()
        ivProductImage.setImageDrawable(AppCompatResources.getDrawable(this, product.productImage))
        tvTotalPriceVal.text =  "₹" + product.productPrice.toString()


        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


        edtProductQty.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(
                p0: CharSequence?,
                p1: Int,
                p2: Int,
                p3: Int
            ) {}

            override fun onTextChanged(
                p0: CharSequence?,
                p1: Int,
                p2: Int,
                p3: Int
            ) {
                val qty = p0.toString().toIntOrNull() ?: 1
                val totalPrice = product.productPrice * qty
                tvTotalPriceVal.text = "₹" +totalPrice.toString()
            }
        })

        btnBuy.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Are you sure you want to buy this product?")
                .setCancelable(false)
                .setPositiveButton("Yes"){ dialog, _ ->
                    Toast.makeText(this, "Order Placed!", Toast.LENGTH_SHORT).show()


                    //display notification
                    var intent = Intent(this, ProductsActivity::class.java)
                    var pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)


                    notificationChannel = NotificationChannel(channelId, description,
                        NotificationManager.IMPORTANCE_HIGH)
                    notificationChannel.description = "Order confirmation notification"
                    notificationManager.createNotificationChannel(notificationChannel)

                    notificationBuilder = Notification.Builder(this, channelId)
                    notificationBuilder.setContentTitle("Order confirmation notification")
                        .setContentText("Thanks for choosing us")
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentIntent(pendingIntent)
                    notificationManager.notify(123, notificationBuilder.build())

                    dialog.dismiss()

                }
                .setNegativeButton("No"){ dialog, _ ->
                    dialog.dismiss()
                }
                .show()

        }
    }
}