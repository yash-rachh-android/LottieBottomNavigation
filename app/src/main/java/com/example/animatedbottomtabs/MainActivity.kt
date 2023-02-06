package com.example.animatedbottomtabs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.airbnb.lottie.LottieAnimationView
import com.example.animatedbottomtabs.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView() {
        val lottie = LottieAnimationView(this)
        lottie.setAnimation(R.raw.home)
        val lottie1 = LottieAnimationView(this)
        lottie1.setAnimation(R.raw.home)
        val lottie2 = LottieAnimationView(this)
        lottie2.setAnimation(R.raw.home)
        binding.bottomNavigation.addTab(lottie,"HOME")
        binding.bottomNavigation.addTab(lottie1,"HOME1")
        binding.bottomNavigation.addTab(lottie2,"HOME2")
        binding.bottomNavigation.setOnItemClickListener { menu ->
            when(menu){
                "HOME" -> {
                    Log.e("TAG", "HOME")
                }
                "HOME1" -> {
                    Log.e("TAG", "HOME1")
                }
                "HOME2" -> {
                    Log.e("TAG", "HOME2")
                }
            }
        }
    }
}