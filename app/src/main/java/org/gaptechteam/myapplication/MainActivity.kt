package org.gaptechteam.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.gaptechteam.myapplication.activity.MasukActivity
import org.gaptechteam.myapplication.darah.BloodActivity
import org.gaptechteam.myapplication.fragment.AkunFragment
import org.gaptechteam.myapplication.fragment.HomeFragment
import org.gaptechteam.myapplication.fragment.RumsakFragment
import org.gaptechteam.myapplication.halper.SharedPref

class MainActivity : AppCompatActivity() {

    val fragmentHome : Fragment = HomeFragment()
//    val fragmentDarah : Fragment = DarahFragment()
    val fragmentAkun : Fragment = AkunFragment()
    val fragmentRs : Fragment = RumsakFragment()
    val fm : FragmentManager =supportFragmentManager
    var active : Fragment = fragmentHome

    private lateinit var menu : Menu
    private lateinit var menuItem: MenuItem
    private lateinit var bottonNavigationView: BottomNavigationView

    private val statusLogin = false

    private lateinit var s:SharedPref
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        s = SharedPref(this)
        setUpBottoNav()

    }

    fun setUpBottoNav(){
        fm.beginTransaction().add(R.id.container,fragmentHome).show(fragmentHome).commit()
        fm.beginTransaction().add(R.id.container,fragmentAkun).hide(fragmentAkun).commit()
//        fm.beginTransaction().add(R.id.container,fragmentDarah).hide(fragmentDarah).commit()
//        fm.beginTransaction().add(R.id.container,fragmentRs).hide(fragmentRs).commit()

        bottonNavigationView = findViewById(R.id.nav_view)
        menu = bottonNavigationView.menu
        menuItem = menu.getItem(0)
        menuItem.isChecked = true

        bottonNavigationView.setOnNavigationItemReselectedListener { item ->

            when(item.itemId) {
                R.id.navigation_home ->{
                    callFragment(0,fragmentHome)
                }
                R.id.navigation_darah ->{
                    if(s.getStatusLogin()==false){
                        Toast.makeText(this,"Please Login", Toast.LENGTH_SHORT).show();

                    }else{
                        startActivity(Intent(this, BloodActivity::class.java))
                    }

                }
//                R.id.navigation_rs ->{
//                    callFragment(2,fragmentRs)
//                }
                R.id.navigation_akun ->{
                    if(s.getStatusLogin()) {
                        callFragment(2, fragmentAkun)
                    }else{
                        startActivity(Intent(this, MasukActivity::class.java))
                    }
                }

            }
            false
        }
    }
    fun callFragment(int : Int,fragment: Fragment){
        menuItem = menu.getItem(int)
        menuItem.isChecked = true
        fm.beginTransaction().hide(active).show(fragment).commit()
        active = fragment
    }
}