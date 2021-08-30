package com.aleangelozi.mvppattern

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.aleangelozi.mvppattern.R
import com.aleangelozi.mvppattern.view.CountriesView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener{
            val intent = Intent(this, CountriesView::class.java)
            startActivity(intent)
        }
    }
}