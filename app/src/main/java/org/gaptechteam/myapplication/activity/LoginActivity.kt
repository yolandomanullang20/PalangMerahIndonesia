package org.gaptechteam.myapplication.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.edt_email
import kotlinx.android.synthetic.main.activity_login.edt_password
import kotlinx.android.synthetic.main.activity_login.pb
import org.gaptechteam.myapplication.MainActivity
import org.gaptechteam.myapplication.R
import org.gaptechteam.myapplication.app.ApiConfig
import org.gaptechteam.myapplication.halper.SharedPref
import org.gaptechteam.myapplication.model.ResponModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    lateinit var s:SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        s = SharedPref(this)
        btn_login.setOnClickListener{
            login()
        }
    }
    fun login(){
        if (
            edt_email.text.isEmpty()){
            edt_email.error = "Kolom Email Tidak Boleh Kosong"
            edt_email.requestFocus()
            return}
        else if (
            edt_password.text.isEmpty()){
            edt_password.error = "Kolom Password Tidak Boleh Kosong"
            edt_password.requestFocus()
            return
        }


        pb.visibility = View.VISIBLE
        ApiConfig.instanceRetrofit.login(edt_email.text.toString(),edt_password.text.toString()).enqueue(object :Callback<ResponModel>{

            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                Toast.makeText(this@LoginActivity,"Sukses",Toast.LENGTH_SHORT).show()
                pb.visibility = View.GONE

            }

            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                pb.visibility = View.GONE
                val respons = response.body()!!
                if(respons.success ==1 ){
                    s.setStatusLogin(true)
                    s.setUser(respons.user)
//                    s.setString(s.name,respons.user.name)
//                    s.setString(s.phone,respons.user.phone)
//                    s.setString(s.email,respons.user.email)
                    val intent = Intent(this@LoginActivity,MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()
                    Toast.makeText(this@LoginActivity,"Selamat Datang " + respons.user.name,Toast.LENGTH_SHORT).show()

                }else{
//                    Toast.makeText(this@RegisterActivity,"Error",Toast.LENGTH_SHORT).show()
                    Toast.makeText(this@LoginActivity,"Error " +respons.message,Toast.LENGTH_SHORT).show()

                }
            }

        })
    }

}


