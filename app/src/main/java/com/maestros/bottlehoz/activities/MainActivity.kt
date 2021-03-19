package com.maestros.bottlehoz.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.maestros.bottlehoz.R
import com.maestros.bottlehoz.databinding.ActivityLoginBinding
import com.maestros.bottlehoz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.layoutBuyer.setOnClickListener(clickListener)
    }

    val clickListener = View.OnClickListener { view ->
        when (view.getId()) {
            R.id.layout_buyer -> layoutBuyer()
        }
    }

    private fun layoutBuyer() {
        intent = Intent(applicationContext, LoginActivity::class.java)
        intent.putExtra("userType","3")
        startActivity(intent)
    }
}