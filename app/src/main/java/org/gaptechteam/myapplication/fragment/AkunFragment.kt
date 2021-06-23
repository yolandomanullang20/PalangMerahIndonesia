package org.gaptechteam.myapplication.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import org.gaptechteam.myapplication.R
import org.gaptechteam.myapplication.halper.SharedPref

class AkunFragment : Fragment() {
    lateinit var s:SharedPref
    lateinit var btn_logout :Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.fragment_akun, container, false)

        btn_logout = view.findViewById(R.id.btn_logout)
        s = SharedPref(activity!!)

        btn_logout.setOnClickListener{
            s.setStatusLogin(false)
        }
        return view
    }


}