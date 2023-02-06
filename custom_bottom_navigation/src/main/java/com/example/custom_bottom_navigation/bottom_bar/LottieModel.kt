package com.example.custom_bottom_navigation.bottom_bar

import android.widget.RelativeLayout
import com.airbnb.lottie.Lottie
import com.airbnb.lottie.LottieAnimationView

data class LottieModel(
    var parent : RelativeLayout ?= null,
    var lottie : LottieAnimationView ?= null,
    var id : String = ""
) {
}