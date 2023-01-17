package aaa.admin.flipgame

import aaa.admin.flipgame.Fligamu.FLIGAMAF
import aaa.admin.flipgame.Fligamu.FLIGAMOS
import android.app.Application
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.onesignal.OneSignal

class FlipGameApp : Application() {

    override fun onCreate() {
        super.onCreate()
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
        OneSignal.initWithContext(this)
        OneSignal.setAppId(FLIGAMOS)
        AppsFlyerLib.getInstance().init(FLIGAMAF, object :
            AppsFlyerConversionListener {
            override fun onConversionDataSuccess(p0: MutableMap<String, Any>?) {}
            override fun onConversionDataFail(p0: String?) {}
            override fun onAppOpenAttribution(p0: MutableMap<String, String>?) {}
            override fun onAttributionFailure(p0: String?) {}
        }, this)
        AppsFlyerLib.getInstance().start(this)
    }
}