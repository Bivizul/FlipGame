package aaa.admin.flipgame

import android.graphics.Camera
import android.graphics.Matrix
import android.view.animation.Animation
import android.view.animation.Transformation

class FlipCardAnimation(
    private val mFromDegrees: Float, private val mToDegrees: Float,
    private val mCenterX: Float, private val mCenterY: Float
) :
    Animation() {
    private var mCamera: Camera? = null

    private var isContentChange = false
    private var listener: OnContentChangeListener? = null

    fun setCanContentChange() {
        isContentChange = false
    }

    override fun initialize(width: Int, height: Int, parentWidth: Int, parentHeight: Int) {
        super.initialize(width, height, parentWidth, parentHeight)
        mCamera = Camera()
    }

    override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
        val fromDegrees = mFromDegrees
        var degrees = fromDegrees + (mToDegrees - fromDegrees) * interpolatedTime
        val centerX = mCenterX
        val centerY = mCenterY
        val camera: Camera = mCamera!!
        val matrix: Matrix = t.getMatrix()
        camera.save()
        if (degrees > 90 || degrees < -90) {
            if (!isContentChange) {
                if (listener != null) {
                    listener!!.contentChange()
                }
                isContentChange = true
            }
            if (degrees > 0) {
                degrees = 270 + degrees - 90
            } else if (degrees < 0) {
                degrees = -270 + (degrees + 90)
            }
        }
        camera.rotateX(degrees)
        camera.getMatrix(matrix)
        camera.restore()
        matrix.preTranslate(-centerX, -centerY)
        matrix.postTranslate(centerX, centerY)
    }

    fun setOnContentChangeListener(listener: OnContentChangeListener?) {
        this.listener = listener
    }

    interface OnContentChangeListener {
        fun contentChange()
    }
}