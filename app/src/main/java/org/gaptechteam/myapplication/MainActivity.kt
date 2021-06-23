package org.gaptechteam.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import org.gaptechteam.myapplication.fragment.AkunFragment
import org.gaptechteam.myapplication.fragment.DarahFragment
import org.gaptechteam.myapplication.fragment.HomeFragment

class MainActivity : AppCompatActivity() {

    val fragmentHome : Fragment = HomeFragment()
    val fragmentDarah : Fragment = DarahFragment()
    val fragmentAkun : Fragment = AkunFragment()
    val fm : FragmentManager =supportFragmentManager
    var active : Fragment = fragmentHome

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fm.beginTransaction().add(R.id.container,fragmentHome).show(fragmentHome).commit()
        fm.beginTransaction().add(R.id.container,fragmentAkun).hide(fragmentAkun).commit()
        fm.beginTransaction().add(R.id.container,fragmentDarah).hide(fragmentDarah).commit()
    }
}