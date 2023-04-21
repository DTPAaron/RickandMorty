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

        Picasso.get().load(characterdata[position].image).memoryPolicy(MemoryPolicy.NO_CACHE,MemoryPolicy.NO_STORE)
            .networkPolicy(NetworkPolicy.NO_CACHE,NetworkPolicy.NO_STORE).into(holder.binding.characterimage)
        if (characterdata[position].gender=="Male"){
            Picasso.get().load(R.drawable.male).memoryPolicy(MemoryPolicy.NO_CACHE,MemoryPolicy.NO_STORE)
                .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE).into(holder.binding.genderimage)
        }else if (characterdata[position].gender=="Female"){
            Picasso.get().load(R.drawable.female).memoryPolicy(MemoryPolicy.NO_CACHE,MemoryPolicy.NO_STORE)
                .networkPolicy(NetworkPolicy.NO_CACHE,NetworkPolicy.NO_STORE).into(holder.binding.genderimage)
        }else{
            Picasso.get().load(R.drawable.unknown).memoryPolicy(MemoryPolicy.NO_CACHE,MemoryPolicy.NO_STORE)
                .networkPolicy(NetworkPolicy.NO_CACHE,NetworkPolicy.NO_STORE).into(holder.binding.genderimage)
        }
    }

}