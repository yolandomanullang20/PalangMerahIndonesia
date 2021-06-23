package org.gaptechteam.myapplication.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.gaptechteam.myapplication.R
import org.gaptechteam.myapplication.halper.SharedPref


class LoginActivity : AppCompatActivity() {
    lateinit var s: SharedPref


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        s = SharedPref(this)

        btn_prosesLogin.setOnClickListener{
            s.setStatusLogin(true)
        }
    }
}