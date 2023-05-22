package com.example.custom_bottom_navigation.bottom_bar

import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.airbnb.lottie.Lottie
import com.airbnb.lottie.LottieAnimationView

data class LottieModel(
    var parent : RelativeLayout ?= null,
    var lparent : LinearLayout ?= null,
    var lottie : LottieAnimationView ?= null,
    var id : String = "",
    var titleView : TextView ?= null
) {
}