package com.example.rickandmorty

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.rickandmorty.databinding.ActivityMainBinding

class SplashScreen : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val text=intent.getStringExtra("text")
        if (text!=null){
            binding.splashtext.text=text
            var intent=Intent(this,HomePage::class.java)
            Handler().postDelayed({
                startActivity(intent)
                finish()
            },3000)
        }else{
            var intent=Intent(this,HomePage::class.java)
            Handler().postDelayed({
                startActivity(intent)
                finish()
            },3000)
        }

    }

}