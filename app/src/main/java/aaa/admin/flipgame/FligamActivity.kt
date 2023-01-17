package aaa.admin.flipgame

import aaa.admin.flipgame.Fligamu.FLIGAMDOR
import aaa.admin.flipgame.Fligamu.FLIGAMKOR
import aaa.admin.flipgame.Fligamu.FLIGAMRC
import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import androidx.activity.ComponentActivity

@Suppress("DEPRECATION")
class FligamActivity : ComponentActivity() {

    private lateinit var fligamwv: WebView
    var fligamfpc: ValueCallback<Array<Uri>>? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fligam)

        fligamwv = findViewById(R.id.fligamwv)
        fligamwv.webViewClient = WebViewClient()

        fligamwv.webChromeClient = MyChromeClient()
        fligamwv.scrollBarStyle = WebView.SCROLLBARS_OUTSIDE_OVERLAY
        fligamwv.isScrollbarFadingEnabled = false

        setSettings()

        val fligamurl = intent.getStringExtra(FLIGAMKOR) ?: FLIGAMDOR

        if (savedInstanceState == null) {
            fligamwv.post {
                kotlin.run { fligamwv.loadUrl(fligamurl) }
            }
        }

        fligamwv.canGoBack()
        fligamwv.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK &&
                event.action == MotionEvent.ACTION_UP &&
                fligamwv.canGoBack()
            ) {
                fligamwv.goBack()
                return@OnKeyListener true
            }
            false
        })
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setSettings() {
        val fligamws = fligamwv.settings
        fligamws.javaScriptEnabled = true
        fligamws.loadWithOverviewMode = true
        fligamws.allowFileAccess = true
        fligamws.domStorageEnabled = true
        fligamws.builtInZoomControls = true
        fligamws.displayZoomControls = false
        fligamws.useWideViewPort = true
        fligamws.setSupportZoom(true)
        fligamws.userAgentString = fligamws.userAgentString.replace("; wv", "")
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        fligamwv.saveState(outState)
    }

    inner class MyChromeClient : WebChromeClient() {

        override fun onShowFileChooser(
            view: WebView,
            filePath: ValueCallback<Array<Uri>>,
            fileChooserParams: FileChooserParams
        ): Boolean {
            fligamfpc = filePath
            val fligami = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            fligami.putExtra(Intent.EXTRA_TITLE, "Image Chooser")
            startActivityForResult(fligami, FLIGAMRC)
            return true
        }

        private var fligamcv: View? = null
        private var fligamcvc: CustomViewCallback? = null
        private var fligamoo = 0
        private var fligamosuv = 0

        override fun getDefaultVideoPoster(): Bitmap? {
            return if (fligamcv == null) {
                null
            } else BitmapFactory.decodeResource(
                this@FligamActivity.applicationContext.resources,
                2130837573
            )
        }

        override fun onHideCustomView() {
            (this@FligamActivity.window.decorView as FrameLayout).removeView(fligamcv)
            fligamcv = null
            this@FligamActivity.window.decorView.systemUiVisibility =
                fligamosuv
            this@FligamActivity.requestedOrientation = fligamoo
            fligamcvc!!.onCustomViewHidden()
            fligamcvc = null
        }

        override fun onShowCustomView(
            paramView: View?,
            paramCustomViewCallback: CustomViewCallback?
        ) {
            if (fligamcv != null) {
                onHideCustomView()
                return
            }
            fligamcv = paramView
            fligamosuv =
                this@FligamActivity.window.decorView.systemUiVisibility
            fligamoo = this@FligamActivity.requestedOrientation!!
            fligamcvc = paramCustomViewCallback
            (this@FligamActivity.window.decorView as FrameLayout).addView(
                fligamcv,
                FrameLayout.LayoutParams(-1, -1)
            )
            this@FligamActivity.window.decorView.systemUiVisibility =
                3846 or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        if (requestCode == FLIGAMRC) {
            fligamfpc!!.onReceiveValue(
                WebChromeClient.FileChooserParams.parseResult(
                    resultCode,
                    intent
                )
            )
            fligamfpc = null
        }
    }

}