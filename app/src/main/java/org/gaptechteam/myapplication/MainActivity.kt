package org.gaptechteam.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.gaptechteam.myapplication.fragment.AkunFragment
import org.gaptechteam.myapplication.fragment.DarahFragment
import org.gaptechteam.myapplication.fragment.HomeFragment

class MainActivity : AppCompatActivity() {

    val fragmentHome : Fragment = HomeFragment()
    val fragmentDarah : Fragment = DarahFragment()
    val fragmentAkun : Fragment = AkunFragment()
    val fm : FragmentManager =supportFragmentManager
    var active : Fragment = fragmentHome

    private lateinit var menu : Menu
    private lateinit var menuItem: MenuItem
    private lateinit var bottonNavigationView: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpBottoNav()

    }

    fun setUpBottoNav(){
        fm.beginTransaction().add(R.id.container,fragmentHome).show(fragmentHome).commit()
        fm.beginTransaction().add(R.id.container,fragmentAkun).hide(fragmentAkun).commit()
        fm.beginTransaction().add(R.id.container,fragmentDarah).hide(fragmentDarah).commit()

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
                    callFragment(1,fragmentDarah)
                }
                R.id.navigation_akun ->{
                    callFragment(2,fragmentAkun)

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