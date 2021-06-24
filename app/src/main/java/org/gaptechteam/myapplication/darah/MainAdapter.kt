package org.gaptechteam.myapplication.darah


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.gaptechteam.myapplication.databinding.ListBloodBinding

class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    private val data = mutableListOf<Blood>()
    fun updateData(newData: List<Blood>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }
    class ViewHolder(private val binding: ListBloodBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(hewan: Blood) = with(binding) {
            kodeTextView.text = "Golongan darah "  + hewan.goldaJenis
            namaTextView.text = hewan.goldaPositif
            hargaTextView.text = hewan.goldaNegatif

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListBloodBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])    }

    override fun getItemCount(): Int {
        return data.size
    }
}