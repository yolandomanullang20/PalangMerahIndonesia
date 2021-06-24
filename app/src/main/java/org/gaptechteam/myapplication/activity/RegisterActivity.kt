package org.gaptechteam.myapplication.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register.*
import okhttp3.ResponseBody
import org.gaptechteam.myapplication.MainActivity
import org.gaptechteam.myapplication.R
import org.gaptechteam.myapplication.app.ApiConfig
import org.gaptechteam.myapplication.halper.SharedPref
import org.gaptechteam.myapplication.model.ResponModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {

    lateinit var s: SharedPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        s = SharedPref(this)


        btn_register.setOnClickListener{
            register()
        }
        btn_google.setOnClickListener{
            Toast.makeText(this@RegisterActivity,"Coming Soon",Toast.LENGTH_SHORT).show()

        }
    }

    fun register(){
        if(edt_nama.text.isEmpty()){
            edt_nama.error = "Kolom Nama Tidak Boleh Kosong"
            edt_nama.requestFocus()
            return
        }else if (edt_email.text.isEmpty()){
            edt_email.error = "Kolom Email Tidak Boleh Kosong"
            edt_email.requestFocus()
            return
        }else if (edt_phone.text.isEmpty()){
            edt_phone.error = "Kolom No HP Tidak Boleh Kosong"
            edt_phone.requestFocus()
            return
        }else if (edt_password.text.isEmpty()){
            edt_password.error = "Kolom Password Tidak Boleh Kosong"
            edt_password.requestFocus()
            return
        }

        pb.visibility = View.VISIBLE
        ApiConfig.instanceRetrofit.register(edt_nama.text.toString(),edt_email.text.toString(),edt_phone.text.toString(),edt_password.text.toString()).enqueue(object :Callback<ResponModel>{

            override fun onFailure(call: Call<ResponModel>, t: Throwable) {
                pb.visibility = View.GONE

                Toast.makeText(this@RegisterActivity,"Sukses",Toast.LENGTH_SHORT).show()

            }

            override fun onResponse(call: Call<ResponModel>, response: Response<ResponModel>) {
                val respons = response.body()!!
                if(respons.success ==1 ){
                    pb.visibility = View.GONE
                    s.setStatusLogin(true)
                    val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()
                    Toast.makeText(this@RegisterActivity,"Selamat Datang " + respons.user.name,Toast.LENGTH_SHORT).show()

                }else{
//                    Toast.makeText(this@RegisterActivity,"Error",Toast.LENGTH_SHORT).show()
                    Toast.makeText(this@RegisterActivity,"Error " +respons.message,Toast.LENGTH_SHORT).show()

                }
            }

        })
    }
}