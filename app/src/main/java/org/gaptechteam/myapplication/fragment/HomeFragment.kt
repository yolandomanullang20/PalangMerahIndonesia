package org.gaptechteam.myapplication.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.fragment_home.*
import org.gaptechteam.myapplication.MainActivity
import org.gaptechteam.myapplication.R
import org.gaptechteam.myapplication.adapter.AdapterBerita
import org.gaptechteam.myapplication.adapter.AdapterSlider
import org.gaptechteam.myapplication.forum.activity.ForumActivity
import org.gaptechteam.myapplication.halper.SharedPref
import org.gaptechteam.myapplication.model.Berita

class HomeFragment : Fragment() {

    lateinit var vpSlider : ViewPager
    lateinit var rv_berita : RecyclerView
    lateinit var rv_rs : RecyclerView
    lateinit var tv_welcome : TextView
    lateinit var rl_forum : RelativeLayout
    lateinit var rl_news : RelativeLayout
    lateinit var s: SharedPref


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view : View = inflater.inflate(R.layout.fragment_home, container, false)

        s = SharedPref(requireActivity())


        //call item from xml
        vpSlider = view.findViewById(R.id.vp_slider)
        rv_berita = view.findViewById(R.id.rv_news)
        rv_rs = view.findViewById(R.id.rv_rs)
        tv_welcome = view.findViewById(R.id.tv_welcome)
        rl_forum = view.findViewById(R.id.rl_forum)
        rl_news = view.findViewById(R.id.rl_news)

        //slider home
        sliderHome(vpSlider)




        rl_forum.setOnClickListener{
            startActivity(Intent(activity, ForumActivity::class.java))

        }
        rl_news.setOnClickListener{
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse("https://www.pmikotabandung.org/pendaftaran_online/berita")
            startActivity(i)
        }









        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL

        val layoutManager2 = LinearLayoutManager(activity)
        layoutManager2.orientation = LinearLayoutManager.HORIZONTAL

        rv_berita.adapter = AdapterBerita(arrBerita)
        rv_berita.layoutManager = layoutManager

        rv_rs.adapter = AdapterBerita(arrRumahSakit)
        rv_rs.layoutManager = layoutManager2


        settingData()
        return view
    }
    fun settingData(){
        if (s.getStatusLogin()==false){
            tv_welcome.setText("Halo Guest")
        }else{
        if(s.getUser()==null){
            val intent = Intent(activity, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            return
        }
        else{
            val user = s.getUser()!!

            tv_welcome.text = "Halo " + user.name
        }
        }


    }
    //slider home
    fun sliderHome(vpSlider: ViewPager){
        val arraySlider = ArrayList<Int>()
        arraySlider.add(R.drawable.pmibandung)
        arraySlider.add(R.drawable.berita5)
        arraySlider.add(R.drawable.rs5)

        val adapterSlide = AdapterSlider(arraySlider, activity)
        vpSlider.adapter = adapterSlide
    }

    val arrBerita:ArrayList<Berita>get(){
        val arr = ArrayList<Berita>()
        val b1 =  Berita()
        b1.nama = "PENYERAHAN PIAGAM PENGHARGAAN PERWAKILAN DONOR 75x 2020"
        b1.tanggal = "22-11-2020"
        b1.gambarBerita = R.drawable.berita1

        val b2 =  Berita()
        b2.nama = "RANGKAIAN PERINGATAN HUT PALANG MERAH INDONESIA KE-75"
        b2.tanggal = "25-11-2020"
        b2.gambarBerita = R.drawable.berita2

        val b3 =  Berita()
        b3.nama = "Gerakan Cuci Tangan Bersama"
        b3.tanggal = "29-11-2020"
        b3.gambarBerita = R.drawable.berita3

        val b4 =  Berita()
        b4.nama = "PMI With TNI Melakanakan Kegiatan Donor Darah"
        b4.tanggal = "02-12-2020"
        b4.gambarBerita = R.drawable.berita4

        val b5 =  Berita()
        b5.nama = "Hut PMI Ke - 71"
        b5.tanggal = "03-12-2020"
        b5.gambarBerita = R.drawable.berita5

        arr.add(b1)
        arr.add(b2)
        arr.add(b3)
        arr.add(b4)
        arr.add(b5)

        return arr
    }
    val arrRumahSakit:ArrayList<Berita>get(){
        val arr = ArrayList<Berita>()
        val b1 =  Berita()
        b1.nama = "RS Dokter Hasan Sadikin"
        b1.tanggal = "Jl. Pasteur No.38,Pasteur"
        b1.gambarBerita = R.drawable.rs1

        val b2 =  Berita()
        b2.nama = "RS Advent Bandung"
        b2.tanggal = "Jl. Cihampelas No.161, Cipaganti"
        b2.gambarBerita = R.drawable.rs2

        val b3 =  Berita()
        b3.nama = "RS Santo Borromeus"
        b3.tanggal = "Jl. Ir. H. Juanda No.100, Lebakgede"
        b3.gambarBerita = R.drawable.rs3

        val b4 =  Berita()
        b4.nama = "RS Hermina Arcamanik"
        b4.tanggal = "Jl. A.H. Nasution No.50, Antapani Wetan,"
        b4.gambarBerita = R.drawable.rs4

        val b5 =  Berita()
        b5.nama = "RS PMI BANDUNG"
        b5.tanggal = "Jl. Aceh No.79, Cihapit,"
        b5.gambarBerita = R.drawable.rs5

        arr.add(b1)
        arr.add(b2)
        arr.add(b3)
        arr.add(b4)
        arr.add(b5)

        return arr
    }
}