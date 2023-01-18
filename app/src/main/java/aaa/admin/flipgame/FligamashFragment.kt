package aaa.admin.flipgame

import aaa.admin.flipgame.Fligamu.FLIGAMD
import aaa.admin.flipgame.Fligamu.FLIGAMEJ
import aaa.admin.flipgame.Fligamu.FLIGAMFOS
import aaa.admin.flipgame.Fligamu.FLIGAMKOR
import aaa.admin.flipgame.Fligamu.FLIGAMG
import aaa.admin.flipgame.Fligamu.FLIGAMNOS
import aaa.admin.flipgame.Fligamu.FLIGAMNP
import aaa.admin.flipgame.Fligamu.FLIGAMOV
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.appsflyer.AppsFlyerLib
import com.onesignal.OneSignal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.util.*

class FligamashFragment : Fragment(R.layout.fragment_fligamash) {

    var fligamsec = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (Fligamu.getFligamn(requireContext()) == FLIGAMD) fligamcn()
        else fligamsuc()
    }

    private fun fligamsuc() {
        CoroutineScope(Dispatchers.Main).launch {
            val fligamgr = Fligamnet.fligamnet().getFligamg(
                Fligam(
                    Fligamu.getFligami(requireContext()),
                    Fligamu.getFligamm(),
                    Fligamu.getFligams(requireContext()),
                    Fligamu.getFligamn(requireContext()),
                    Fligamu.getFligaml(),
                    Fligamu.getFligamt(),
                    Fligamu.getFligamf(requireContext(), fligamsec),
                    Fligamu.getFligamj(requireContext()),
                )
            )
            if (fligamgr.isSuccessful) {
                if (fligamgr.body() != null) {
                    when (fligamgr.body()!!.fligamg) {
                        FLIGAMG -> {
                            findNavController().navigate(R.id.action_fligamashFragment_to_fligammenuFragment)
                        }
                        FLIGAMNP -> {
                            Fligamu.setFligamosdp()
                            findNavController().navigate(R.id.action_fligamashFragment_to_fligammenuFragment)
                        }
                        else -> {
                            Fligamu.setFligamb(requireContext())
                            OneSignal.setExternalUserId(Fligamu.fligampvi(fligamgr.body()!!.fligamg))
                            AppsFlyerLib.getInstance()
                                .setCustomerUserId(Fligamu.fligampvi(fligamgr.body()!!.fligamg))
                            val fligami = Intent(requireActivity(), FligamActivity::class.java)
                            fligami.putExtra(FLIGAMKOR, fligamgr.body()!!.fligamg)
                            startActivity(fligami)
                            requireActivity().finish()
                        }
                    }
                } else {
                    Fligamu.getFligamdlg(requireActivity())
                }
            } else {
                Fligamu.getFligamdlg(requireActivity())
            }
        }
    }

    private fun fligamtimer() {
        val fligamtimer = Timer()
        fligamtimer.schedule(object : TimerTask() {
            override fun run() {
                if (Fligamu.getFligamafd(requireContext()) != FLIGAMEJ) {
                    try {
                        val fligamafd = JSONObject(Fligamu.getFligamafd(requireContext())!!)
                        if (fligamafd.get(FLIGAMFOS) == FLIGAMOV) {
                            Fligamu.setFligamcb(
                                requireContext(),
                                fligamafd.get(FLIGAMFOS) as String
                            )
                            fligamtimer.cancel()
                            fligamsuc()
                        } else if (fligamafd.get(FLIGAMNOS).toString().isNotEmpty()) {
                            Fligamu.setFligamcb(
                                requireContext(),
                                fligamafd.get(FLIGAMNOS) as String
                            )
                            fligamtimer.cancel()
                            fligamsuc()
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        fligamtimer.cancel()
                        fligamsuc()
                    }
                }
                fligamsec++
                if (fligamsec == 10) {
                    fligamtimer.cancel()
                    fligamsuc()
                }
            }
        }, 0, 1000)
    }

    private fun fligamcn() {
        if (!Fligamu.getFligamcb(requireContext())) {
            if (Fligamu.getFligamafd(requireContext()) != FLIGAMEJ) {
                val fligamafd = JSONObject(Fligamu.getFligamafd(requireContext())!!)
                if (fligamafd.get(FLIGAMFOS) == FLIGAMOV) {
                    Fligamu.setFligamcb(requireContext(), fligamafd.get(FLIGAMFOS) as String)
                    Fligamu.setFligamlu(fligamafd)
                } else if (fligamafd.get(FLIGAMNOS).toString().isNotEmpty()) {
                    Fligamu.setFligamcb(requireContext(), fligamafd.get(FLIGAMNOS) as String)
                    Fligamu.setFligamlu(fligamafd)
                }
            }
            fligamsuc()
        } else {
            fligamtimer()
        }
    }

}