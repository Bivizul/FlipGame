package aaa.admin.flipgame

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class FligammenuFragment : Fragment(R.layout.fragment_fligammenu) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnStart = view.findViewById<Button>(R.id.btn_start)

        btnStart.setOnClickListener {
            findNavController().navigate(R.id.action_fligammenuFragment_to_fligamgameFragment)
        }

    }

}