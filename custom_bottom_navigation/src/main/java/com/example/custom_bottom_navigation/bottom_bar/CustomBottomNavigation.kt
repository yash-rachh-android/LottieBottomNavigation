package com.example.custom_bottom_navigation.bottom_bar

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.airbnb.lottie.LottieAnimationView
import com.example.custom_bottom_navigation.databinding.CustomBottombarBinding

class CustomBottomNavigation @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var binding = CustomBottombarBinding.inflate(LayoutInflater.from(context), this, true)
    private var lottieViews = ArrayList<LottieModel>()
    private val Int.px get() = (this * Resources.getSystem().displayMetrics.density).toInt()

    fun addTab(
        lottieAnimationView: LottieAnimationView,
        id: String,
        lottieWidth: Int = 0,
        lottieHeight: Int = 0,

        ) {
        val tabLayout = RelativeLayout(context)
        val params = LayoutParams(0, android.view.ViewGroup.LayoutParams.MATCH_PARENT, 1f)
        if (lottieWidth != 0 || lottieHeight != 0) {
            val dynamicParams = RelativeLayout.LayoutParams(lottieWidth.px, lottieHeight.px)
            dynamicParams.addRule(RelativeLayout.CENTER_IN_PARENT)
            tabLayout.addView(lottieAnimationView, dynamicParams)
            binding.mainLayout.layoutParams = LayoutParams(
                android.view.ViewGroup.LayoutParams.MATCH_PARENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT
            )
        } else {
            val lottieParams = RelativeLayout.LayoutParams(
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT
            )
            lottieParams.addRule(RelativeLayout.CENTER_IN_PARENT)
            tabLayout.addView(lottieAnimationView, lottieParams)
            binding.mainLayout.layoutParams = LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, 50.px)
        }
        binding.mainLayout.addView(tabLayout, params)
        lottieViews.add(LottieModel(tabLayout, lottieAnimationView, id))
    }

    fun setBottomBarBackgroundColor(backgroundColor: String = "#FFFFF") {
        binding.mainLayout.setBackgroundColor(Color.parseColor(backgroundColor))
    }

    fun setOnItemClickListener(listener: (item: String?) -> Unit) {
        for (obj in lottieViews) {
            obj.parent?.setOnClickListener {
                listener(obj.id)
                setSelector(obj.id)
            }
        }
    }

    private fun setSelector(id: String) {
        resetAllViewSelection()
        val index = lottieViews.indexOfFirst {
            it.id == id
        }
        lottieViews[index].lottie?.playAnimation()
    }

    private fun resetAllViewSelection() {
        for (obj in lottieViews) {
            obj.lottie?.pauseAnimation()
            obj.lottie?.progress = 0F
        }
    }
}