package com.example.rickandmorty

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.example.Data.characterdata
import com.example.rickandmorty.databinding.ActivityDetailsPageBinding
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class DetailsPage : AppCompatActivity() {
    private lateinit var binding:ActivityDetailsPageBinding
    private lateinit var characterdetails: characterdata
    private  var episodeId  = mutableListOf<String>()
    private lateinit var created:String
    var check=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id = intent.getStringExtra("id")

        loaddetails(id)
        binding.imageButton.setOnClickListener{
            check=1
            val check1=1
            val intent=Intent(this,HomePage::class.java)
            intent.putExtra("check1",check1)
            startActivity(intent)
            finish()
        }

    }

    private fun loaddetails(id:String?){
        val moshi= Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
        val retrofit= Retrofit.Builder().baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
        val api=retrofit.create(API::class.java)
        val call= api.getsinglecharacter(id)
        call.enqueue(object :Callback<characterdata>{
            override fun onResponse(
                call: Call<characterdata>,
                response: Response<characterdata>
            ) {
            if (response.isSuccessful){
                response.body().let {
                    characterdetails= it!!
                    binding.CharName.text=characterdetails.name
                    binding.Status.text=characterdetails.status
                    binding.Specy.text=characterdetails.species
                    binding.Gender.text=characterdetails.gender
                    binding.Origin.text= characterdetails.origin!!.name
                    binding.Location.text= characterdetails.location!!.name
                    Picasso.get().load(characterdetails.image).memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                        .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE).into(binding.charimage)

                    for (index in 0..characterdetails.episode.size-1){
                        episodeId.add(characterdetails.episode[index].substringAfterLast("/"))
                    }
                    binding.Episodes.text=episodeId.toString().replace("[","").replace("]","")
                    created(characterdetails)
                    binding.Created.text=created
                }
            }
            }

            override fun onFailure(call: Call<characterdata>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }
    private fun created(characterdetails:characterdata){

        val time=characterdetails.created?.substringAfterLast("T")
        val time2=time?.substringBeforeLast(".")
        val date=characterdetails.created?.substringBeforeLast("T")
        val day=date?.substringAfterLast("-")?.toInt()
        val month=date?.substringBeforeLast("-")?.substringAfterLast("-")
        val year=date?.substringBeforeLast("-")?.substringBeforeLast("-")
        if (month=="01"){
            created=day.toString()+" Jan "+year+","+time2
        }else if (month=="02"){
            created=day.toString()+" Feb "+year+","+time2
        }else if (month=="03"){
            created=day.toString()+" Mar "+year+","+time2
        }else if (month=="04"){
            created=day.toString()+" Apr "+year+","+time2
        }else if (month=="05"){
            created=day.toString()+" May "+year+","+time2
        }else if (month=="06"){
            created=day.toString()+" Jun "+year+","+time2
        }else if (month=="07"){
            created=day.toString()+" Jul "+year+","+time2
        }else if (month=="08 "){
            created=day.toString()+" Aug "+year+","+time2
        }else if (month=="09"){
            created=day.toString()+" Sep "+year+","+time2
        }else if (month=="10"){
            created=day.toString()+" Oct "+year+","+time2
        }else if (month=="11"){
            created=day.toString()+" Nov "+year+","+time2
        }else{
            created=day.toString()+" Dec "+year+","+time2
        }
    }

    override fun onPause() {
        super.onPause()
        if(check==0){
            val intent= Intent(this,SplashScreen::class.java)
            val text="Hello!"
            intent.putExtra("text",text)
            startActivity(intent)
            finish()
        }

    }

}