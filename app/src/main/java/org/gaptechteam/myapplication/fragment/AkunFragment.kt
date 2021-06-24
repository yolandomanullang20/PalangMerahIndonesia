package org.gaptechteam.myapplication.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import org.gaptechteam.myapplication.MainActivity
import org.gaptechteam.myapplication.R
import org.gaptechteam.myapplication.activity.LoginActivity
import org.gaptechteam.myapplication.activity.MasukActivity
import org.gaptechteam.myapplication.halper.SharedPref

class AkunFragment : Fragment() {
    lateinit var s:SharedPref
    lateinit var btn_logout :TextView
    lateinit var tvNama:TextView
    lateinit var tvEmail:TextView
    lateinit var tvPhone:TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.fragment_akun, container, false)


        init(view)

        s = SharedPref(requireActivity())
        setData()

        btn_logout.setOnClickListener{
            s.setStatusLogin(false)
            val intent = Intent(activity, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)

        }

        return view
    }

    fun setData(){
        if (s.getStatusLogin()==true){
            if(s.getUser()==null){
                val intent = Intent(activity, MasukActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                return
            }
            val user = s.getUser()!!

            tvNama.text = user.name
            tvEmail.text = user.email
            tvPhone.text = user.phone
        }



    }
    private fun init(view: View) {
        btn_logout = view.findViewById(R.id.btn_logout)
        tvNama = view.findViewById(R.id.tv_nama)
        tvEmail = view.findViewById(R.id.tv_email)
        tvPhone = view.findViewById(R.id.tv_phone)

    }


}