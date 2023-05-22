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
        lottie.setAnimation(R.raw.tab_1)
        val lottie1 = LottieAnimationView(this)
        lottie1.setAnimation(R.raw.tab_1)
        val lottie2 = LottieAnimationView(this)
        lottie2.setAnimation(R.raw.tab_1)
        binding.bottomNavigation.addTab(lottie,"HOME", title = "Home")
        binding.bottomNavigation.addTab(lottie1,"HOME1",title = "Home 2")
        binding.bottomNavigation.addTab(lottie2,"HOME2",title = "Home 3")
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
        binding.bottomNavigation.setSelectedColor("#87B155")
        binding.bottomNavigation.setUnSelectedColor("#9595A1")
        binding.clickCheck.setOnClickListener {
            binding.bottomNavigation.selectLottieWithClick("HOME2")
        }
    }
}