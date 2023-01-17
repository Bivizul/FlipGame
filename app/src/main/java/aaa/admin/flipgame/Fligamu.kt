package aaa.admin.flipgame

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.res.Configuration
import android.net.ConnectivityManager
import android.telephony.TelephonyManager
import com.onesignal.OneSignal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*

@Suppress("DEPRECATION")
object Fligamu {

    private const val FLIGAMT = "Error connection game network"
    private const val FLIGAMM = "Please reload"
    private const val FLIGAMPB = "Exit"
    private const val FLIGAMIV = "http://65.109.10.118/games/FlipGame/v.jpg"
    private const val FLIGAMIH = "http://65.109.10.118/games/FlipGame/h.jpg"
    private const val FLIGAMK = "fligamk"
    private const val NOFLIGAM = "nofligam"
    private const val FLIGAMGMT = "GMT"
    private const val AFPREFFLIGAM = "appsflyer-data"
    private const val FLIGAMAI = "attributionId"
    const val FLIGAMFOS = "af_status"
    const val FLIGAMAF = "VdkuAEYGy3tZxMUc6xgshQ"
    const val FLIGAMOV = "Organic"
    const val FLIGAMRC = 100
    const val FLIGAMKOR = "corappkor"
    const val FLIGAMDOR = "https://www.google.com/"
    const val FLIGAMNOS = "campaign"
    const val FLIGAM = "fligam"
    const val FLIGAMD = "default"
    const val FLIGAMN = "fligamg"
    const val FLIGAMNP = "nopush"
    const val FLIGAMEJ = "[]"
    const val APPPREFFLIGAM = "apppreffligam"
    const val FLIGAMFL = "fligamfl"
    const val FLIGAMOS = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"


    fun setFligamosdp() = OneSignal.disablePush(true)

    fun getFligamt(): String {
        val fligamtz: String = SimpleDateFormat("z", Locale.getDefault()).format(
            Calendar.getInstance(
                TimeZone.getTimeZone(FLIGAMGMT), Locale.getDefault()
            ).time
        ).replace(FLIGAMGMT, "")
        val fligamzone = if (fligamtz.contains(":")) fligamtz else this.FLIGAMD
        return fligamzone
    }

    fun setFligamb(fligamc: Context) {
        val fligamsp =
            fligamc.getSharedPreferences(APPPREFFLIGAM, Context.MODE_PRIVATE)
        fligamsp.edit().putBoolean(FLIGAMFL, false).apply()
    }

    fun getFligaml() = Locale.getDefault().language


    fun setFligamcb(fligamc: Context, fligams: String) {
        val fligamafsp =
            fligamc.getSharedPreferences(AFPREFFLIGAM, Context.MODE_PRIVATE)
        fligamafsp.edit().putString(this.FLIGAM, fligams).apply()
    }

    fun getFligamcb(fligamc: Context): Boolean {
        val fligamafsp =
            fligamc.getSharedPreferences(AFPREFFLIGAM, Context.MODE_PRIVATE)
        return fligamafsp.getBoolean(FLIGAMFL, true)
    }

    fun getFligamdlg(fligamact: Activity) {
        AlertDialog.Builder(fligamact).apply {
            setTitle(FLIGAMT)
            setMessage(FLIGAMM)
            setPositiveButton(FLIGAMPB) { _, _ ->
                fligamact.finish()
                System.exit(0)
            }
            setCancelable(true)
        }.create().show()
    }

    fun getFligami(fligamc: Context): String {
        val fligamsp = fligamc.getSharedPreferences(APPPREFFLIGAM, Context.MODE_PRIVATE)
        var fligamid = fligamsp.getString(FLIGAMK, NOFLIGAM) ?: NOFLIGAM
        if (fligamid == NOFLIGAM) {
            val fligamd = Date()
            val fligamsdf = SimpleDateFormat("yyMMddhhmmssMs")
            val fligamdt = fligamsdf.format(fligamd)
            val fligamrn = (10000 until 100000).random()
            fligamid = fligamdt + fligamrn
            fligamsp.edit().putString(FLIGAMK, fligamid).apply()
        }
        return fligamid
    }

    fun getFligamafd(fligamc: Context): String {
        val fligamafsp =
            fligamc.getSharedPreferences(AFPREFFLIGAM, Context.MODE_PRIVATE)
        return fligamafsp.getString(FLIGAMAI, FLIGAMEJ) ?: FLIGAMEJ
    }

    fun setFligamlu(fligamjo: JSONObject) {
        val fligamrs = Retrofit.Builder()
            .baseUrl("http://65.109.10.118/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FligamApi::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            fligamrs.setFligamr(
                fligamjo
            )
        }
    }

    fun getFligamm() = "${android.os.Build.MANUFACTURER} ${android.os.Build.MODEL}"

    @SuppressLint("MissingPermission")
    fun getFligamnet(fligamc: Context): Boolean {
        val conmanfligam =
            fligamc.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netinffligam = conmanfligam.activeNetworkInfo
        return netinffligam != null && netinffligam.isConnected
    }

    fun getFligamf(fligamc: Context, fligamsec: Int): Int {
        val fligamsp =
            fligamc.getSharedPreferences(AFPREFFLIGAM, Context.MODE_PRIVATE)
        return if (fligamsp.getBoolean(FLIGAMFL, true)) fligamsec else -1
    }

    fun getFligamim(fligamc: Context): String {
        val fligamc = fligamc.resources.configuration.orientation
        val fligami = if (fligamc == Configuration.ORIENTATION_PORTRAIT) {
            FLIGAMIV
        } else {
            FLIGAMIH
        }
        return fligami
    }

    fun fligampvi(fligamai: String): String {
        var fligamres = ""
        var fligampos = -1
        for (index in fligamai.indices) {
            if (fligamai[index] == '&') {
                fligampos = index
                break
            }
        }
        fligampos += 9
        while (fligamai[fligampos] in '0'..'9') {
            fligamres += fligamai[fligampos]
            fligampos++
        }
        return fligamres
    }

    fun getFligams(fligamc: Context) =
        (fligamc.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager).simCountryIso

    fun getFligamj(fligamc: Context): JSONObject {
        val fligamsp =
            fligamc.getSharedPreferences(AFPREFFLIGAM, Context.MODE_PRIVATE)
        val fligamd = fligamsp.getString(FLIGAMAI, FLIGAMEJ) ?: FLIGAMEJ
        return try {
            JSONObject(fligamd)
        } catch (e: Exception) {
            JSONObject()
        }
    }

    fun getFligamn(fligamc: Context): String {
        val fligamsp =
            fligamc.getSharedPreferences(APPPREFFLIGAM, Context.MODE_PRIVATE)
        return fligamsp.getString(this.FLIGAM, this.FLIGAMD) ?: this.FLIGAMD
    }
}