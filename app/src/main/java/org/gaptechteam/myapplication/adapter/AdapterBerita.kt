package org.gaptechteam.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.gaptechteam.myapplication.R
import org.gaptechteam.myapplication.model.Berita

class AdapterBerita( var data : ArrayList<Berita>) : RecyclerView.Adapter<AdapterBerita.Holder>() {
    class Holder(view :View): RecyclerView.ViewHolder(view){
        val tvJudul = view.findViewById<TextView>(R.id.tv_judul)
        val tvTanggal = view.findViewById<TextView>(R.id.tv_tanggal)
        val tvImg = view.findViewById<ImageView>(R.id.img_berita)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_berita, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.tvJudul.text = data[position].nama
        holder.tvTanggal.text = data[position].tanggal
        holder.tvImg.setImageResource(data[position].gambarBerita)

    }

    override fun getItemCount(): Int {
        return data.size

    }
}