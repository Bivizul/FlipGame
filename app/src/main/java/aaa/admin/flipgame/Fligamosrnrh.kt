package aaa.admin.flipgame

import aaa.admin.flipgame.Fligamu.FLIGAM
import android.content.Context
import com.appsflyer.AppsFlyerLib
import com.onesignal.OSNotificationReceivedEvent
import com.onesignal.OneSignal.OSRemoteNotificationReceivedHandler

class Fligamosrnrh : OSRemoteNotificationReceivedHandler {

    override fun remoteNotificationReceived(p0: Context?, p1: OSNotificationReceivedEvent?) {
        val corapponre = p1!!.notification.additionalData.get(FLIGAM).toString()
        if (corapponre.isNotEmpty()) {
            p0.let {
                AppsFlyerLib.getInstance().logEvent(p0, corapponre, null)
                p1.complete(null)
            }
        }
    }
}