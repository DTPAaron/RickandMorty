package com.example.rickandmorty.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.example.Data.characterdata
import com.example.rickandmorty.R
import com.example.rickandmorty.databinding.RowLayoutBinding
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso


class characteradapter(private var characterdata: ArrayList<characterdata>,private val listener:Listener):RecyclerView.Adapter<characteradapter.characterholder>() {
    interface Listener{
    fun onItemClick(characterdata: characterdata)
    }
    class characterholder(val binding: RowLayoutBinding):RecyclerView.ViewHolder(binding.root) {

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): characterholder {
        val binding= RowLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return characterholder(binding)
    }

    override fun getItemCount(): Int {
        return characterdata.count()
    }

    override fun onBindViewHolder(holder: characterholder, position: Int) {
        holder.binding.cardview.setOnClickListener {
        listener.onItemClick(characterdata.get(position))
        }
        holder.binding.charactername.text=characterdata[position].name

        val characterImageResource = when (characterdata[position].gender) {
            "Male" -> R.drawable.male
            "Female" -> R.drawable.female
            else -> R.drawable.unknown
        }
        Picasso.get().load(characterdata[position].image)
            .resize(140, 140)
            .centerCrop()
            .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
            .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
            .into(holder.binding.characterimage)

        Picasso.get().load(characterImageResource)
            .resize(100, 120)

            .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
            .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
            .into(holder.binding.genderimage)

    }

}