package com.example.custom_bottom_navigation.bottom_bar

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.airbnb.lottie.LottieAnimationView
import com.example.custom_bottom_navigation.databinding.CustomBottombarBinding


class CustomBottomNavigation @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attrs, defStyleAttr) {

    private var binding = CustomBottombarBinding.inflate(LayoutInflater.from(context), this, true)
    private var lottieViews = ArrayList<LottieModel>()
    private val Int.px get() = (this * Resources.getSystem().displayMetrics.density).toInt()
    private var selectedColor = "#000000";
    private var unSelectedColor = "#000000";

    fun addTab(
        lottieAnimationView: LottieAnimationView,
        id: String,
        lottieWidth: Int = 0,
        lottieHeight: Int = 0,
        title: String = "",
    ) {
        val tabLayout = RelativeLayout(context)
        val params = LayoutParams(0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1f)
        lottieAnimationView.id = View.generateViewId()
        if (lottieWidth != 0 || lottieHeight != 0) {
            val dynamicParams = RelativeLayout.LayoutParams(lottieWidth.px, lottieHeight.px)
            dynamicParams.addRule(RelativeLayout.CENTER_IN_PARENT)
            tabLayout.addView(lottieAnimationView, dynamicParams)
            binding.mainLayout.layoutParams = LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        } else {
            val lottieParams = RelativeLayout.LayoutParams(
                50.px,
                50.px
            )
            lottieParams.addRule(RelativeLayout.CENTER_IN_PARENT)
            tabLayout.addView(lottieAnimationView, lottieParams)
            /*binding.mainLayout.layoutParams = LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, 50.px)*/
        }
        val titleView = TextView(context)
        if (title.isNotEmpty()) {
            val titleParams = RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
            )
            titleParams.addRule(RelativeLayout.BELOW, lottieAnimationView.id)
            titleParams.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE)
            titleParams.setMargins(0.px, 10.px, 0.px, 5.px)
            titleView.text = title
            tabLayout.addView(titleView, titleParams)
        }
        binding.mainLayout.addView(tabLayout, params)
        lottieViews.add(
            LottieModel(
                tabLayout,
                lottieAnimationView,
                id,
                titleView = if (title.isNotEmpty()) titleView else null
            )
        )
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

    fun selectLottieWithoutClick(id: String) {
        setSelector(id)
    }

    fun selectLottieWithClick(id: String) {
        performLottieClick(id)
    }

    fun setSelectedColor(color: String = "#000000") {
        selectedColor = color
    }

    fun setUnSelectedColor(color: String = "#000000") {
        unSelectedColor = color
    }

    private fun performLottieClick(id: String) {
        val index = lottieViews.indexOfFirst {
            it.id == id
        }
        lottieViews[index].parent?.performClick()
    }

    private fun setSelector(id: String) {
        resetAllViewSelection()
        val index = lottieViews.indexOfFirst {
            it.id == id
        }
        lottieViews[index].lottie?.playAnimation()
        lottieViews[index].titleView?.setTextColor(Color.parseColor(selectedColor))
    }

    private fun resetAllViewSelection() {
        for (obj in lottieViews) {
            obj.lottie?.pauseAnimation()
            obj.lottie?.progress = 0F
            obj.titleView?.setTextColor(Color.parseColor(unSelectedColor))
        }
    }
}