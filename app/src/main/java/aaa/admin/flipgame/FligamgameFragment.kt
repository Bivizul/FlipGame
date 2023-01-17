package aaa.admin.flipgame

import aaa.admin.flipgame.FlipCardAnimation.OnContentChangeListener
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnticipateOvershootInterpolator
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.util.*


class FligamgameFragment : Fragment(R.layout.fragment_fligamgame) {

    var animation1: FlipCardAnimation? = null
    var animation: FlipCardAnimation? = null
    var animation_item: FlipCardAnimation? = null
    var animation_item1: FlipCardAnimation? = null
    var num = 0
    var iv_pro : ImageView? = null
    var iv_pro_item : ImageView? = null
    var iv_pro_item1 : ImageView? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val llyt = view.findViewById(R.id.llyt) as LinearLayout
        val llyt_item = view.findViewById(R.id.llyt_item) as LinearLayout
        val llyt_item1 = view.findViewById(R.id.llyt_item1) as LinearLayout
        val view_bg: View = view.findViewById(R.id.view_bg)
        iv_pro = view.findViewById<ImageView>(R.id.iv_pro)
        iv_pro_item = view.findViewById<ImageView>(R.id.iv_pro_item)
        iv_pro_item1 = view.findViewById<ImageView>(R.id.iv_pro_item1)
        val tv_item = view.findViewById<TextView>(R.id.tv_item)
        val tv_item1 = view.findViewById<TextView>(R.id.tv_item1)
        val tv_price = view.findViewById<TextView>(R.id.tv_price)
        val tv_price_item = view.findViewById<TextView>(R.id.tv_price_item)
        val tv_price_item1 = view.findViewById<TextView>(R.id.tv_price_item1)
        val tv = view.findViewById<TextView>(R.id.tv)
        llyt.setOnClickListener {
            startAnimation(animation, llyt, tv, tv_price, iv_pro, 180)
            startAnimation(animation1, view_bg, null, null, null, -180)
        }
        llyt_item.setOnClickListener {
            startAnimation(
                animation_item,
                llyt_item,
                tv_item,
                tv_price_item,
                iv_pro_item,
                180
            )
        }
        llyt_item1.setOnClickListener {
            startAnimation(
                animation_item1,
                llyt_item1,
                tv_item1,
                tv_price_item1,
                iv_pro_item1,
                -180
            )
        }

    }

    private fun startAnimation(
        animation: FlipCardAnimation?,
        llyt_item: View,
        tv_item: TextView?,
        tv_price_item: TextView?,
        iv_pro: ImageView?,
        degree: Int
    ) {
        var animation: FlipCardAnimation? = animation
        if (animation != null) {
            animation.setCanContentChange()
            llyt_item.startAnimation(animation)
        } else {
            val width = llyt_item.width / 2
            val height = llyt_item.height / 2
            animation = FlipCardAnimation(0f, degree.toFloat(), width.toFloat(), height.toFloat())
            animation.interpolator = AnticipateOvershootInterpolator()
            animation.duration = 3000
            animation.fillAfter = false
            animation.repeatCount = 1
            animation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {}
                override fun onAnimationEnd(animation: Animation) {}
                override fun onAnimationRepeat(animation: Animation) {
                    (animation as FlipCardAnimation).setCanContentChange()
                }
            })
            animation.setOnContentChangeListener(object : OnContentChangeListener {
                override fun contentChange() {
                    if (iv_pro == null) {
                        return
                    }
                    if (num >= 3) {
                        num = 0
                    }
//                    iv_pro.setBackgroundResource(DRAWABLE.get(num))
                    tv_item?.text = "￥" + Random().nextInt(500)
                    if (num === 0) {
                        tv_price_item?.text = "Discount"
                    } else if (num === 1) {
                        tv_price_item?.text = "Price"
                    } else if (num === 2) {
                        tv_price_item?.text = "Cost"
                    }
                    num++
                }
            })
            llyt_item.startAnimation(animation)
        }
    }

}