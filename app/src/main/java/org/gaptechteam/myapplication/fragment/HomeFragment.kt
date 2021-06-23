package org.gaptechteam.myapplication.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import org.gaptechteam.myapplication.R
import org.gaptechteam.myapplication.adapter.AdapterSlider

class HomeFragment : Fragment() {

    lateinit var vpSlider : ViewPager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view : View = inflater.inflate(R.layout.fragment_home, container, false)
        vpSlider = view.findViewById(R.id.vp_slider)

        val arraySlider = ArrayList<Int>()
        arraySlider.add(R.drawable.slider1)
        arraySlider.add(R.drawable.slider2)
        arraySlider.add(R.drawable.slider3)

        val adapterSlide = AdapterSlider(arraySlider,activity)
        vpSlider.adapter = adapterSlide
        return view
    }


}