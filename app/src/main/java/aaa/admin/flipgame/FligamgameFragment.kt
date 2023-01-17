package aaa.admin.flipgame

import aaa.admin.flipgame.FlipCardAnimation.OnContentChangeListener
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnticipateOvershootInterpolator
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment

class FligamgameFragment : Fragment(R.layout.fragment_fligamgame) {

    var animation_item: FlipCardAnimation? = null
    var scoreTV: TextView? = null
    var countTV: TextView? = null
    var score = 0
    var count = 0

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(SCORE, score)
        outState.putInt(COUNT, count)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val llyt_item = view.findViewById(R.id.llyt_item) as LinearLayout
        val tv_price_item = view.findViewById<TextView>(R.id.tv_price_item)
        scoreTV = view.findViewById<TextView>(R.id.tv_score)
        countTV = view.findViewById<TextView>(R.id.tv_count)
        val btnBlack = view.findViewById<Button>(R.id.btn_black)
        val btnRed = view.findViewById<Button>(R.id.btn_red)
        btnBlack.setOnClickListener {
            startAnimation(
                animation_item,
                llyt_item,
                tv_price_item,
                BLACK_CARD,
                180,
            )
        }
        btnRed.setOnClickListener {
            startAnimation(
                animation_item,
                llyt_item,
                tv_price_item,
                RED_CARD,
                180
            )
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        score = savedInstanceState?.getInt(SCORE) ?: 0
        count = savedInstanceState?.getInt(COUNT) ?: 0
        scoreTV?.text = score.toString()
        countTV?.text = count.toString()
    }

    private fun startAnimation(
        animation: FlipCardAnimation?,
        llyt_item: View,
        tv_price_item: TextView?,
        colorCard: Int,
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
            animation.repeatCount = 0
            animation.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation) {}
                override fun onAnimationEnd(animation: Animation) {}
                override fun onAnimationRepeat(animation: Animation) {
                    (animation as FlipCardAnimation).setCanContentChange()
                }
            })
            animation.setOnContentChangeListener(object : OnContentChangeListener {
                override fun contentChange() {
                    var answer = ""
                    val numRandom = kotlin.random.Random.nextInt(1, 3)
                    if (colorCard == numRandom) {
                        answer = "RIGHT"
                        score++
                        count++
                    } else {
                        answer = "WRONG"
                        count++
                    }
                    when (numRandom) {
                        BLACK_CARD -> {
                            llyt_item.setBackgroundResource(R.drawable.shape_bg_black)
                            tv_price_item?.text = answer
                        }
                        else -> {
                            llyt_item.setBackgroundResource(R.drawable.shape_bg_red)
                            tv_price_item?.text = answer
                        }
                    }
                    scoreTV?.text = score.toString()
                    countTV?.text = count.toString()
                }
            })
            llyt_item.startAnimation(animation)
        }
    }

    companion object {
        const val BLACK_CARD = 1
        const val RED_CARD = 2
        const val SCORE = "score"
        const val COUNT = "count"
    }

}