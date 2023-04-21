package com.example.rickandmorty.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.ColumnLayoutBinding
import com.example.rickandmorty.Data.locationdata


class locationadapter(private val locationdata:ArrayList<locationdata>, private val listener: Listener): RecyclerView.Adapter<locationadapter.locationholder>() {
    private var selectedItemPosition: Int = -1
    interface Listener{

        fun onitemclick(locationdata: locationdata)
    }

    class locationholder(val binding: ColumnLayoutBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): locationholder {
        val binding=ColumnLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return locationholder(binding)

    }

    override fun getItemCount(): Int {
        return locationdata.count()
    }

    override fun onBindViewHolder(holder: locationholder, position: Int) {

        holder.binding.locationbutton.setOnClickListener{
            holder.binding.locationbutton.setBackgroundColor(Color.rgb(26,62,65))
            selectedItemPosition=holder.adapterPosition
            listener.onitemclick(locationdata.get(position))
            notifyDataSetChanged()
        }
        if (selectedItemPosition==position){
            holder.binding.locationbutton.setBackgroundColor(Color.rgb(26,62,65))
        }else{
            holder.binding.locationbutton.setBackgroundColor(Color.rgb(65, 180, 203))
        }
        holder.binding.locationbutton.text=locationdata.get(position).name.toString()
    }


}